package com.mycodingtest.controller;

import com.mycodingtest.dto.AlgorithmTagResponse;
import com.mycodingtest.dto.AlgorithmTagSetRequest;
import com.mycodingtest.service.AlgorithmTagService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AlgorithmTagController {

    private final AlgorithmTagService algorithmTagService;

    public AlgorithmTagController(AlgorithmTagService algorithmTagService) {
        this.algorithmTagService = algorithmTagService;
    }

    @PutMapping("/api/solved-problem/{solvedProblemId}/tag")
    public ResponseEntity<Void> setTags(@PathVariable Long solvedProblemId,
                                        @RequestBody AlgorithmTagSetRequest request) {
        algorithmTagService.setAlgorithmTags(solvedProblemId, request);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/api/solved-problem/{solvedProblemId}/tag")
    public ResponseEntity<AlgorithmTagResponse> getTags(@PathVariable Long solvedProblemId) {
        return ResponseEntity.ok(algorithmTagService.getAlgorithmTags(solvedProblemId));
    }
}
