package com.example.springboot.payload.request;

import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostUpdateRequest {

    private String title;
    private String content;
}
