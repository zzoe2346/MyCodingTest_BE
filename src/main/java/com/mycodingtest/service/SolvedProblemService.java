package com.mycodingtest.service;

import com.mycodingtest.dto.SolvedProblemWithReviewResponse;
import com.mycodingtest.repository.SolvedProblemRepository;
import com.mycodingtest.util.SecurityUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class SolvedProblemService {

    private final SolvedProblemRepository solvedProblemRepository;

    public SolvedProblemService(SolvedProblemRepository solvedProblemRepository) {
        this.solvedProblemRepository = solvedProblemRepository;
    }

    public Page<SolvedProblemWithReviewResponse> getSolvedProblemWithRiviewPage(Pageable pageable) {
        Long userId = SecurityUtil.getCurrentUserId();
        return solvedProblemRepository.findByUserId(userId, pageable)
                .map((solvedProblem) -> new SolvedProblemWithReviewResponse(solvedProblem.getId(), solvedProblem.getProblemNumber(), solvedProblem.getProblemTitle(), solvedProblem.getRecentSubmitAt(), solvedProblem.getRecentResultText(), solvedProblem.isFavorite(), solvedProblem.getReview().getId(), solvedProblem.getReview().getDifficultyLevel(), solvedProblem.getReview().getImportanceLevel(), solvedProblem.getAlgorithmTagsToStrArray(), solvedProblem.isReviewed()));
    }
}
