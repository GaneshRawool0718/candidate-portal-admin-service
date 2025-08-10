package com.example.adminService.service;

import com.example.adminService.constant.SignupConstants;
import com.example.adminService.dto.request.UserCreateDTO;
import com.example.adminService.model.User;
import com.example.adminService.repository.UserRepository;
import lombok.RequiredArgsConstructor;

import java.util.Map;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SignupService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public Map<String, String> register(UserCreateDTO dto) {
        if (userRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new IllegalArgumentException(SignupConstants.EMAIL_ALREADY_REGISTERED);
        }

        User user = new User();
        user.setEmail(dto.getEmail());
        user.setName(dto.getName());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));

        userRepository.save(user);
        return Map.of("message", SignupConstants.ADMIN_REGISTERED_SUCCESSFULLY);
    }
}
