package com.hcc.repositories;

import com.hcc.entities.Authority;
import com.hcc.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    public Optional<User> findByUsername(String Username);
    public List<Authority> findAuthoritiesById(Long id);
}