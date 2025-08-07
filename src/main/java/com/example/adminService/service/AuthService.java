package com.example.adminService.service;

import com.example.adminService.dto.request.LoginDTO;
import com.example.adminService.dto.response.AuthResponse;
import com.example.adminService.model.User;
import com.example.adminService.repository.UserRepository;
import com.example.adminService.security.CustomUserDetailsService;
import com.example.adminService.security.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    // This service handles authentication logic
    // It includes methods for user login and token generation  
    // It uses the AuthenticationManager to authenticate users and the JwtUtils to generate JWT tokens

    private final AuthenticationManager authenticationManager;
    private final CustomUserDetailsService userDetailsService;
    private final JwtUtils jwtUtils;
    private final UserRepository userRepository;

    public AuthResponse login(LoginDTO dto) {
        // Authenticate the user using the provided email and password
        // If authentication is successful, generate a JWT token and return it along with the user's ID
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getPassword())
        );

        UserDetails userDetails = userDetailsService.loadUserByUsername(dto.getEmail());
        String token = jwtUtils.generateToken(userDetails);

        User user = userRepository.findByEmail(dto.getEmail()).orElseThrow();

        return new AuthResponse(token, user.getId());
    }
}

