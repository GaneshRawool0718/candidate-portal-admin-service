package com.example.adminService.service;


import com.example.adminService.constant.ApplicationConstants;
import com.example.adminService.dto.request.CandidateRequestDTO;
import com.example.adminService.enums.CandidateStatus;
import com.example.adminService.model.Candidate;
import com.example.adminService.repository.CandidateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CandidateService {

    /*
     * Service for managing candidates.
     * Handles registration, score updates, and status changes.
     * Uses CandidateRepository for database operations.
     * Validates candidate email uniqueness during registration.
     */
    private final CandidateRepository candidateRepository;

    public Candidate registerCandidate(CandidateRequestDTO dto) {
        // Check if a candidate with the same email already exists
        // If exists, throw an exception
        // This ensures email uniqueness
        if (candidateRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new IllegalArgumentException(ApplicationConstants.CANDIDATE_EMAIL_EXISTS);
        }

        Candidate candidate = Candidate.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .technology(dto.getTechnology())
                .experience(dto.getExperience())
                .score(dto.getScore() != null ? dto.getScore() : 0)
                .status(CandidateStatus.PENDING)
                .build();

        return candidateRepository.save(candidate);
    }

    public Candidate updateScore(Long candidateId, Integer score) {
        // Find the candidate by ID
        // If not found, throw an exception

        Candidate candidate = candidateRepository.findById(candidateId)
                .orElseThrow(() -> new RuntimeException(ApplicationConstants.CANDIDATE_NOT_FOUND + candidateId));

        candidate.setScore(score);

        if (!candidate.isStatusManuallyUpdated()) {
            candidate.setStatus(score >= 60 ? CandidateStatus.IN_PROGRESS : CandidateStatus.REJECTED);
        }

        return candidateRepository.save(candidate);
    }

    public Candidate updateStatus(Long candidateId, CandidateStatus newStatus) {
        // Find the candidate by ID
        // If not found, throw an exception 
        // Update the status and set statusManuallyUpdated to true
        Candidate candidate = candidateRepository.findById(candidateId)
                .orElseThrow(() -> new RuntimeException(ApplicationConstants.CANDIDATE_NOT_FOUND + candidateId));

        candidate.setStatus(newStatus);
        candidate.setStatusManuallyUpdated(true);

        return candidateRepository.save(candidate);
    }
}
