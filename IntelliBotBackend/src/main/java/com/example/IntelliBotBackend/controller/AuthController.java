package com.example.IntelliBotBackend.controller;

import com.example.IntelliBotBackend.request.Login;
import com.example.IntelliBotBackend.request.RegisterUser;
import com.example.IntelliBotBackend.response.AuthenticationResponse;
import com.example.IntelliBotBackend.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/sign-up")
    @ResponseBody
    public ResponseEntity<String> signup(@RequestBody RegisterUser registerUser){
        authService.signup(registerUser);
        return new ResponseEntity<>("User Registration Successful", HttpStatus.OK);
    }
    @PostMapping("/sign-in")
    public AuthenticationResponse login(@RequestBody Login login){
        return authService.login(login);

    }
}
