package com.hcc.filters;

import com.hcc.repositories.UserRepository;
import com.hcc.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.springframework.util.ObjectUtils.isEmpty;

@Component
public class JwtFilter extends OncePerRequestFilter {
    @Autowired
    private UserRepository userRepo;
    @Autowired
    private JwtUtils jwtUtils;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        //get auth header and validate
        final String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        if(isEmpty(header) || !header.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
        }
        //get users identity
        final String token = header.split(",")[1].trim();
        UserDetails userDetails = userRepo.findByUsername(jwtUtils.getUsernameFromToken(token)).orElse(null);
        if(userDetails == null) {
            throw new UsernameNotFoundException("Invalid Credentials");
        }
        if(!jwtUtils.validateToken(token, userDetails)){
            filterChain.doFilter(request, response);
        }
    }
}
