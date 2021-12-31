package com.cch.demojwt.repository;

import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import com.cch.demojwt.entity.User;
import com.cch.demojwt.response.VO.UserResponseVO;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByAccount(String account);

	boolean existsByAccount(String account);

	boolean existsByEmail(String email);

    @Transactional
	void deleteByAccount(String account);
}
