package com.hcc.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Fetch;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name="users")
public final class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "cohort_start_date")
    private LocalDate cohortStartDate;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
    @JsonIgnore
    private List<Authority> authorities;


    public User() {

    }



    public User(LocalDate cohortStartDate, String username, String password) {
        this.cohortStartDate = cohortStartDate;
        this.password = password;
        this.username = username;

    }

    //getters and setters
    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDate getCohortStartDate() {
        return cohortStartDate;
    }

    public void setCohortStartDate(LocalDate cohortStartDate) {
        this.cohortStartDate = cohortStartDate;
    }

    @Override
    public Collection<? extends  GrantedAuthority> getAuthorities() {
       return new ArrayList<>(authorities);
    }
    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public void setAuthority(List<Authority> authorities) {

        this.authorities = authorities;
    }

}
