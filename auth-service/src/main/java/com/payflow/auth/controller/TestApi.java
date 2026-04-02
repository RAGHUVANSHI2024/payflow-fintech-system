package com.payflow.auth.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestApi {

    @GetMapping("/protected")
    public String testApi(
            @RequestHeader("X-User-Email") String email,
            @RequestHeader("X-User-Roles") String roles
    ) {
        return "Hello " + email + " Roles: " + roles;
    }
}
