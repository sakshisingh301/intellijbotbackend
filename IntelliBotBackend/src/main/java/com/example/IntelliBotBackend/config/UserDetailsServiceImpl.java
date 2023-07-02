package com.example.IntelliBotBackend.config;

import com.example.IntelliBotBackend.entity.RegisteredUser;
import com.example.IntelliBotBackend.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {


    private final UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<RegisteredUser> user = userRepository.findByEmail(email);
        RegisteredUser registeredUser = user.get();

        return new User(registeredUser.getEmail(),registeredUser.getPassword(),registeredUser.getActive(),true,true,true, null);
    }

    private Collection<? extends GrantedAuthority> getAuthorities(String roles) {
        return Collections.singletonList(new SimpleGrantedAuthority(roles));
    }
}
