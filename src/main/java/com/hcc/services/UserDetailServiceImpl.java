package com.hcc.services;

import com.hcc.entities.Authority;
import com.hcc.entities.User;
import com.hcc.enums.AuthorityEnum;
import com.hcc.repositories.AuthorityRepository;
import com.hcc.utils.CustomPasswordEncoder;

import com.hcc.utils.LoadAuth;
import com.hcc.utils.LoadUser;
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
    CustomPasswordEncoder passwordEncoder;

    @Autowired
     private UserRepository userRepo;
    @Autowired
    private AuthorityRepository authRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

      Optional<User> userOpt =  userRepo.findByUsername(username);
      return userOpt.orElseThrow(() -> new UsernameNotFoundException("Invalid Credentials"));

    }


    public List<Authority> findUserRoleById(Long id) {
        return userRepo.findAuthoritiesById(id);
    }


    public User loadDummyUser() {
        LoadUser loadUser = new LoadUser(passwordEncoder);
        User user = loadUser.getLeaner();

        LoadAuth loadAuth = new LoadAuth();
        Authority auth = loadAuth.getAuthority(AuthorityEnum.ROLE_REVIEWER.name());
        user.addAuthority(auth);
        //save the user
        System.out.println("testing before saving user");
        userRepo.save(user);
        System.out.println("user already saved");
      //save the authority
        authRepo.save(auth);
        return user;
    }
}
