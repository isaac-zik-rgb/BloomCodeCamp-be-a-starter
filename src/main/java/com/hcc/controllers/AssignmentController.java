package com.hcc.controllers;

import com.hcc.dto.CreateAssignmentRequestDto;
import com.hcc.entities.Assignment;
import com.hcc.entities.User;
import com.hcc.repositories.AssignmentRepository;
import com.hcc.services.AssignmentsService;
import com.hcc.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
public class AssignmentController {

@Autowired
    private AssignmentsService assignmentsService;
@Autowired
    private AuthenticationManager authenticationManager;
@Autowired
private JwtUtils jwtUtils;
//100810
@GetMapping("/api/assignments")
    public ResponseEntity<?> getAllAssignments(@RequestHeader("Authorization") String authorization, @AuthenticationPrincipal User user) {
    //validate the token make sure it's not expired
    //extract token from authorizationHeader
    String token = null;
    if (authorization != null && authorization.startsWith("Bearer ")) {
        token = authorization.split(" ")[1].trim();

        if (user != null && token != null) {
            boolean isValid = jwtUtils.validateToken(token, user);

            if (isValid && !jwtUtils.isTokenExpired(token)) {
                return ResponseEntity.ok(assignmentsService.getAssignmentsByUser(user));
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

    }
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
}

@PostMapping("api/assignments")
    public ResponseEntity<?> createAssignment(@RequestBody CreateAssignmentRequestDto postAssign, @AuthenticationPrincipal User User) {

    return null;
}
}
