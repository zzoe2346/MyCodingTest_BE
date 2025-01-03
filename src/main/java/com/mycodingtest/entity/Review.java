package com.mycodingtest.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int difficultyLevel = -1;
    private int importanceLevel = -1;
    @UpdateTimestamp
    private LocalDateTime reviewedAt;
    @ManyToOne
    private User user;

    public Review(User user) {
        this.user = user;
    }

    protected Review() {
    }


    public Long getId() {
        return id;
    }

    public int getDifficultyLevel() {
        return difficultyLevel;
    }

    public void setDifficultyLevel(int difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
    }

    public int getImportanceLevel() {
        return importanceLevel;
    }

    public void setImportanceLevel(int importanceLevel) {
        this.importanceLevel = importanceLevel;
    }

    public LocalDateTime getReviewedAt() {
        return reviewedAt;
    }
}
