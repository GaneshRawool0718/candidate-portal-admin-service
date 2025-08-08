package com.example.adminService.controller;

import com.example.adminService.dto.request.CandidateRequestDTO;
import com.example.adminService.model.Candidate;
import com.example.adminService.service.CandidateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CandidateController {

    private final CandidateService candidateService;

    @PostMapping("/register-candidate")
    public ResponseEntity<Candidate> registerCandidate(@RequestBody CandidateRequestDTO requestDTO) {
        Candidate savedCandidate = candidateService.registerCandidate(requestDTO);
        return ResponseEntity.ok(savedCandidate);
    }
}

