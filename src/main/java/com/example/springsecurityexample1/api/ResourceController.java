package com.example.springsecurityexample1.api;

import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/resource")
public class ResourceController {

    @GetMapping
    public String getResource() {
        return "Hello World";
    }

    @GetMapping("/user")
    public String getUser() {
        return "Hello User";
    }

    @GetMapping("/admin")
    public String getAdmin() {
        return "Hello Admin";
    }

    @GetMapping("/auth")
    public String auth() {
        return "Hello";
    }

}