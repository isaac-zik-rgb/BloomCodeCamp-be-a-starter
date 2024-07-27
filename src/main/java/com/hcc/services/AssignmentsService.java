package com.hcc.services;

import com.hcc.entities.Assignment;
import com.hcc.exceptions.AssignmentNotFoundException;
import com.hcc.repositories.AssignmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class AssignmentsService {
    @Autowired
    private AssignmentRepository assignmentRepo;

    public Set<Assignment> getAssignmentsByUsername(String username) {
        return assignmentRepo.findByUser_Username(username);
    }

    public Assignment getAssignmentById(Long Id) {
        return assignmentRepo.findById(Id).orElseThrow(() -> new AssignmentNotFoundException("No Assignment found"));
    }
}
