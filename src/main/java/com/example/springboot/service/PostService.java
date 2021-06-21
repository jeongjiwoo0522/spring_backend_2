package com.example.springboot.service;

import com.example.springboot.entity.post.PostRepository;
import com.example.springboot.payload.request.PostSaveRequest;
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
}
