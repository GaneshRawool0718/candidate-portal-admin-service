package com.example.adminService.model;

import com.example.adminService.enums.CandidateStatus;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "candidates")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Candidate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true, nullable = false)
    private String email;

    private String technology;

    private String experience;

    @Builder.Default
    private Integer score = 0;

    @Enumerated(EnumType.STRING)
    private CandidateStatus status;

    @Builder.Default
    private boolean statusManuallyUpdated = false;
}
