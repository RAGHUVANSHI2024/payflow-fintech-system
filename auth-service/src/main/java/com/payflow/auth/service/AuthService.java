package com.payflow.auth.service;

import com.payflow.auth.dto.LoginRequest;
import com.payflow.auth.dto.RegisterRequest;
import com.payflow.auth.entity.User;
import com.payflow.auth.repository.AuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private AuthRepository authRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    public User registerUser(RegisterRequest request){

        if (authRepository.existsByEmail(request.getEmail())){
            throw new RuntimeException("Use another email for register !");
        }

        User user =new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setEnabled(true);
        user.getRole().add("USER");

        return authRepository.save(user);

    }
    public User login(LoginRequest request){

        Optional<User> optionalUser = authRepository.findByEmail(request.getEmail());
        if (optionalUser.isEmpty()) {
            throw new IllegalArgumentException("Invalid email or password.");
        }

        User user = optionalUser.get();

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("Invalid email or password.");
        }
        return user;
    }
}
