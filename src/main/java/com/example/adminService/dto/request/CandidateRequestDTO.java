package com.example.adminService.dto.request;

import lombok.Data;

@Data
public class CandidateRequestDTO {
    private String name;
    private String email;
    private String technology;
    private String experience;
    private Integer score; 
}
