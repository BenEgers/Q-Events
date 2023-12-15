package com.example.demo.auth;

import com.example.demo.email.EmailService;
import com.example.demo.user.User;
import com.example.demo.user.UserAuth;
import com.example.demo.user.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final UserService userService;

    private final EmailService emailService;

    private final PasswordEncoder passwordEncoder;

    public AuthController(UserService userService, EmailService emailService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.emailService = emailService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping
    public Integer authenticateUser(@RequestBody UserAuth authuser){
        Optional<User> user = this.userService.getUserByEmail(authuser.getEmail());
        if(user.isPresent() && verifyPassword(authuser.getPassword(), user.get().getPassword()) ) {
            this.emailService.sendLoginEmail( user.get());
            return user.get().getId();
        } else {
            return 0;
        }

    }

    private boolean verifyPassword(String plainPassword, String hashedPassword) {
        return passwordEncoder.matches(plainPassword, hashedPassword);
    }
}



