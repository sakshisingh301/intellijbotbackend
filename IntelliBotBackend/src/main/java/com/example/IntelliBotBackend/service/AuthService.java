package com.example.IntelliBotBackend.service;

import com.example.IntelliBotBackend.config.JwtProvider;
import com.example.IntelliBotBackend.config.UserDetailsServiceImpl;
import com.example.IntelliBotBackend.entity.RegisteredUser;
import com.example.IntelliBotBackend.repository.UserRepository;
import com.example.IntelliBotBackend.request.Login;
import com.example.IntelliBotBackend.request.RegisterUser;
import com.example.IntelliBotBackend.response.AuthenticationResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class AuthService {

    private static final String ENCRYPTION_ALGORITHM = "SHA-256";
    private final UserRepository userRepository;

    private final AuthenticationManager authenticationManager;

    private final UserDetailsServiceImpl userDetailsService;

    private final JwtProvider jwtProvider;

    public AuthService(UserRepository userRepository, AuthenticationManager authenticationManager, UserDetailsServiceImpl userDetailsService, JwtProvider jwtProvider) {
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtProvider = jwtProvider;
    }

//    public ResponseEntity<?> signup(RegisteredUser registerUser) {
//        RegisteredUser registeredUser = new RegisteredUser();
//        List<RegisteredUser> existUser =  userRepository.findByEmail(registerUser.getEmail());
//        if (Objects.isNull(existUser) || existUser.isEmpty()) {
//            registeredUser.setEmail(registerUser.getEmail());
//            registeredUser.setPassword(passwordEncoder.encode(registerUser.getPassword()));
//            registeredUser.setDesignation(registerUser.getDesignation());
//            registeredUser.setActive(true);
//             return ResponseEntity.ok().body(userRepository.save(registeredUser));
//        }
//        return ResponseEntity.ok().body("User Already Exists!!");
//    }

    public ResponseEntity<?> signup(RegisteredUser registerUser) {
        Optional<RegisteredUser> existUser = userRepository.findByEmail(registerUser.getEmail());
        if (Objects.isNull(existUser)) {
            registerUser.setPassword(encryptPassword(registerUser.getPassword()));
            registerUser.setActive(true);
            userRepository.save(registerUser);
            return ResponseEntity.ok().body("User Registered Successfully!!");
        }
        return ResponseEntity.ok().body("User Already Exists!!");
    }

//    public AuthenticationResponse login(Login login) {
//        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login.getUsername(), encryptPassword(login.getPassword())));
//
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//
//
//        final UserDetails userDetails = userDetailsService.loadUserByUsername(login.getUsername());
//
//        final String jwtToken = jwtProvider.generateToken(authentication);
//
//        return new AuthenticationResponse(userDetails.getUsername(), jwtToken);
//    }

//    public ResponseEntity<?> login(Login login) {
//
//        RegisteredUser user = userRepository.findByEmail(login.getUsername()).get(0);
//        if (Objects.nonNull(user)){
//            if (Objects.equals(user.getPassword(), encryptPassword(login.getPassword()))) {
////                UserResponse response = mapper.userResponseMapping(user);
//                return ResponseEntity.ok(response);
//            }
//        }
//        return ResponseEntity.badRequest().body(CONSTANTS.USER_NOT_FOUND);
//    }
    public String encryptPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance(ENCRYPTION_ALGORITHM);
            byte[] hashedPasswordBytes = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hashedPasswordBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
}
