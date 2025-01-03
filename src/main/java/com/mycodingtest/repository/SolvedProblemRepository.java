package com.mycodingtest.repository;

import com.mycodingtest.entity.Review;
import com.mycodingtest.entity.SolvedProblem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SolvedProblemRepository extends JpaRepository<SolvedProblem, Long> {
    Optional<SolvedProblem> findByUserIdAndProblemNumber(Long userId, int problemNumber);

    Optional<SolvedProblem> findByReview(Review review);

    Page<SolvedProblem> findByUserId(Long userId, Pageable pageable);
}
