package com.mycodingtest.controller;

import com.mycodingtest.dto.JudgmentResultResponse;
import com.mycodingtest.dto.JudgmentResultSaveRequest;
import com.mycodingtest.dto.SubmittedCodeResponse;
import com.mycodingtest.service.JudgmentResultService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class JudgmentResultController {

    private final JudgmentResultService judgmentResultService;

    public JudgmentResultController(JudgmentResultService judgmentResultService) {
        this.judgmentResultService = judgmentResultService;
    }

    @PostMapping("/api/solved-problems/judgment-results")
    public ResponseEntity<String> saveJudgmentResult(@RequestBody JudgmentResultSaveRequest request) {
        judgmentResultService.saveJudgmentResult(request);
        return ResponseEntity.status(HttpStatus.CREATED).body("JudgmentResult saved successfully");
    }

    @GetMapping("/api/solved-problems/{solvedProblemId}/judgment-results")
    public ResponseEntity<List<JudgmentResultResponse>> getJudgmentResultList(@PathVariable Long solvedProblemId) {
        List<JudgmentResultResponse> judgmentResultList = judgmentResultService.getJudgmentResultList(solvedProblemId);
        return ResponseEntity.ok(judgmentResultList);
    }

    @GetMapping("/api/solved-problems/{solvedProblemId}/judgment-results/{judgmentId}/submitted-code")
    public ResponseEntity<SubmittedCodeResponse> getSubmittedCode(@PathVariable Long solvedProblemId,
                                                                  @PathVariable Long judgmentId) {
        String code = judgmentResultService.getSubmittedCode(solvedProblemId, judgmentId);
        return ResponseEntity.ok(new SubmittedCodeResponse(code));
    }
}
