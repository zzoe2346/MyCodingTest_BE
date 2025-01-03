package com.mycodingtest.controller;

import com.mycodingtest.dto.SolvedProblemWithReviewResponse;
import com.mycodingtest.service.SolvedProblemService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SolvedProblemController {

    private final SolvedProblemService solvedProblemService;

    public SolvedProblemController(SolvedProblemService solvedProblemService) {
        this.solvedProblemService = solvedProblemService;
    }

    @GetMapping("/api/solved-problems")
    public ResponseEntity<Page<SolvedProblemWithReviewResponse>> getSolvedProblemWithReviewPage(@PageableDefault Pageable pageable) {
        return ResponseEntity.ok(solvedProblemService.getSolvedProblemWithRiviewPage(pageable));
    }
}
