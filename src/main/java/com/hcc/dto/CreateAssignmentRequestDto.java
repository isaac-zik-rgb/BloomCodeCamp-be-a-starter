package com.hcc.dto;

import com.hcc.enums.AssignmentEnum;
import com.hcc.enums.AssignmentStatusEnum;

public class CreateAssignmentRequestDto {
    private String githubUrl;
    private String branch;
    private int number;
    private String status;

    public CreateAssignmentRequestDto() {
    }

    public CreateAssignmentRequestDto(String githubUrl, String branch, int number) {
        this.githubUrl = githubUrl;
        this.branch = branch;
        this.number = number;
        this.status = AssignmentStatusEnum.PENDING_SUBMISSION.getStatus();
    }

    public String getGithubUrl() {
        return githubUrl;
    }

    public void setGithubUrl(String githubUrl) {
        this.githubUrl = githubUrl;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
