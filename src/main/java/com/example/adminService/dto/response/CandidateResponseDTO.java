package com.example.adminService.dto.response;

import com.example.adminService.enums.CandidateStatus;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CandidateResponseDTO {
    private Long id;
    private String name;
    private String email;
    private String technology;
    private String experience;
    private Integer score;
    private CandidateStatus status;
}

