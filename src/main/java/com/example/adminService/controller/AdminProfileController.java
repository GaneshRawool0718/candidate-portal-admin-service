package com.example.adminService.controller;

import com.example.adminService.dto.request.AdminProfileUpdateDTO;
import com.example.adminService.dto.response.AdminProfileDTO;
import com.example.adminService.service.AdminProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/profile")
@RequiredArgsConstructor
public class AdminProfileController {
    /*
     * Controller for handling admin profile operations
     * such as retrieving and updating the profile of the currently logged-in admin.    
     * This controller uses the AdminProfileService to perform operations
     * and returns the appropriate response.
     */
    private final AdminProfileService adminProfileService;

    // Get currently logged-in admin profile
    @GetMapping
    public ResponseEntity<AdminProfileDTO> getProfile() {
        return ResponseEntity.ok(adminProfileService.getCurrentAdminProfile());
    }

    // Update currently logged-in admin profile
    @PutMapping
    public ResponseEntity<AdminProfileDTO> updateProfile(@RequestBody AdminProfileUpdateDTO updateDTO) {
        return ResponseEntity.ok(adminProfileService.updateCurrentAdminProfile(updateDTO));
    }
}
