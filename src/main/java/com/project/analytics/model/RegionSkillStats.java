package com.project.analytics.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.Instant;

@Entity
@Table(name = "region_skill_stats")
public class RegionSkillStats {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String h3RegionIndex;

    @Column(nullable = false)
    private Long skillId;

    @Column(nullable = false)
    private Integer attempts = 0;

    @Column(nullable = false)
    private Integer correct = 0;

    @Column(nullable = false)
    private Instant updatedAt = Instant.now();

    private transient Double stagedAvgScore;
    private transient Long stagedSubmissionsCount;

    public Long getId() {
        return id;
    }

    public String getH3RegionIndex() {
        return h3RegionIndex;
    }

    public String getH3Index() {
        return h3RegionIndex;
    }

    public void setH3RegionIndex(String h3RegionIndex) {
        this.h3RegionIndex = h3RegionIndex;
    }

    public void setH3Index(String h3Index) {
        this.h3RegionIndex = h3Index;
    }

    public Long getSkillId() {
        return skillId;
    }

    public void setSkillId(Long skillId) {
        this.skillId = skillId;
    }

    public Integer getAttempts() {
        return attempts;
    }

    public void setAttempts(Integer attempts) {
        this.attempts = attempts;
    }

    public Integer getCorrect() {
        return correct;
    }

    public void setCorrect(Integer correct) {
        this.correct = correct;
    }

    public Double getAvgScore() {
        if (attempts == null || attempts == 0) {
            return 0.0;
        }
        return (double) correct / attempts;
    }

    public void setAvgScore(Double avgScore) {
        this.stagedAvgScore = avgScore == null ? 0.0 : avgScore;
        applyStagedAggregates();
    }

    public Long getSubmissionsCount() {
        return attempts == null ? 0L : attempts.longValue();
    }

    public void setSubmissionsCount(Long submissionsCount) {
        this.stagedSubmissionsCount = submissionsCount == null ? 0L : submissionsCount;
        applyStagedAggregates();
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }

    private void applyStagedAggregates() {
        if (stagedAvgScore == null || stagedSubmissionsCount == null) {
            return;
        }

        this.attempts = Math.toIntExact(stagedSubmissionsCount);
        this.correct = (int) Math.round(stagedAvgScore * stagedSubmissionsCount);
    }
}
