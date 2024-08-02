package com.hcc.services;

import com.hcc.entities.Assignment;
import com.hcc.entities.User;
import com.hcc.enums.AssignmentStatusEnum;
import com.hcc.exceptions.AssignmentNotFoundException;
import com.hcc.repositories.AssignmentRepository;
import org.hibernate.Cache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AssignmentsService {
    @Autowired
    private AssignmentRepository assignmentRepo;
    private Cache cache;

    public List<Assignment> getAssignmentsByUser(User user) {
        return assignmentRepo.findByUser(user);
    }

    public Assignment getAssignmentById(Long Id) {
        return assignmentRepo.findById(Id).orElseThrow(() -> new AssignmentNotFoundException("No Assignment found"));
    }
    public List<Assignment> findAll(){
        List<Assignment> assignmentList = assignmentRepo.findAll();
        return assignmentList.stream()
                .filter(assignment ->!assignment.getStatus()
                        .equals(AssignmentStatusEnum.IN_REVIEW.getStatus()))
                .filter(assignment -> !assignment.getStatus().equals(AssignmentStatusEnum.PENDING_SUBMISSION.getStatus()))
                .filter(assignment -> assignment.getCodeReviewer() == null).collect(Collectors.toList());
    }


    public Assignment saveAssignment(Assignment assignment) {
        return assignmentRepo.save(assignment);
    }
    public List<Assignment> getAllAssignmentsByReviewer(User codeReviewer){
        return assignmentRepo.findByCodeReviewer(codeReviewer);
    }

}
