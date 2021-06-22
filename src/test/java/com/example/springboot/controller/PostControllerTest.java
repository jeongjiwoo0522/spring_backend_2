package com.example.springboot.controller;

import com.example.springboot.entity.post.Post;
import com.example.springboot.entity.post.PostRepository;
import com.example.springboot.payload.request.PostSaveRequest;
import com.example.springboot.payload.request.PostUpdateRequest;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PostControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private PostRepository postRepository;

    @Test
    @Transactional
    public void Post_등록() throws Exception {
        // given
        String title = "title";
        String content = "content";
        PostSaveRequest request = PostSaveRequest
                .builder()
                .title(title)
                .content(content)
                .author("author")
                .build();
        String url = "http://localhost:" + port + "/api/v1/post";

        // when
        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url, request, Long.class);

        // then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Post> all = postRepository.findAll();
        assertThat(all.get(0).getTitle()).isEqualTo(title);
        assertThat(all.get(0).getContent()).isEqualTo(content);
    }

    @Test
    public void Post_수정() throws Exception {
        // given
        Post savedPost = postRepository.save(
                Post.builder()
                .title("title")
                .content("content")
                .author("author")
                .build()
        );
        Long updateId = savedPost.getId();
        String expectedTitle = "title2";
        String expectedContent = "content2";

        PostUpdateRequest request = PostUpdateRequest.builder()
                .title(expectedTitle)
                .content(expectedContent)
                .build();


        String url = "http://localhost:" + port + "/api/v1/post/" + updateId;

        HttpEntity<PostUpdateRequest> requestEntity = new HttpEntity<>(request);

        // when
        ResponseEntity<Long> responseEntity = restTemplate
                .exchange(url, HttpMethod.PUT, requestEntity, Long.class);

        // then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        Post all = postRepository.findById(updateId)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시물이 없습니다. id=" + updateId));

        assertThat(all.getTitle()).isEqualTo(expectedTitle);
        assertThat(all.getContent()).isEqualTo(expectedContent);
    }
}
