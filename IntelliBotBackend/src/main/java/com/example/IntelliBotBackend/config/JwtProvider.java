//package com.example.IntelliBotBackend.config;
////
////import io.jsonwebtoken.Claims;
////import io.jsonwebtoken.Jwts;
////import io.jsonwebtoken.SignatureAlgorithm;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Service;
//
//import java.util.Date;
//
//@Service
//public class JwtProvider {
//
//
//
//    private static final String SECRET_KEY="0c97ab3cef7c978cf81e681fd70d7a8862dea635509928d6fe68a6d0d509785a";
//
//
//    /*
//    * Generates a JSON Web token from private key
//    * */
//    public String generateToken(Authentication authentication){
//        User principal= (User) authentication.getPrincipal();
//        //Creating a JSON Web Token and then the expiration time for the token and signing it with the private key
//        return Jwts.builder()
//                .setSubject(principal.getUsername())
//                .setIssuedAt(new Date(System.currentTimeMillis()))
//                .setExpiration(new Date(System.currentTimeMillis() + 1000*60*30 ))
//                .signWith(SignatureAlgorithm.HS256,SECRET_KEY)
//                .compact();
//    }
//
//    /**
//     *returns the PrivateKey to sign the JWT Token
//     */
//
//
//    /**
//     *returns the username extracted from the JWT
//     */
//    public String extractUsernameFromJWT(String token){
//        String username=  extractClaims(token).getSubject();
//        return username;
//    }
//
//    /*
//    *returns the Date extracted from the JWT
//    */
//    public Date extractExpirationDate(String token){
//        return extractClaims(token).getExpiration();
//    }
//
//    /*
//    * returns the same Claims object extracted from the JWT token
//    * */
//    public Claims extractClaims(String token){
//        Claims claims= extractAllClaims(token);
//        return claims;
//    }
//
//    /*
//    * returns the Claims object
//    * */
//    @SuppressWarnings("deprecation")
//    private Claims extractAllClaims(String token) {
//        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
//    }
//
//    /**
//     * compares the current date from the token expiry date and returns true or false
//     */
//    private Boolean isTokenExpired(String token){
//        return extractExpirationDate(token).before(new Date());
//    }
//
//    /*
//    * used for validating the token username and validity
//    * */
//    public Boolean validateToken(String token, UserDetails userDetails){
//        final String username= extractUsernameFromJWT(token);
//        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
//    }
//}
