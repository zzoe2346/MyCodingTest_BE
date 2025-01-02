package com.mycodingtest.repository;

import com.mycodingtest.entity.JudgmentResult;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JudgmentResultRepository extends JpaRepository<JudgmentResult, Long> {
    List<JudgmentResult> findBySolvedProblemId(Long solveProblemId);
}

