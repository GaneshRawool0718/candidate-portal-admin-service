package com.example.adminService.controller;

import com.example.adminService.dto.request.CandidateRequestDTO;
import com.example.adminService.enums.CandidateStatus;
import com.example.adminService.model.Candidate;
import com.example.adminService.service.CandidateService;
import lombok.RequiredArgsConstructor;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/candidates")
@RequiredArgsConstructor
public class CandidateController {

    private final CandidateService candidateService;

    /**
     * Registers a new candidate.
     * POST /api/candidates/register
     */
    @PostMapping("/register")
    public ResponseEntity<Candidate> registerCandidate(@RequestBody CandidateRequestDTO requestDTO) {
        Candidate savedCandidate = candidateService.registerCandidate(requestDTO);
        return ResponseEntity.ok(savedCandidate);
    }

    /**
     * Updates candidate score and auto-updates status based on score.
     * PUT /api/candidates/{id}/score
     */
    @PutMapping("/{id}/score")
    public ResponseEntity<Candidate> updateScore(@PathVariable Long id, @RequestBody Integer score) {
        Candidate updatedCandidate = candidateService.updateScore(id, score);
        return ResponseEntity.ok(updatedCandidate);
    }

    /**
     * Admin manually updates candidate status.
     * PUT /api/candidates/{id}/status
     */
   @PutMapping("/{id}/status")
    public ResponseEntity<Candidate> updateStatus(@PathVariable Long id, @RequestBody Map<String, String> statusPayload) {
        // Extract the status from the request body
        String statusString = statusPayload.get("status");

        if (statusString == null) {
            return ResponseEntity.badRequest().body(null); // Handle missing "status"
        }

        try {
            // Convert the status string to the enum
            CandidateStatus newStatus = CandidateStatus.valueOf(statusString.toUpperCase());
            Candidate updatedCandidate = candidateService.updateStatus(id, newStatus);
            return ResponseEntity.ok(updatedCandidate);
        } catch (IllegalArgumentException e) {
            // Handle invalid enum value
            return ResponseEntity.badRequest().body(null); // Or send a custom error response
        }
    }
}


