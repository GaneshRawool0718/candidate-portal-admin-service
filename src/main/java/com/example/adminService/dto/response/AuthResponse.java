package com.example.adminService.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthResponse {
    // This class represents the response sent back to the client after a successful authentication
    // It includes the JWT token and the user's ID
    private String token;
    private Integer id;
}

