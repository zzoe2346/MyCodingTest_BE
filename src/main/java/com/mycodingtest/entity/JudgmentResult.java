package com.mycodingtest.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class JudgmentResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private Long submissionId;
    @Column(nullable = false)
    private String baekjoonId;
    @Column(nullable = false)
    private int problemId;
    @Column(nullable = false)
    private String resultText;
    @Column(nullable = false)
    private int memory;
    @Column(nullable = false)
    private int time;
    @Column(nullable = false)
    private String language;
    @Column(nullable = false)
    private int codeLength;
    @Column(nullable = false)
    private LocalDateTime submittedAt;
    @ManyToOne
    @JoinColumn(nullable = false)
    private User user;
    @ManyToOne
    @JoinColumn(nullable = false)
    private SolvedProblem solvedProblem;

    public JudgmentResult(String baekjoonId, int codeLength, Long id, String language, int memory, int problemId, String resultText, Long submissionId, LocalDateTime submittedAt, int time, User user, SolvedProblem solvedProblem) {
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
        this.user = user;
        this.solvedProblem = solvedProblem;
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
