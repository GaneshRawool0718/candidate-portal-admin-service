package com.example.adminService.security;

import com.example.adminService.model.User;
import com.example.adminService.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    // This service implements UserDetailsService to load user-specific data
    // It retrieves user information from the UserRepository based on the email provided    


    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // This method loads user details by email
        // It retrieves the user from the repository and returns a UserDetails object
        User user = userRepository.findByEmail(email)
            .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return new org.springframework.security.core.userdetails.User(
            user.getEmail(),
            user.getPassword(),
            Collections.emptyList() // No roles
        );
    }
}

