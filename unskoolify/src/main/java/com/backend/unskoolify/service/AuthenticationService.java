package com.backend.unskoolify.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.backend.unskoolify.dto.request.AuthRequest;
import com.backend.unskoolify.entity.UserInfo;
import com.backend.unskoolify.repository.AuthenticationRepository;
import com.backend.unskoolify.util.JwtUtils;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthenticationService {

    private final AuthenticationRepository authenticationRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;

    public String loginUser(AuthRequest authRequest) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword()));

        if (authentication.isAuthenticated()) {
            return jwtUtils.generateToken(authRequest.getEmail());
        }

        throw new UsernameNotFoundException("invalid user");
    }

    public boolean isEmailExists(String email) {
        return authenticationRepository.existsByEmail(email);
    }

    public void registerUser(UserInfo userInfo){
        authenticationRepository.save(userInfo);
    }
     

}
