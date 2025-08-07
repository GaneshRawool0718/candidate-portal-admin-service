package com.example.adminService.dto.request;
import lombok.Data;

@Data
public class LoginDTO {
    // This class represents the data transfer object for user login
    // It contains the user's email and password for authentication
    private String email;
    private String password;
}

