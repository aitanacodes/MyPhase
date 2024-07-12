package com.example.myphase.controller;

import com.example.myphase.dto.UserRegistrationDto;
import com.example.myphase.entity.User;
import com.example.myphase.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {


    private UserService userService;

    @Autowired
    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@Valid @RequestBody UserRegistrationDto registrationDto) {
        User user = userService.registerNewUser(registrationDto);
        return ResponseEntity.ok(user);
    }
}
