package com.mycodingtest.service;

import com.mycodingtest.dto.MemoResponse;
import com.mycodingtest.dto.MemoSaveRequest;
import com.mycodingtest.dto.ReviewResponse;
import com.mycodingtest.dto.ReviewUpdateRequest;
import com.mycodingtest.dynamoDbBean.Memo;
import com.mycodingtest.entity.Review;
import com.mycodingtest.entity.SolvedProblem;
import com.mycodingtest.repository.ReviewRepository;
import com.mycodingtest.repository.SolvedProblemRepository;
import io.awspring.cloud.dynamodb.DynamoDbTemplate;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.enhanced.dynamodb.Key;

import java.util.Objects;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final SolvedProblemRepository solvedProblemRepository;
    private final DynamoDbTemplate dynamoDbTemplate;

    public ReviewService(ReviewRepository reviewRepository, SolvedProblemRepository solvedProblemRepository, DynamoDbTemplate dynamoDbTemplate) {
        this.reviewRepository = reviewRepository;
        this.solvedProblemRepository = solvedProblemRepository;
        this.dynamoDbTemplate = dynamoDbTemplate;
    }

    @Transactional
    public void updateReview(ReviewUpdateRequest request, Long reviewId) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new RuntimeException("Review not found"));
        review.setDifficultyLevel(request.difficultyLevel());
        review.setImportanceLevel(request.importanceLevel());

        solvedProblemRepository.findByReview(review)
                .ifPresent(SolvedProblem::setReviewedToTrue);
    }

    public ReviewResponse getReview(Long reviewId) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new RuntimeException("Review not found"));
        return new ReviewResponse(review.getDifficultyLevel(), review.getImportanceLevel(), review.getReviewedAt());
    }

    public void saveMemo(MemoSaveRequest request, Long reviewId) {
        dynamoDbTemplate.save(new Memo(request.content(), reviewId));
    }

    public MemoResponse getMemo(Long reviewId) {
        String memoContent = Objects.requireNonNull(dynamoDbTemplate.load(Key.builder().partitionValue(reviewId).build(), Memo.class)).getContent();
        return new MemoResponse(memoContent);
    }
}
