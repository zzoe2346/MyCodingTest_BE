package com.mycodingtest.dynamoDbBean;

import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbSortKey;

@DynamoDbBean
public class Code {

    Long userId;
    String problemSubmissionId;
    String content;

    public Code(Long userId, String problemSubmissionId, String content) {
        this.content = content;
        this.problemSubmissionId = problemSubmissionId;
        this.userId = userId;
    }

    public Code() {
    }

    public String getContent() {
        return content;
    }

    @DynamoDbSortKey
    public String getProblemSubmissionId() {
        return problemSubmissionId;
    }

    @DynamoDbPartitionKey
    public Long getUserId() {
        return userId;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setProblemSubmissionId(String problemSubmissionId) {
        this.problemSubmissionId = problemSubmissionId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return content;
    }
}
