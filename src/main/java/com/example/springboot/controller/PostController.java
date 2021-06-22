package com.example.springboot.controller;

import com.example.springboot.payload.request.PostSaveRequest;
import com.example.springboot.payload.request.PostUpdateRequest;
import com.example.springboot.payload.response.PostListResponse;
import com.example.springboot.payload.response.PostResponse;
import com.example.springboot.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class PostController {

    private final PostService postService;

    @PostMapping("/api/v1/post")
    public Long save(@RequestBody PostSaveRequest request) {
        return postService.save(request);
    }

    @PutMapping("/api/v1/post/{id}")
    public Long update(@PathVariable Long id, @RequestBody PostUpdateRequest request) {
        return postService.update(id, request);
    }

    @GetMapping("/api/v1/post/{id}")
    public PostResponse findById(@PathVariable Long id) {
        return postService.findById(id);
    }

    @GetMapping("/api/v1/post")
    public List<PostListResponse> findAllDesc() {
        return postService.findAllDesc();
    }

    @DeleteMapping("/api/v1/post/{id}")
    public Long delete(@PathVariable Long id) {
        postService.delete(id);
        return id;
    }
}
