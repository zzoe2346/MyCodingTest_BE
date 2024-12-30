package com.mycodingtest.repository;

import com.mycodingtest.entity.ProblemSolvingStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProblemSolvingStatusRepository extends JpaRepository<ProblemSolvingStatus, Long> {
    Optional<ProblemSolvingStatus> findByProblemIdAndUserId(int problemId, Long userId);
}
