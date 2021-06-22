package com.example.springboot.entity.post;

import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
@Transactional
public class PostRepositoryTest {

    @Autowired
    PostRepository postRepository;

    @Test
    public void 게시글저장_불러오기() {
        // given
        String title = "테스트 게시글";
        String content = "테스트 본문";

        postRepository.save(
                Post.builder()
                .title(title)
                .content(content)
                .author("jiwoourty@gmail.com")
                .build()
        );

        // when
        List<Post> postList = postRepository.findAll();

        // then
        Post post = postList.get(0);
        assertThat(post.getTitle()).isEqualTo(title);
        assertThat(post.getContent()).isEqualTo(content);
    }

    @Test
    @Transactional
    public void BaseTimeEntity_등록() {
        // given
        LocalDateTime now = LocalDateTime.of(2021, 6, 4, 0, 0, 0);
        postRepository.save(
                Post.builder()
                        .title("title")
                        .content("content")
                        .author("author")
                        .build()
        );
        // when
        List<Post> postList = postRepository.findAll();

        // then
        Post post = postList.get(0);

        System.out.println(">>>>>>>>>>>>>> createdAt=" + post.getCreatedAt() + ", updatedAt=" + post.getUpdatedAt());

        assertThat(post.getCreatedAt()).isAfter(now);
        assertThat(post.getUpdatedAt()).isAfter(now);
    }
}
