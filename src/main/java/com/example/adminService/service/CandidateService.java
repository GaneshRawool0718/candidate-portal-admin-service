package com.example.adminService.service;

import com.example.adminService.dto.request.CandidateRequestDTO;
import com.example.adminService.model.Candidate;
import com.example.adminService.repository.CandidateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.example.adminService.constant.ApplicationConstants;

@Service
@RequiredArgsConstructor
public class CandidateService {
    /*
     * Service class for managing candidates.
     * It provides methods to register a new candidate.
     * This class uses the CandidateRepository to interact with the database.
     * 
     */
    private final CandidateRepository candidateRepository;

    public Candidate registerCandidate(CandidateRequestDTO dto) {
        // Optionally: Check if email already exists to prevent duplicates
        if (candidateRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new IllegalArgumentException(ApplicationConstants.CANDIDATE_EMAIL_EXISTS);
        }

        Candidate candidate = Candidate.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .technology(dto.getTechnology())
                .experience(dto.getExperience())
                .build();

        return candidateRepository.save(candidate);
    }
}
