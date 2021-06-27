package com.example.springboot.controller;

import com.example.springboot.config.auth.LoginUser;
import com.example.springboot.config.auth.dto.SessionUser;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@RestController
public class IndexController {

    private final HttpSession httpSession;

    @GetMapping("/")
    public SessionUser index(@LoginUser SessionUser user) {
        return user;
    }
}
