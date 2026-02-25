package com.project.assessment.controller;

public class QuizSubmitRequest {

    private Long userId;
    private Long skillId;
    private Integer score;
    private Integer maxScore;
    private Integer passingScore;
    private Double latitude;
    private Double longitude;
    private Integer h3Resolution;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getSkillId() {
        return skillId;
    }

    public void setSkillId(Long skillId) {
        this.skillId = skillId;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getMaxScore() {
        return maxScore;
    }

    public void setMaxScore(Integer maxScore) {
        this.maxScore = maxScore;
    }

    public Integer getPassingScore() {
        return passingScore;
    }

    public void setPassingScore(Integer passingScore) {
        this.passingScore = passingScore;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Integer getH3Resolution() {
        return h3Resolution;
    }

    public void setH3Resolution(Integer h3Resolution) {
        this.h3Resolution = h3Resolution;
    }

    public int resolvedH3Resolution() {
        return h3Resolution == null ? 9 : h3Resolution;
    }

    public int resolvedPassingScore() {
        if (passingScore != null) {
            return passingScore;
        }
        return maxScore == null ? 0 : maxScore;
    }
}
