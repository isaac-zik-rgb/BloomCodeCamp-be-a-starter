package com.hcc.controllers;

import com.hcc.dto.LoginRequest;
import com.hcc.entities.User;
import com.hcc.enums.AuthorityEnum;
import com.hcc.services.UserDetailServiceImpl;
import com.hcc.utils.LoadUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
public class UserController{
    @Autowired
    UserDetailServiceImpl userDetailService;


    @PostMapping("/api/auth/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        UserDetails user = userDetailService.loadUserByUsername(loginRequest.getUsername());
        return ResponseEntity.ok(user);


    }
}
