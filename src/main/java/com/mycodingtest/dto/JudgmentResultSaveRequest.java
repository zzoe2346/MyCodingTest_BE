package com.mycodingtest.dto;

import java.time.LocalDateTime;

public record JudgmentResultSaveRequest(
        String code,
        Long submissionId,
        String baekjoonId,
        int problemNumber,
        String problemTitle,
        String resultText,
        int memory,
        int time,
        String language,
        int codeLength,
        LocalDateTime submittedAt
) {
}
