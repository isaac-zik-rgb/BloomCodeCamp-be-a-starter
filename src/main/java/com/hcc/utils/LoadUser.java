package com.hcc.utils;

import com.hcc.entities.Authority;
import com.hcc.entities.User;
import com.hcc.enums.AuthorityEnum;
import com.hcc.repositories.AuthorityRepository;
import com.hcc.repositories.UserRepository;
import com.hcc.services.UserDetailServiceImpl;
import org.aspectj.weaver.ast.Call;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.Callable;

@Component
public class LoadUser {
    private CustomPasswordEncoder passwordEncoder;

    public LoadUser(CustomPasswordEncoder passwordEncoder) {


        this.passwordEncoder = passwordEncoder;
    }

    public User getLeaner(){
        User user = new User();
        user.setPassword(passwordEncoder.getPasswordEncoder().encode("1234"));
        user.setUsername("isaac");
       user.setCohortStartDate(LocalDate.now());
       return user;
    }
}
