package com.hcc.utils;

import com.hcc.entities.Authority;
import com.hcc.entities.User;
import com.hcc.repositories.AuthorityRepository;
import com.hcc.services.UserDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class LoadAuth {
    private Authority auth;
    public LoadAuth(){
    }


    public  Authority getAuthority(String authority) {
        this.auth = new Authority();
        auth.setAuthority(authority);
        return this.auth;
    }




}
