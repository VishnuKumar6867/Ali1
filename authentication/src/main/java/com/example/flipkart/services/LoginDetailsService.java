package com.example.flipkart.services;

import com.example.flipkart.entity.LoginDetails;
import com.example.flipkart.exception.AuthenticationException;
import com.example.flipkart.repository.LoginDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class LoginDetailsService {
    @Autowired
    LoginDetailsRepository repository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public LoginDetails getByUsername(String username){
        return repository.findByUserName(username);
    }
    public LoginDetails createUser(LoginDetails details) throws AuthenticationException {
        if(repository.findByUserName(details.getUserName()) != null){
            throw new AuthenticationException("User Already available");
        }
        details.setPassword(passwordEncoder.encode(details.getPassword()));
        return repository.save(details);
    }
}
