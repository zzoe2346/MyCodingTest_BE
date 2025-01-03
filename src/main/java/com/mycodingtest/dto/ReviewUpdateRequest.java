package com.mycodingtest.dto;

public record ReviewUpdateRequest(
        int difficultyLevel,
        int importanceLevel
) {
}
