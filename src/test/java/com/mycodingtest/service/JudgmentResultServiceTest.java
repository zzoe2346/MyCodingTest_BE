package com.mycodingtest.service;

import com.mycodingtest.dto.JudgmentResultSaveRequest;
import com.mycodingtest.dynamoDbBean.Code;
import com.mycodingtest.entity.JudgmentResult;
import com.mycodingtest.entity.Review;
import com.mycodingtest.entity.SolvedProblem;
import com.mycodingtest.entity.User;
import com.mycodingtest.repository.JudgmentResultRepository;
import com.mycodingtest.repository.SolvedProblemRepository;
import com.mycodingtest.repository.UserRepository;
import io.awspring.cloud.dynamodb.DynamoDbTemplate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoSettings;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.mockito.Mockito.*;

@MockitoSettings
class JudgmentResultServiceTest {

    @Mock
    private DynamoDbTemplate dynamoDbTemplate;

    @Mock
    private JudgmentResultRepository judgmentResultRepository;

    @Mock
    private SolvedProblemRepository solvedProblemRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private JudgmentResultService judgmentResultService;

    @Test
    @DisplayName("채점 결과 저장할 때 유저가 존재, 푼 문제가 이미 저장된 경우 성공 저장이 됨")
    void saveJudgmentResult_whenUserExistsAndSolvedProblemExists_thenSaveSuccessfully() {
        // Arrange
        Long userId = 1L;
        LocalDateTime submittedAt = LocalDateTime.now();
        JudgmentResultSaveRequest request = new JudgmentResultSaveRequest(
                "baekjoonUser123",
                100L,
                "Java",
                1234,
                "50ms",
                "맞았습니다!!",
                234,
                100,
                "java",
                100,
                submittedAt
        );

        User mockUser = mock(User.class);
        mockUser.setId(userId);

        SolvedProblem mockSolvedProblem = new SolvedProblem(
                request.problemNumber(),
                request.problemTitle(),
                mockUser,
                new Review(mockUser)
        );

        when(userRepository.findById(userId)).thenReturn(Optional.of(mockUser));
        when(solvedProblemRepository.findByUserIdAndProblemNumber(userId, request.problemNumber()))
                .thenReturn(Optional.of(mockSolvedProblem));
        when(solvedProblemRepository.save(any(SolvedProblem.class))).thenReturn(mockSolvedProblem);

        JudgmentResult mockJudgmentResult = new JudgmentResult(
                request.baekjoonId(),
                request.codeLength(),
                request.submissionId(),
                request.language(),
                request.memory(),
                request.problemNumber(),
                request.resultText(),
                request.submissionId(),
                request.submittedAt(),
                request.time(),
                mockUser,
                mockSolvedProblem
        );

        when(judgmentResultRepository.save(any(JudgmentResult.class)))
                .thenReturn(mockJudgmentResult);

        // Act
        judgmentResultService.saveJudgmentResult(request, userId);

        // Assert
        verify(solvedProblemRepository, times(1)).save(any(SolvedProblem.class));
        verify(judgmentResultRepository, times(1)).save(any(JudgmentResult.class));
        verify(dynamoDbTemplate, times(1)).save(any(Code.class));
    }

    @Test
    @DisplayName("채점 결과 저장할 때 유저가 존재, 처음 푼 문제라 푼 문제로 저장이 안 된 경우 새로 SolvedProblem Entity 생성후 이를 활용해서 저장이 됨")
    void saveJudgmentResult_whenUserExistsButSolvedProblemNotExists_thenSaveSuccessfully() {
        // Arrange
        Long userId = 1L;
        LocalDateTime submittedAt = LocalDateTime.now();
        JudgmentResultSaveRequest request = new JudgmentResultSaveRequest(
                "baekjoonUser123",
                100L,
                "Java",
                1234,
                "50ms",
                "맞았습니다!!",
                234,
                100,
                "java",
                100,
                submittedAt
        );

        User mockUser = mock(User.class);
        mockUser.setId(userId);

        SolvedProblem mockSolvedProblem = new SolvedProblem(
                request.problemNumber(),
                request.problemTitle(),
                mockUser,
                new Review(mockUser)
        );

        when(userRepository.findById(userId)).thenReturn(Optional.of(mockUser));
        when(solvedProblemRepository.findByUserIdAndProblemNumber(userId, request.problemNumber()))
                .thenReturn(Optional.empty());
        when(solvedProblemRepository.save(any(SolvedProblem.class))).thenReturn(mockSolvedProblem);

        JudgmentResult mockJudgmentResult = new JudgmentResult(
                request.baekjoonId(),
                request.codeLength(),
                request.submissionId(),
                request.language(),
                request.memory(),
                request.problemNumber(),
                request.resultText(),
                request.submissionId(),
                request.submittedAt(),
                request.time(),
                mockUser,
                mockSolvedProblem
        );

        when(judgmentResultRepository.save(any(JudgmentResult.class)))
                .thenReturn(mockJudgmentResult);

        // Act
        judgmentResultService.saveJudgmentResult(request, userId);

        // Assert
        verify(solvedProblemRepository, times(1)).save(any(SolvedProblem.class));
        verify(judgmentResultRepository, times(1)).save(any(JudgmentResult.class));
        verify(dynamoDbTemplate, times(1)).save(any(Code.class));
    }
}
