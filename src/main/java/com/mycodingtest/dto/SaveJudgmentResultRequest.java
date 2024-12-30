package com.mycodingtest.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public record SaveJudgmentResultRequest(
        String code,
        Long submissionId,
        String baekjoonId,
        int problemId,
        String problemTitle,
        String resultText,
        int memory,
        int time,
        String language,
        int codeLength,
        @JsonFormat(pattern = "yyyy년 MM월 dd일 HH:mm:ss")
        LocalDateTime submittedAt
) {
}



