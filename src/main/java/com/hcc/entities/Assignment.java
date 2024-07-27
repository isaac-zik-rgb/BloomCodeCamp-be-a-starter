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

    @Column(name = "review_video_url")
    private String reviewVideoUrl;

    @ManyToOne(optional = false)
    @JoinTable(name = "users")
    private User user;

    public Assignment() {

    }

    public Assignment(String status, int number, String branch, String reviewVideoUrl, User user, String githubUrl) {
        this.status = status;
        this.number = number;
        this.branch = branch;
        this.reviewVideoUrl = reviewVideoUrl;
        this.user = user;
        this.githubUrl = githubUrl;
    }

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

    public String getReviewVideoUrl() {
        return reviewVideoUrl;
    }

    public void setReviewVideoUrl(String reviewVideoUrl) {
        this.reviewVideoUrl = reviewVideoUrl;
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
}
