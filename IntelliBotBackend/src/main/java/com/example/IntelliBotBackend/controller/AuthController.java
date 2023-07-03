package com.example.IntelliBotBackend.controller;

import com.example.IntelliBotBackend.request.Login;
import com.example.IntelliBotBackend.request.PromptRequest;
import com.example.IntelliBotBackend.request.RegisterUser;
import com.example.IntelliBotBackend.service.AuthService;
import com.example.IntelliBotBackend.utilities.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthService authService;
    @Autowired
    private Utils utils;


    /**
     * SignUp API
     * @param registerUser
     * @return
     */
    @PostMapping("/sign-up")
    public ResponseEntity<?> signup(@RequestBody RegisterUser registerUser) {
        return authService.signup(registerUser);
    }

    /**
     * Login API
     * @param login
     * @return
     */
    @PostMapping("/sign-in")
    public ResponseEntity<?> login(@RequestBody Login login) {
        return authService.login(login);

    }

}
