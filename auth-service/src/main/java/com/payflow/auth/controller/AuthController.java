package com.payflow.auth.controller;

import com.netflix.discovery.converters.Auto;
import com.payflow.auth.dto.LoginRequest;
import com.payflow.auth.dto.RegisterRequest;
import com.payflow.auth.entity.RefreshToken;
import com.payflow.auth.entity.User;
import com.payflow.auth.service.AuthService;
import com.payflow.auth.service.RefreshTokenService;
import com.payflow.auth.utile.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private RefreshTokenService refreshTokenService;
    @Autowired
    private JwtUtils jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<User> createUser(@RequestBody RegisterRequest request){

        return ResponseEntity.ok().body(authService.registerUser(request));
    }

    public ResponseEntity<Map<String , Object>> login(@RequestBody LoginRequest request){

        User user = authService.login(request);
        String token =jwtUtil.generateToken(user.getEmail(),user.getRole());
        RefreshToken refreshToken = refreshTokenService.createRefreshToken(user.getEmail());

        return ResponseEntity.ok(Map.of(
                "token", token,
                "refreshToken",refreshToken,
                "role",user.getRole()
        ));

    }
}
