package com.project.assessment.controller;

import com.project.assessment.model.QuizResult;

import java.time.Instant;

public class QuizResultResponse {

    private final Long resultId;
    private final Long quizId;
    private final Long userId;
    private final Long skillId;
    private final Integer score;
    private final Integer maxScore;
    private final Integer passingScore;
    private final boolean passed;
    private final String h3RegionIndex;
    private final Instant submittedAt;
    private final String submittedBy;

    public QuizResultResponse(Long resultId,
                              Long quizId,
                              Long userId,
                              Long skillId,
                              Integer score,
                              Integer maxScore,
                              Integer passingScore,
                              boolean passed,
                              String h3RegionIndex,
                              Instant submittedAt,
                              String submittedBy) {
        this.resultId = resultId;
        this.quizId = quizId;
        this.userId = userId;
        this.skillId = skillId;
        this.score = score;
        this.maxScore = maxScore;
        this.passingScore = passingScore;
        this.passed = passed;
        this.h3RegionIndex = h3RegionIndex;
        this.submittedAt = submittedAt;
        this.submittedBy = submittedBy;
    }

    public static QuizResultResponse from(Long quizId,
                                          QuizResult result,
                                          int passingScore,
                                          boolean passed,
                                          String submittedBy) {
        return new QuizResultResponse(
                result.getId(),
                quizId,
                result.getUserId(),
                result.getSkillId(),
                result.getScore(),
                result.getMaxScore(),
                passingScore,
                passed,
                result.getH3RegionIndex(),
                result.getSubmittedAt(),
                submittedBy
        );
    }

    public Long getResultId() {
        return resultId;
    }

    public Long getQuizId() {
        return quizId;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getSkillId() {
        return skillId;
    }

    public Integer getScore() {
        return score;
    }

    public Integer getMaxScore() {
        return maxScore;
    }

    public Integer getPassingScore() {
        return passingScore;
    }

    public boolean isPassed() {
        return passed;
    }

    public String getH3RegionIndex() {
        return h3RegionIndex;
    }

    public Instant getSubmittedAt() {
        return submittedAt;
    }

    public String getSubmittedBy() {
        return submittedBy;
    }
}
