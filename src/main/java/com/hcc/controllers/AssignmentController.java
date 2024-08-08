package com.hcc.controllers;

import com.hcc.dto.CreateAssignmentRequestDto;
import com.hcc.dto.UpdateAssignmentDto;
import com.hcc.entities.Assignment;
import com.hcc.entities.User;
import com.hcc.enums.AssignmentStatusEnum;
import com.hcc.enums.AuthorityEnum;
import com.hcc.repositories.AssignmentRepository;
import com.hcc.services.AssignmentsService;
import com.hcc.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/assignments")
@CrossOrigin(origins = "*")
public class AssignmentController {

@Autowired
    private AssignmentsService assignmentsService;
@Autowired
    private AuthenticationManager authenticationManager;
@Autowired
private JwtUtils jwtUtils;
//100810

@GetMapping("/")
    public ResponseEntity<?> getAllAssignments() {
    try {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
                User user =  (User) authentication.getPrincipal();
                System.out.println(user.getUsername());
                List<String> authorities = user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
                if (authorities.contains(AuthorityEnum.ROLE_REVIEWER.name())){
                    System.out.println(true);
                    return ResponseEntity.ok(assignmentsService.findAll());
                }
                return ResponseEntity.ok(assignmentsService.getAssignmentsByUser(user));
    }catch (BadCredentialsException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
}

@PostMapping("/")
    public ResponseEntity<?> createAssignment(@RequestBody CreateAssignmentRequestDto postAssign) {
    // get the authenticated user
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (authentication.isAuthenticated()){
        // create a new Assignment
        User user = (User) authentication.getPrincipal();
        Assignment assignment = new Assignment();
        assignment.setBranch(postAssign.getBranch());
        assignment.setNumber(postAssign.getNumber());
        assignment.setUser(user);
        assignment.setStatus(AssignmentStatusEnum.SUBMITTED.getStatus());
        assignment.setGithubUrl(postAssign.getGithubUrl());
        //save the assignment and return it in the response
        return ResponseEntity.ok(assignmentsService.saveAssignment(assignment));
    }
    return  ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

}

@GetMapping("/assignments/{id}")
    public ResponseEntity<?> getAssignmentById(@PathVariable Long id) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    User user = (User) authentication.getPrincipal();
    List<String> authorities = user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
    if(authorities.contains(AuthorityEnum.ROLE_REVIEWER.name())){
        //get the assignmentById and change its status to inreview
        Assignment assignment = assignmentsService.getAssignmentById(id);
        assignment.setStatus(AssignmentStatusEnum.IN_REVIEW.getStatus());
        assignment.setCodeReviewer(user);
        //and save it back
        return ResponseEntity.ok(assignmentsService.saveAssignment(assignment));

    }
    Assignment assignment = assignmentsService.getAssignmentById(id);
    if (assignment != null){
        return ResponseEntity.ok(assignmentsService.getAssignmentById(id));
    }
    return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

}

@PutMapping("/assignments/{id}")
    public ResponseEntity<?> updateAssignment(@PathVariable Long id, @RequestBody UpdateAssignmentDto updateA) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

    //get the assignment by the Id
    Assignment assignment = assignmentsService.getAssignmentById(id);
    if (assignment.getStatus().equals(AssignmentStatusEnum.IN_REVIEW.getStatus())) {
        return ResponseEntity.status(405).body("Assignment under Review!!, Please try again later");
    }
    if(updateA.getBranch() != null && !updateA.getBranch().isBlank()) {
        assignment.setBranch(updateA.getBranch());
    }
    if(updateA.getGithubUrl() != null && !updateA.getGithubUrl().isBlank()) {
        assignment.setBranch(updateA.getGithubUrl());
    }
    return ResponseEntity.ok(assignmentsService.saveAssignment(assignment));
    //if assignment status is in review
}
@GetMapping("/assignment/reviews")
    public ResponseEntity<?> getAllReviewsAssignmentByCodeReviewer(){
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    User user = (User) authentication.getPrincipal();
    return ResponseEntity.ok(assignmentsService.getAllAssignmentsByReviewer(user));
}

}
