package com.pl.VerificationByLink.controller;

import com.pl.VerificationByLink.model.User;
import com.pl.VerificationByLink.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user){
        userService.registerUser(user);
        return  ResponseEntity.ok("User registered successfully. Please verify your email.");
    }

    @GetMapping("/verify")
    public RedirectView verify(@RequestParam("token") String token){
        if(userService.verify(token)){
            return  new RedirectView("/");
        }
        return new RedirectView("/invalid-token");
    }


    @GetMapping("/")
    private String home(){
        return "User Verified welcome to home";
    }
}
