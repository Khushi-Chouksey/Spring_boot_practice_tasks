package com.example.Assigment2.service;

import com.example.Assigment2.model.User;
import com.example.Assigment2.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepo repo;
    private final AuthenticationManager authManager;
    private final JWTService jwtService;
    @Autowired
    public UserService(UserRepo repo, JWTService jwtService,AuthenticationManager authManager) {
        this.repo = repo;
        this.jwtService = jwtService;
        this.authManager = authManager;
    }

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public User save(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        return repo.save(user);
    }

    public String verify(User user) {
        Authentication authentication = authManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(user.getUsername());
        } else {
            return "fail";
        }
    }

//    public String verify(User user) {
//        try {
//            System.out.println("Attempting to authenticate user: " + user.getUsername());
//            User storedUser = repo.findByUsername(user.getUsername());
//
//            if (storedUser != null) {
//                System.out.println("User found in database: " + storedUser.getUsername());
//                // Compare the raw password from login with the encoded password stored in the database
//                if (encoder.matches(user.getPassword(), storedUser.getPassword())) {
//                    return "success";
//                } else {
//                    return "authentication failed: password mismatch";
//                }
//            } else {
//                return "authentication failed: user not found";
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            return "authentication failed: " + e.getMessage();
//        }
//    }
}
