package com.backend.unskoolify.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/hello")
public class HelloWorldController {

    @GetMapping()
    public String hello() {
        return "Hello for all people!";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasAuthority('ROLE_SUPERADMIN')")
    public String adminRoute() {
        return "For super admin eyes only bro!";
    }

    @GetMapping("/user")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public String userRoute() {
        return "For user eyes only bro!";
    }
}
