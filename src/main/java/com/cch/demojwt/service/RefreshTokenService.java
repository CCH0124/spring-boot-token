package com.cch.demojwt.service;

import java.util.Optional;

import com.cch.demojwt.entity.RefreshToken;

public interface RefreshTokenService {
    Optional<RefreshToken> findByToken(String token);
    RefreshToken createRefreshToken(String id);
    RefreshToken verifyExpiration(RefreshToken token);
    void deleteByUserId(String id);
}
