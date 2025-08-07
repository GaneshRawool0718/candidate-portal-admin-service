package com.example.adminService.controller;

import com.example.adminService.dto.request.UserCreateDTO;
import com.example.adminService.service.SignupService;
import lombok.RequiredArgsConstructor;
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

    @PostMapping("/signup")
    public ResponseEntity<?> register(@RequestBody UserCreateDTO dto) {
        // Validate the input DTO
        try {
            String result = signupService.register(dto);
            return ResponseEntity.ok(result);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
