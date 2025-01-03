package com.mycodingtest.entity;

import com.mycodingtest.dto.AlgorithmTagResponse;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
public class SolvedProblem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int problemNumber;
    private String problemTitle;
    @ManyToOne
    private User user;
    @OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private Review review;
    private LocalDateTime recentSubmitAt;
    private boolean isReviewed = false;
    private String recentResultText;
    private boolean isFavorite = false;
    @ManyToMany
    @JoinTable(
            name = "solvedProblem_algorithmTag",
            joinColumns = @JoinColumn(name = "solvedProblem_id"),
            inverseJoinColumns = @JoinColumn(name = "algorithmTag_id")
    )
    private Set<AlgorithmTag> algorithmTags = new HashSet<>();

    public void setAlgorithmTags(Set<AlgorithmTag> algorithmTags) {
        this.algorithmTags = algorithmTags;
    }

    public AlgorithmTagResponse toAlgorithmTagResponse() {
        return new AlgorithmTagResponse(algorithmTags.stream()
                .map(AlgorithmTag::getName)
                .toArray(String[]::new));
    }

    public String[] getAlgorithmTagsToStrArray() {
        return algorithmTags.stream()
                .map(AlgorithmTag::getName)
                .toArray(String[]::new);
    }

    public SolvedProblem(int problemNumber, String problemTitle, User user, Review review) {
        this.problemNumber = problemNumber;
        this.problemTitle = problemTitle;
        this.user = user;
        this.review = review;
    }

    protected SolvedProblem() {
    }

    public void setReviewedToTrue() {
        this.isReviewed = true;
    }

    public void setRecentResultText(String recentResultText) {
        this.recentResultText = recentResultText;
    }

    public void setRecentSubmitAt(LocalDateTime recentSubmitAt) {
        this.recentSubmitAt = recentSubmitAt;
    }

    public Long getId() {
        return id;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public boolean isReviewed() {
        return isReviewed;
    }

    public int getProblemNumber() {
        return problemNumber;
    }

    public String getProblemTitle() {
        return problemTitle;
    }

    public String getRecentResultText() {
        return recentResultText;
    }

    public LocalDateTime getRecentSubmitAt() {
        return recentSubmitAt;
    }

    public Review getReview() {
        return review;
    }


}
