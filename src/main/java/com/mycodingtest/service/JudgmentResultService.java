package com.mycodingtest.service;

import com.mycodingtest.dto.JudgmentResultResponse;
import com.mycodingtest.dto.SaveJudgmentResultRequest;
import com.mycodingtest.dynamoDbBean.Code;
import com.mycodingtest.entity.JudgmentResult;
import com.mycodingtest.entity.ProblemSolvingStatus;
import com.mycodingtest.entity.User;
import com.mycodingtest.repository.JudgmentResultRepository;
import com.mycodingtest.repository.ProblemSolvingStatusRepository;
import io.awspring.cloud.dynamodb.DynamoDbTemplate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.enhanced.dynamodb.Key;

@Service
public class JudgmentResultService {

    private final DynamoDbTemplate dynamoDbTemplate;
    private final JudgmentResultRepository judgmentResultRepository;
    private final ProblemSolvingStatusRepository problemSolvingStatusRepository;
    static final Long userId = 1L; // for dev
    static final User user = new User(); // for dev

    public JudgmentResultService(DynamoDbTemplate dynamoDbTemplate, JudgmentResultRepository judgmentResultRepository, ProblemSolvingStatusRepository problemSolvingStatusRepository) {
        this.problemSolvingStatusRepository = problemSolvingStatusRepository;
        this.dynamoDbTemplate = dynamoDbTemplate;
        this.judgmentResultRepository = judgmentResultRepository;
    }

    public void saveJudgmentResult(SaveJudgmentResultRequest request) {
        ProblemSolvingStatus problemSolvingStatus = problemSolvingStatusRepository.findByProblemIdAndUserId(request.problemId(), userId)
                .orElse(new ProblemSolvingStatus(user, request.problemId(), request.problemTitle()));

        problemSolvingStatus.setRecentSubmitAt(request.submittedAt());
        problemSolvingStatus.setRecentResultText(request.resultText());

        problemSolvingStatusRepository.save(problemSolvingStatus);
        dynamoDbTemplate.save(new Code(userId, request.problemId() + "#" + request.submissionId(), request.code()));
        judgmentResultRepository.save(new JudgmentResult(request.baekjoonId(), request.codeLength(), request.submissionId(), request.language(), request.memory(), request.problemId(), request.resultText(), request.submissionId(), request.submittedAt(), request.time()));
    }

    public String getSubmittedCode(int problemId, Long submissionId) {
        return dynamoDbTemplate.load(Key.builder().partitionValue(userId).sortValue(problemId + "#" + submissionId).build(), Code.class).toString();
    }

    public JudgmentResultResponse getJudgmentResult(Long judgmentResultId) {
        JudgmentResult judgmentResult = judgmentResultRepository.findById(judgmentResultId)
                .orElseThrow(() -> new RuntimeException("JudgmentResult not found"));

        return new JudgmentResultResponse(judgmentResult.getSubmissionId(), judgmentResult.getBaekjoonId(), judgmentResult.getProblemId(), judgmentResult.getResultText(), judgmentResult.getMemory(), judgmentResult.getTime(), judgmentResult.getLanguage(), judgmentResult.getCodeLength(), judgmentResult.getSubmittedAt());
    }

    public Page<JudgmentResult> getJudgmentResultPagenationByUserId(Long userId, Pageable pageable) {
        return judgmentResultRepository.findJudgmentResultsByUserId(userId, pageable);
    }
}
