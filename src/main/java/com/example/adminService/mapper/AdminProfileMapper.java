package com.example.adminService.mapper;

import com.example.adminService.dto.response.AdminProfileDTO;
import com.example.adminService.model.User;
import org.springframework.stereotype.Component;

@Component
public class AdminProfileMapper {
    // Converts User entity to AdminProfileDTO
    // This method maps the User entity to the AdminProfileDTO

    public AdminProfileDTO toDTO(User user) {
        AdminProfileDTO dto = new AdminProfileDTO();
        dto.setName(user.getName());
        return dto;
    }
}