package com.hcc.dto;

public class UpdateAssignmentDto {
    private String githubUrl;
    private String branch;

    public UpdateAssignmentDto() {
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

    public UpdateAssignmentDto(String githubUrl, String branch) {
        this.branch = branch;
        this.githubUrl = githubUrl;


    }




}
