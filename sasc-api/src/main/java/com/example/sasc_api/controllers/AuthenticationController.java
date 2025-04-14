package com.example.sasc_api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.sasc_api.domain.user.User;
import com.example.sasc_api.dto.AuthenticationRegister;
import com.example.sasc_api.dto.AuthenticationResponse;
import com.example.sasc_api.services.UserService;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    @Autowired
    UserService userService;

    @PostMapping
    public ResponseEntity<Object> create( @RequestBody AuthenticationRegister user) throws Exception {
        User userCreated = userService.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(userCreated);
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login( @RequestBody AuthenticationResponse user) throws Exception {
        String userLogin= userService.login(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(userLogin);
    }

}