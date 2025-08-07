package com.example.adminService.dto.request;

import lombok.Data;

@Data
public class UserCreateDTO {
    // This DTO is used for user registration
    // It contains the necessary fields for creating a new user account
    private String name;
    private String email;
    private String password;
}

