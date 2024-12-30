package com.mycodingtest.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import java.time.LocalDateTime;

@Entity
public class JudgmentResult {

    @Id
    private Long id;
    private Long submissionId;
    private String baekjoonId;
    private int problemId;
    private String resultText;
    private int memory;
    private int time;
    private String language;
    private int codeLength;
    private LocalDateTime submittedAt;
    @ManyToOne
    private User user;
    @ManyToOne
    private ProblemSolvingStatus problemSolvingStatus;

    public JudgmentResult(String baekjoonId, int codeLength, Long id, String language, int memory, int problemId, String resultText, Long submissionId, LocalDateTime submittedAt, int time) {
        this.baekjoonId = baekjoonId;
        this.codeLength = codeLength;
        this.id = id;
        this.language = language;
        this.memory = memory;
        this.problemId = problemId;
        this.resultText = resultText;
        this.submissionId = submissionId;
        this.submittedAt = submittedAt;
        this.time = time;
    }

    protected JudgmentResult() {
    }

    public String getBaekjoonId() {
        return baekjoonId;
    }

    public int getCodeLength() {
        return codeLength;
    }

    public Long getId() {
        return id;
    }

    public String getLanguage() {
        return language;
    }

    public int getMemory() {
        return memory;
    }

    public int getProblemId() {
        return problemId;
    }

    public String getResultText() {
        return resultText;
    }

    public Long getSubmissionId() {
        return submissionId;
    }

    public LocalDateTime getSubmittedAt() {
        return submittedAt;
    }

    public int getTime() {
        return time;
    }
}
