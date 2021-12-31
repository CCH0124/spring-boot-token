package com.cch.demojwt.repository;

import java.util.Optional;

import com.cch.demojwt.definition.ERole;
import com.cch.demojwt.entity.Role;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long>{
    Optional<Role> findByName(ERole name);
}
