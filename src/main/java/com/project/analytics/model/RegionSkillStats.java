package com.project.analytics.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

import java.time.Instant;

@Entity
@Table(
        name = "region_skill_stats",
        uniqueConstraints = @UniqueConstraint(columnNames = {"h3_region_index", "skill_id"})
)
public class RegionSkillStats {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "h3_region_index", nullable = false)
    private String h3RegionIndex;

    @Column(name = "skill_id", nullable = false)
    private Long skillId;

    @Column(nullable = false)
    private long attempts;

    @Column(nullable = false)
    private long correct;

    @Column(nullable = false)
    private Instant updatedAt = Instant.now();

    public Long getId() {
        return id;
    }

    public String getH3RegionIndex() {
        return h3RegionIndex;
    }

    public void setH3RegionIndex(String h3RegionIndex) {
        this.h3RegionIndex = h3RegionIndex;
    }

    public Long getSkillId() {
        return skillId;
    }

    public void setSkillId(Long skillId) {
        this.skillId = skillId;
    }

    public long getAttempts() {
        return attempts;
    }

    public void setAttempts(long attempts) {
        this.attempts = attempts;
    }

    public long getCorrect() {
        return correct;
    }

    public void setCorrect(long correct) {
        this.correct = correct;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }
}
