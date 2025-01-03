package com.mycodingtest.service;

import com.mycodingtest.dto.JudgmentResultResponse;
import com.mycodingtest.dto.JudgmentResultSaveRequest;
import com.mycodingtest.dynamoDbBean.Code;
import com.mycodingtest.entity.JudgmentResult;
import com.mycodingtest.entity.Review;
import com.mycodingtest.entity.SolvedProblem;
import com.mycodingtest.entity.User;
import com.mycodingtest.repository.JudgmentResultRepository;
import com.mycodingtest.repository.SolvedProblemRepository;
import com.mycodingtest.repository.UserRepository;
import com.mycodingtest.util.SecurityUtil;
import io.awspring.cloud.dynamodb.DynamoDbTemplate;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import software.amazon.awssdk.enhanced.dynamodb.Key;

import java.util.List;
import java.util.Objects;

@Service
public class JudgmentResultService {

    private final DynamoDbTemplate dynamoDbTemplate;
    private final JudgmentResultRepository judgmentResultRepository;
    private final SolvedProblemRepository solvedProblemRepository;
    private final UserRepository userRepository;

    public JudgmentResultService(DynamoDbTemplate dynamoDbTemplate, JudgmentResultRepository judgmentResultRepository, SolvedProblemRepository solvedProblemRepository, UserRepository userRepository) {
        this.solvedProblemRepository = solvedProblemRepository;
        this.dynamoDbTemplate = dynamoDbTemplate;
        this.judgmentResultRepository = judgmentResultRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public void saveJudgmentResult(JudgmentResultSaveRequest request) {
        Long userId = SecurityUtil.getCurrentUserId();
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        SolvedProblem solvedProblem = solvedProblemRepository.findByUserIdAndProblemNumber(userId, request.problemNumber())
                .orElse(new SolvedProblem(request.problemNumber(), request.problemTitle(), user, new Review(user)));

        solvedProblem.setRecentSubmitAt(request.submittedAt());
        solvedProblem.setRecentResultText(request.resultText());

        Long solvedProblemId = solvedProblemRepository.save(solvedProblem).getId();
        Long judgmentId = judgmentResultRepository.save(new JudgmentResult(request.baekjoonId(), request.codeLength(), request.language(), request.memory(), request.problemNumber(), request.resultText(), request.submissionId(), request.submittedAt(), request.time(), user, solvedProblem)).getId();
        dynamoDbTemplate.save(new Code(solvedProblemId, judgmentId, request.code()));
    }

    public String getSubmittedCode(Long solvedProblem, Long judgmentId) {
        return Objects.requireNonNull(dynamoDbTemplate.load(Key.builder().partitionValue(solvedProblem).sortValue(judgmentId).build(), Code.class)).getContent();
    }

    @Transactional(readOnly = true)
    public List<JudgmentResultResponse> getJudgmentResultList(Long solvedProblemId) {
        return judgmentResultRepository.findBySolvedProblemId(solvedProblemId).stream()
                .map(judgmentResult -> new JudgmentResultResponse(judgmentResult.getSubmissionId(), judgmentResult.getBaekjoonId(), judgmentResult.getProblemId(), judgmentResult.getResultText(), judgmentResult.getMemory(), judgmentResult.getTime(), judgmentResult.getLanguage(), judgmentResult.getCodeLength(), judgmentResult.getSubmittedAt()))
                .toList();
    }
}
