package com.example.Assigment2.contoller;

import com.example.Assigment2.model.User;
import com.example.Assigment2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLOutput;

@RestController
public class UserController {

    @Autowired
    private UserService service;

   @PostMapping("/login")
    public String login(@RequestBody User user){

       return  service.verify(user);

    }


    @PostMapping("/register")
    public User register(@RequestBody User user){

       return service.save(user);
    }
}
