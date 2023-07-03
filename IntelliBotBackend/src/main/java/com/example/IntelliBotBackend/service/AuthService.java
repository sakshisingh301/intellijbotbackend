package com.example.IntelliBotBackend.service;

import com.example.IntelliBotBackend.request.Login;
import com.example.IntelliBotBackend.request.RegisterUser;
import org.springframework.http.ResponseEntity;

public interface AuthService {
    ResponseEntity<?> signup(RegisterUser registerUser);

    ResponseEntity<?> login(Login login);

    String encryptPassword(String password);
}
