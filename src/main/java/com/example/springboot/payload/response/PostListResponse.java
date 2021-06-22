package com.example.springboot.payload.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class PostListResponse {

    private Long id;
    private String title;
    private String author;
    private LocalDateTime updatedAt;
}
