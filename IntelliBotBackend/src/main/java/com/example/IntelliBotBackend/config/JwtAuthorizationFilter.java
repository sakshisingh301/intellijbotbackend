//package com.example.IntelliBotBackend.config;
//
//
//import io.jsonwebtoken.ExpiredJwtException;
//import javax.servlet.*;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
////import jakarta.servlet.FilterChain;
////import jakarta.servlet.ServletException;
////import jakarta.servlet.http.HttpServletRequest;
////import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//
//import java.io.IOException;
//
//@Component
//public class JwtAuthorizationFilter extends OncePerRequestFilter {
//    private static final String HEADER_STRING = "Authorization";
//    private static final String TOKEN_PREFIX = "Bearer ";
//
//    @Autowired
//    private JwtProvider jwtProvider;
//
//    @Autowired
//    private UserDetailsServiceImpl userDetailsService;
//
//
//
//    private UsernamePasswordAuthenticationToken getAuthenticationToken(HttpServletRequest request) throws Exception {
//        String bearerString= request.getHeader(HEADER_STRING);
//        Boolean isTokenValid= false;
//        String token=bearerString.substring(7);
//        //System.out.println("JWT-TOKEN==>"+token);
//
//        String username= jwtProvider.extractUsernameFromJWT(token);
//
//        if(username!=null && SecurityContextHolder.getContext().getAuthentication() == null){
//            UserDetails userDetails= this.userDetailsService.loadUserByUsername(username);
//            try {
//                isTokenValid= jwtProvider.validateToken(token, userDetails);
//                if(isTokenValid){
//                    return new UsernamePasswordAuthenticationToken(username, null, userDetails.getAuthorities());
//                }
//            }
//            catch (ExpiredJwtException e){
//                throw new Exception("JWT token Expired. Please login again.");
//            }
//            catch (Exception e){
//                e.printStackTrace();
//            }
//
//        }
//        return null;
//    }
//
//
//@Override
//protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//    String requestHeader = request.getHeader(HEADER_STRING);
//
//    if (requestHeader == null || !requestHeader.startsWith(TOKEN_PREFIX)) {
//        filterChain.doFilter(request, response);
//        return;
//    }
//
//    try {
//        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = getAuthenticationToken(request);
//        if (usernamePasswordAuthenticationToken != null) {
//            usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
//        }
//    } catch (ExpiredJwtException e) {
//        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "JWT token expired. Please login again.");
//        return;
//    } catch (Exception e) {
//        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid JWT token.");
//        return;
//    }
//
//    filterChain.doFilter(request, response);
//}
//
//}
