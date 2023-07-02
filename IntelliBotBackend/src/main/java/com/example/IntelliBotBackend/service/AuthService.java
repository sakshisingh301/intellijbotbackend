package com.example.IntelliBotBackend.service;

import com.example.IntelliBotBackend.config.JwtProvider;
import com.example.IntelliBotBackend.config.UserDetailsServiceImpl;
import com.example.IntelliBotBackend.entity.RegisteredUser;
import com.example.IntelliBotBackend.repository.UserRepository;
import com.example.IntelliBotBackend.request.Login;
import com.example.IntelliBotBackend.request.RegisterUser;
import com.example.IntelliBotBackend.response.AuthenticationResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthService {
    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    private final AuthenticationManager authenticationManager;

    private final UserDetailsServiceImpl userDetailsService;

    private final JwtProvider jwtProvider;

    public AuthService(PasswordEncoder passwordEncoder, UserRepository userRepository, AuthenticationManager authenticationManager, UserDetailsServiceImpl userDetailsService, JwtProvider jwtProvider) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtProvider = jwtProvider;
    }

    @Transactional
    public void signup(RegisterUser registerUser){
        RegisteredUser registeredUser= new RegisteredUser();
        registeredUser.setEmail(registerUser.getEmail());
        registeredUser.setPassword(passwordEncoder.encode(registerUser.getPassword()));
        registeredUser.setDesignation(registeredUser.getDesignation());
        registeredUser.setActive(true);
        userRepository.save(registeredUser);




    }

    public AuthenticationResponse login(Login login){
        Authentication authentication= authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login.getUsername(),login.getPassword()));

            SecurityContextHolder.getContext().setAuthentication(authentication);


        final UserDetails userDetails= userDetailsService.loadUserByUsername(login.getUsername());

        final String jwtToken= jwtProvider.generateToken(authentication);

        return new AuthenticationResponse(userDetails.getUsername(),jwtToken);
    }
}
