package com.payflow.auth.service;

import com.payflow.auth.entity.RefreshToken;
import com.payflow.auth.repository.RefreshTokenRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
public class RefreshTokenService {

    @Autowired
    private RefreshTokenRepository repository;

    private static final long REFRESH_TOKEN_GENERATION = 1000*60*60*24*7;

    @Transactional
    public RefreshToken createRefreshToken(String email){

        repository.deleteByUserEmail(email);

        RefreshToken refreshToken =new RefreshToken();
        refreshToken.setUserEmail(email);
        refreshToken.setToken(UUID.randomUUID().toString());
        refreshToken.setExpiryDate(Instant.now().plusMillis(REFRESH_TOKEN_GENERATION));

        return repository.save(refreshToken);
    }
}
