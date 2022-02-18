package com.cch.demojwt.service.impl;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

import com.cch.demojwt.definition.ResponseCode;
import com.cch.demojwt.entity.RefreshToken;
import com.cch.demojwt.exception.CustomException;
import com.cch.demojwt.repository.RefreshTokenRepository;
import com.cch.demojwt.repository.UserRepository;
import com.cch.demojwt.service.RefreshTokenService;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RefreshTokenServiceImpl implements RefreshTokenService {
    @Value("${jwtRefreshExpirationMs}")
    private Long refreshTokenDurationMs;

    private final RefreshTokenRepository refreshTokenRepository;

    private final UserRepository userRepository;

    @Override
    public Optional<RefreshToken> findByToken(String token) {
        // TODO Auto-generated method stub
        return refreshTokenRepository.findByToken(token);
    }

    @Override
    public RefreshToken createRefreshToken(String id) {
        // TODO Auto-generated method stub

        RefreshToken refreshToken = RefreshToken.builder()
                .id(userRepository.findById(UUID.fromString(id)).get().getId())
                .expiryDate(Instant.now().plusMillis(refreshTokenDurationMs))
                .token(UUID.randomUUID().toString().replace("-", ""))
                .build();
        refreshToken = refreshTokenRepository.save(refreshToken);
        return refreshToken;
    }

    @Override
    public RefreshToken verifyExpiration(RefreshToken token) {
        // TODO Auto-generated method stub
        if (token.getExpiryDate().compareTo(Instant.now()) < 0) {
            refreshTokenRepository.delete(token);
            throw new CustomException(ResponseCode.TOKEN_REFRESH_FAIL);
        }
        return token;
    }

    @Override
    public void deleteByUserId(String id) {
        // TODO Auto-generated method stub
        refreshTokenRepository.deleteByUser(userRepository.findById(UUID.fromString(id)).get());
    }

}
