package com.cch.demojwt.repository;

import java.util.Optional;
import java.util.UUID;

import com.cch.demojwt.entity.RefreshToken;
import com.cch.demojwt.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, UUID> {
    Optional<RefreshToken> findById(Long id);

    Optional<RefreshToken> findByToken(String token);

    void deleteByUser(User user);
}
