package com.hcc.controllers;

import com.hcc.dto.LoginRequest;
import com.hcc.entities.User;
import com.hcc.utils.JwtUtils;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class UserController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    JwtUtils jwtUtil;
    @CrossOrigin("*")
    @PostMapping("/login")
    public ResponseEntity<?> login (@RequestBody LoginRequest req) {
        String token = null;

        try {
            Authentication authenticate = authenticationManager
                    .authenticate(
                            new UsernamePasswordAuthenticationToken(
                                    req.getUsername(), req.getPassword()
                            )
                    );

            User user = (User) authenticate.getPrincipal();
            user.setPassword(null);
            System.out.println("done");
            Map<String, String> responseBody = new HashMap<>();
            responseBody.put("username", user.getUsername());
            token =  jwtUtil.generateToken(user);
            responseBody.put("token", token);
            return ResponseEntity.ok()
                    .header(
                            HttpHeaders.AUTHORIZATION,
                         token
                    )

                    .body(responseBody);
        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @GetMapping("/validate")
    public ResponseEntity<?> validateToken(@RequestParam String token, @AuthenticationPrincipal User user) {
        try {
            if (user != null) {
                boolean isTokenValid = jwtUtil.validateToken(token, user);
                return ResponseEntity.ok(isTokenValid);} else {
                return ResponseEntity.ok(false);
            }
        } catch (ExpiredJwtException ex) {
            return ResponseEntity.ok(false);
        }
    }
}
