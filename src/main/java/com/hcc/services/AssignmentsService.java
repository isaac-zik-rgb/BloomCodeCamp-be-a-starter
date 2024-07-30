package com.hcc.services;

import com.hcc.entities.Assignment;
import com.hcc.entities.User;
import com.hcc.exceptions.AssignmentNotFoundException;
import com.hcc.repositories.AssignmentRepository;
import org.hibernate.Cache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class AssignmentsService {
    @Autowired
    private AssignmentRepository assignmentRepo;
    private Cache cache;

    public Set<Assignment> getAssignmentsByUser(User user) {
        return assignmentRepo.findByUser(user);
    }

    public Assignment getAssignmentById(Long Id) {
        return assignmentRepo.findById(Id).orElseThrow(() -> new AssignmentNotFoundException("No Assignment found"));
    }

    public Assignment saveAssignment(Assignment assignment) {
        return assignmentRepo.save(assignment);
    }

}
