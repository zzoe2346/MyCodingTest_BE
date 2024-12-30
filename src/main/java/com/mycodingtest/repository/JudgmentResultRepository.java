package com.mycodingtest.repository;

import com.mycodingtest.entity.JudgmentResult;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JudgmentResultRepository extends JpaRepository<JudgmentResult, Long> {
    Page<JudgmentResult> findJudgmentResultsByUserId(Long userId, Pageable pageable);
}

