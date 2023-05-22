package com.backend.unskoolify.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.unskoolify.entity.UserInfo;

public interface AuthenticationRepository extends JpaRepository<UserInfo, Integer>{
    Optional<UserInfo> findByEmail(String email);
    Boolean existsByEmail(String email);

}
