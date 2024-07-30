package com.hcc.utils;

import com.hcc.entities.Authority;
import com.hcc.entities.User;
import com.hcc.enums.AuthorityEnum;
import com.hcc.repositories.AuthorityRepository;
import com.hcc.repositories.UserRepository;
import net.bytebuddy.asm.Advice;
import org.hibernate.engine.loading.internal.LoadContexts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {
    @Autowired
    UserRepository userRepo;
    @Autowired
    AuthorityRepository authRepo;

    @Override
    public void run(String... args) throws Exception {
        //load the userData
        loadUserData();
        //load authority data
        loadAuthData();
    }

    private void loadUserData(){
        if (userRepo.count() == 0) {
            PasswordEncoder pwden = new BCryptPasswordEncoder();
            String pw = pwden.encode("1234");

            User isaac = new User(LocalDate.now(), "isaac", pw);
            userRepo.save(isaac);

            User isaacAdmin = new User(LocalDate.now(), "isaacAdmin", pw);
            userRepo.save(isaacAdmin);
        }
    }

    private void loadAuthData() {
        if (authRepo.count() == 0) {
            Authority learner  = new Authority(AuthorityEnum.ROLE_LEARNER.name(), userRepo.findByUsername("isaac").get());
            authRepo.save(learner);

            Authority reviewer = new Authority(AuthorityEnum.ROLE_REVIEWER.name(), userRepo.findByUsername("isaacAdmin").get());
            authRepo.save(reviewer);
        }
    }
}
