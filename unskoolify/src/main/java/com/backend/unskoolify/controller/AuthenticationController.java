package com.backend.unskoolify.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.backend.unskoolify.dto.request.AuthRequest;
import com.backend.unskoolify.dto.request.RegisterDTO;
import com.backend.unskoolify.dto.response.ResponseDTO;
import com.backend.unskoolify.dto.response.TokenDTO;
import com.backend.unskoolify.entity.UserInfo;
import com.backend.unskoolify.service.AuthenticationService;
import com.backend.unskoolify.util.Logger;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    private final PasswordEncoder passwordEncoder;
    
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@Valid @RequestBody AuthRequest authRequest){
        try {
            String token= authenticationService.loginUser(authRequest);
            TokenDTO tokenDTO = new TokenDTO();
            tokenDTO.setToken(token);
            List<TokenDTO> listTokenDTO = new ArrayList<TokenDTO>();
            listTokenDTO.add(tokenDTO);
            return new ResponseEntity<ResponseDTO>(new ResponseDTO(true, "Login sucessfully, use token to call other REST APIs", listTokenDTO), HttpStatus.OK);    
        } catch (Exception e) {
            return new ResponseEntity<ResponseDTO>(new ResponseDTO(false, "Email not available", null), HttpStatus.NOT_FOUND);    
        }
        
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterDTO registerDTO){
        Logger.log(AuthenticationController.class).info("start user registerUser method");
        if (authenticationService.isEmailExists(registerDTO.getEmail())){
            return new ResponseEntity<ResponseDTO>(new ResponseDTO(false, "Email Id already registered", null), HttpStatus.CONFLICT);    
        }
        var role = "ROLE_CUSTOMER";
        switch(registerDTO.getRole()){
            case 1:
                role="ROLE_SUPERADMIN";
                break;
            case 2:
                role="ROLE_VENDOR";
                    break;
            case 3:
                role="ROLE_CUSTOMER";
                    break;
            case 4:
                role="ROLE_DELIVERY";
                    break;
            default:
                role="ROLE_CUSTOMER";

        }
        
        UserInfo userInfo = new UserInfo();
        userInfo.setName(registerDTO.getUsername());
        userInfo.setEmail(registerDTO.getEmail());
        userInfo.setPassword(passwordEncoder.encode(registerDTO.getPassword()));
        userInfo.setRoles(role);
        userInfo.setDeviceName(registerDTO.getDeviceName());
        userInfo.setDeviceId(registerDTO.getDeviceId());
        authenticationService.registerUser(userInfo);

        return new ResponseEntity<ResponseDTO>(new ResponseDTO(true, "Registered Successfully", null), HttpStatus.CREATED);

    }
}
