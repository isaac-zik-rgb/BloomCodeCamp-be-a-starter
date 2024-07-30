package com.hcc.entities;

import javax.persistence.*;

@Entity
@Table(name="Assignments")
public class Assignment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "status")
    private String status;

    @Column(name = "number")
    private int number;

    @Column(name = "branch")
    private String branch;

    @Column(name = "github_url")
    private String githubUrl;

    @Column(name = "code_review_video_url")
    private String codeReviewVideoUrl;

    @ManyToOne(optional = false)
    private User user;

    @ManyToOne
    private User codeReviewer;

    public Assignment() {

    }

    public Assignment(String status, int number, String branch, String reviewVideoUrl, User user, String githubUrl) {
        this.status = status;
        this.number = number;
        this.branch = branch;
        this.codeReviewVideoUrl = reviewVideoUrl;
        this.user = user;
        this.githubUrl = githubUrl;
    }

    //getters and setters
    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getCodeReviewVideoUrl() {
        return codeReviewVideoUrl;
    }

    public void setCodeReviewVideoUrl(String codeReviewVideoUrl) {
        this.codeReviewVideoUrl = codeReviewVideoUrl;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getGithubUrl() {
        return this.githubUrl;
    }
    public void setGithubUrl(String githubUrl) {
        this.githubUrl = githubUrl;
    }

    public User getCodeReviewer() {
        return codeReviewer;
    }
    public void setCodeReviewer(User codeReviewer) { this.codeReviewer = codeReviewer;}
}
