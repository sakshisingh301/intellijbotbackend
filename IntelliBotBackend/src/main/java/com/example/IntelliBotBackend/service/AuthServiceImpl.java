//package com.example.IntelliBotBackend.service;
//
////import com.example.IntelliBotBackend.config.JwtProvider;
////import com.example.IntelliBotBackend.config.UserDetailsServiceImpl;
//import com.example.IntelliBotBackend.entity.RegisteredUser;
//import com.example.IntelliBotBackend.repository.UserRepository;
//import com.example.IntelliBotBackend.request.Login;
//import com.example.IntelliBotBackend.request.RegisterUser;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import java.security.MessageDigest;
//import java.security.NoSuchAlgorithmException;
//import java.util.Objects;
//
//@Service
//public class AuthServiceImpl implements AuthService {
//
//    private static final String ENCRYPTION_ALGORITHM = "SHA-256";
//    @Autowired
//    private UserRepository userRepository;
//    @Autowired
//    private AuthenticationManager authenticationManager;
//    @Autowired
//    private UserDetailsServiceImpl userDetailsService;
////    @Autowired
////    private JwtProvider jwtProvider;
//
//    /**
//     * Sign Up Service
//     *
//     * @param registerUser
//     * @return
//     */
//    @Override
//    public ResponseEntity<?> signup(RegisterUser registerUser) {
//        try {
//            RegisteredUser registeredUser = new RegisteredUser();
//            RegisteredUser existUser = userRepository.findByEmail(registerUser.getEmail()).orElse(null);
//            if (Objects.isNull(existUser)) {
//                registeredUser.setEmail(registerUser.getEmail());
//                registeredUser.setPassword(encryptPassword(registerUser.getPassword()));
//                registeredUser.setDesignation(registerUser.getDesignation());
//                registeredUser.setActive(true);
//                userRepository.save(registeredUser);
//                return ResponseEntity.ok().body("User Registered Successfully!!");
//            }
//            return ResponseEntity.ok().body("User Already Exists!!");
//        } catch (Exception e) {
//            return ResponseEntity.badRequest().body("Error occurred during signup: " + e.getMessage());
//        }
//    }
//
////    public AuthenticationResponse login(Login login) {
////        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login.getUsername(), encryptPassword(login.getPassword())));
////
////        SecurityContextHolder.getContext().setAuthentication(authentication);
////
////
////        final UserDetails userDetails = userDetailsService.loadUserByUsername(login.getUsername());
////
////        final String jwtToken = jwtProvider.generateToken(authentication);
////
////        return new AuthenticationResponse(userDetails.getUsername(), jwtToken);
////    }
//
//
//    /**
//     * Login Service
//     *
//     * @param login
//     * @return
//     */
//    @Override
//    public ResponseEntity<?> login(Login login) {
//        try {
//            RegisteredUser user = userRepository.findByEmail(login.getEmail()).orElse(null);
//            if (Objects.nonNull(user) && Objects.equals(user.getPassword(), encryptPassword(login.getPassword()))) {
//                return ResponseEntity.ok(user);
//            }
//            return ResponseEntity.badRequest().body("User Not Found !!");
//        } catch (Exception e) {
//            throw new UsernameNotFoundException("Error in fetching user!!" + e);
//        }
//    }
//
//    /**
//     * Encrypt Password
//     *
//     * @param password
//     * @return
//     */
//    @Override
//    public String encryptPassword(String password) {
//        try {
//            MessageDigest md = MessageDigest.getInstance(ENCRYPTION_ALGORITHM);
//            byte[] hashedPasswordBytes = md.digest(password.getBytes());
//            StringBuilder sb = new StringBuilder();
//            for (byte b : hashedPasswordBytes) {
//                sb.append(String.format("%02x", b));
//            }
//            return sb.toString();
//        } catch (NoSuchAlgorithmException e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
//}
