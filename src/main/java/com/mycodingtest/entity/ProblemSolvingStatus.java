package com.mycodingtest.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class ProblemSolvingStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int problemId;
    private String problemTitle;
    @ManyToOne
    private User user;
    private LocalDateTime recentSubmitAt;
    private boolean isReviewed = false;
    private String recentResultText;

    public ProblemSolvingStatus(User user, int problemId, String problemTitle) {
        this.user = user;
        this.problemId = problemId;
        this.problemTitle = problemTitle;
    }

    protected ProblemSolvingStatus() {
    }

    public void setProblemId(int problemId) {
        this.problemId = problemId;
    }

    public void setProblemTitle(String problemTitle) {
        this.problemTitle = problemTitle;
    }

    public void setRecentResultText(String recentResultText) {
        this.recentResultText = recentResultText;
    }

    public void setRecentSubmitAt(LocalDateTime recentSubmitAt) {
        this.recentSubmitAt = recentSubmitAt;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
