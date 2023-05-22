package com.backend.unskoolify.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterDTO {

    private String username;
    private String email;
    private String password;
    // 1 ROLE_SUPERADMIN
    // 2 ROLE_VENDOR
    // 3 ROLE_CUSTOMER
    // 4 ROLE_DELIVERY
    private int role;
    private String deviceName;
    private String deviceId;


}
