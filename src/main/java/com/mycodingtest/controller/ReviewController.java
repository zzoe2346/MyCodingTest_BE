package com.mycodingtest.controller;

import com.mycodingtest.dto.MemoResponse;
import com.mycodingtest.dto.MemoSaveRequest;
import com.mycodingtest.dto.ReviewResponse;
import com.mycodingtest.dto.ReviewUpdateRequest;
import com.mycodingtest.service.ReviewService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PutMapping("/api/review/{reviewId}")
    public ResponseEntity<Void> updateReview(@RequestBody ReviewUpdateRequest request,
                                             @PathVariable Long reviewId) {
        reviewService.updateReview(request, reviewId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/api/review/{reviewId}")
    public ResponseEntity<ReviewResponse> getReview(@PathVariable Long reviewId) {
        return ResponseEntity.ok(reviewService.getReview(reviewId));
    }

    @PostMapping("/api/review/{reviewId}/memo")
    public void saveMemo(@RequestBody MemoSaveRequest request,
                         @PathVariable Long reviewId) {
        reviewService.saveMemo(request, reviewId);
    }

    @GetMapping("/api/review/{reviewId}/memo")
    public ResponseEntity<MemoResponse> getMemo(@PathVariable Long reviewId) {
        return ResponseEntity.ok(reviewService.getMemo(reviewId));
    }
}
