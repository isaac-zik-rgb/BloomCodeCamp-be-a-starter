package com.hcc.services;


import com.hcc.entities.User;
import com.hcc.repositories.AuthorityRepository;
import com.hcc.utils.CustomPasswordEncoder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.hcc.repositories.UserRepository;

import java.util.*;


@Service
public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
     private UserRepository userRepo;
    @Autowired
    private AuthorityRepository authRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
      Optional<User> userOpt =  userRepo.findByUsername(username);
      return userOpt.orElseThrow(() -> new UsernameNotFoundException("Invalid Credentials"));

    }



}
