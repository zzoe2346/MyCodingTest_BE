package com.mycodingtest.controller;

import com.mycodingtest.dto.JudgmentResultResponse;
import com.mycodingtest.dto.SaveJudgmentResultRequest;
import com.mycodingtest.entity.JudgmentResult;
import com.mycodingtest.service.JudgmentResultService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class JudgmentResultController {

    private final JudgmentResultService judgmentResultService;

    public JudgmentResultController(JudgmentResultService judgmentResultService) {
        this.judgmentResultService = judgmentResultService;
    }

    @PostMapping("/api/judgment")
    public ResponseEntity<String> saveJudgmentResult(@RequestBody SaveJudgmentResultRequest request) {
        judgmentResultService.saveJudgmentResult(request);
        return ResponseEntity.ok("Judgment-Result saved successfully");
    }

    @GetMapping("/api/judgment/result")
    public ResponseEntity<Page<JudgmentResult>> getJudgmentResult(Pageable pageable) {
        Long userId = 1L;
        Page<JudgmentResult> judgmentResultPage = judgmentResultService.getJudgmentResultPagenationByUserId(userId, pageable);
        return ResponseEntity.ok(judgmentResultPage);
    }

    @GetMapping("/api/judgment/result/{judgmentResultId}")
    public ResponseEntity<JudgmentResultResponse> getJudgmentResult(@PathVariable Long judgmentResultId) {
        JudgmentResultResponse response = judgmentResultService.getJudgmentResult(judgmentResultId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/api/judgment/submitted-code")
    public ResponseEntity<String> getSubmittedCode(@RequestParam int problemId, Long submissionId) {
        judgmentResultService.getSubmittedCode(problemId, submissionId);
        return ResponseEntity.ok("Judgment-Result saved successfully");
    }
}
