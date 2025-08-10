package com.example.adminService.controller;

import com.example.adminService.dto.request.LoginDTO;
import com.example.adminService.dto.request.UserCreateDTO;
import com.example.adminService.dto.response.AuthResponse;
import com.example.adminService.service.AuthService;
import com.example.adminService.service.SignupService;
import lombok.RequiredArgsConstructor;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    // This controller handles authentication-related requests
    // It includes user registration functionality
    
    private final SignupService signupService; // This service handles user registration
    private final AuthService authService;
    
    @PostMapping("/signup")
    public ResponseEntity<?> register(@RequestBody UserCreateDTO dto) {
        // Validate the input DTO
        try {
            Map<String, String> result = signupService.register(dto);
            return ResponseEntity.ok(result);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginDTO dto) {
        // Authenticate the user using the provided login credentials
        // If authentication is successful, return the AuthResponse containing the JWT token and user ID
        return ResponseEntity.ok(authService.login(dto));
    }
}
