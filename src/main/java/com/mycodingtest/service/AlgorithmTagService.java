package com.mycodingtest.service;

import com.mycodingtest.dto.AlgorithmTagResponse;
import com.mycodingtest.dto.AlgorithmTagSetRequest;
import com.mycodingtest.entity.AlgorithmTag;
import com.mycodingtest.entity.SolvedProblem;
import com.mycodingtest.repository.AlgorithmTagRepository;
import com.mycodingtest.repository.SolvedProblemRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class AlgorithmTagService {

    private final AlgorithmTagRepository algorithmTagRepository;
    private final SolvedProblemRepository solvedProblemRepository;

    public AlgorithmTagService(AlgorithmTagRepository algorithmTagRepository, SolvedProblemRepository solvedProblemRepository) {
        this.algorithmTagRepository = algorithmTagRepository;
        this.solvedProblemRepository = solvedProblemRepository;
    }

    @Transactional
    public void setAlgorithmTags(Long solvedProblemId, AlgorithmTagSetRequest request) {
        SolvedProblem solvedProblem = solvedProblemRepository.findById(solvedProblemId)
                .orElseThrow();

        Set<AlgorithmTag> tags = new HashSet<>(algorithmTagRepository.findAllById(List.of(request.tagIds())));
        solvedProblem.setAlgorithmTags(tags);
    }

    public AlgorithmTagResponse getAlgorithmTags(Long solvedProblemId) {
        return solvedProblemRepository.findById(solvedProblemId)
                .orElseThrow()
                .toAlgorithmTagResponse();
    }
}
