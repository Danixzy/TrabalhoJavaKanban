package com.example.kanban.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/protected-endpoint")
public class ProtectedController {

    @GetMapping
    public String accessProtectedEndpoint() {
        return "You have accessed a protected endpoint!";
    }
}
