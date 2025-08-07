package com.example.adminService.service;

import com.example.adminService.dto.request.AdminProfileUpdateDTO;
import com.example.adminService.dto.response.AdminProfileDTO;
import com.example.adminService.mapper.AdminProfileMapper;
import com.example.adminService.model.User;
import com.example.adminService.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminProfileService {
    /*  
     * Service for handling admin profile operations
     * such as retrieving and updating the profile of the currently logged-in admin.
     * This service interacts with the UserRepository to fetch and update user data
     * and uses AdminProfileMapper to convert between User entity and AdminProfileDTO.
     */

    private final UserRepository userRepository;
    private final AdminProfileMapper mapper;

    // Get the currently logged-in admin's email from security context
    private String getCurrentUserEmail() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            return ((UserDetails) principal).getUsername();
        }
        return principal.toString();
    }

    public AdminProfileDTO getCurrentAdminProfile() {
        String email = getCurrentUserEmail();
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Admin not found"));
        return mapper.toDTO(user);
    }

    public AdminProfileDTO updateCurrentAdminProfile(AdminProfileUpdateDTO updateDTO) {
        String email = getCurrentUserEmail();
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Admin not found"));

        if (updateDTO.getName() != null) {
            user.setName(updateDTO.getName());
        }

        userRepository.save(user);
        return mapper.toDTO(user);
    }
}
