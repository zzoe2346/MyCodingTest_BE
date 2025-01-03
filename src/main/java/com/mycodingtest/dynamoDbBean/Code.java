package com.mycodingtest.dynamoDbBean;

import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbSortKey;

@DynamoDbBean
public class Code {
    Long solveProblemId;
    Long judgmentResultId;
    String content;

    public Code(Long solveProblemId, Long judgmentResultId, String content) {
        this.content = content;
        this.judgmentResultId = judgmentResultId;
        this.solveProblemId = solveProblemId;
    }

    public Code() {
    }

    @DynamoDbPartitionKey
    public Long getSolveProblemId() {
        return solveProblemId;
    }

    @DynamoDbSortKey
    public Long getJudgmentResultId() {
        return judgmentResultId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setJudgmentResultId(Long judgmentResultId) {
        this.judgmentResultId = judgmentResultId;
    }

    public void setSolveProblemId(Long solveProblemId) {
        this.solveProblemId = solveProblemId;
    }
}
