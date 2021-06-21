package com.example.springboot.service;

import com.example.springboot.entity.post.Post;
import com.example.springboot.entity.post.PostRepository;
import com.example.springboot.payload.request.PostSaveRequest;
import com.example.springboot.payload.request.PostUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;

    @Transactional
    public Long save(PostSaveRequest request) {
        return postRepository.save(request.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostUpdateRequest request) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시물이 없습니다. id=" + id));
        post.update(request.getTitle(), request.getContent());
        return id;
    }
}
