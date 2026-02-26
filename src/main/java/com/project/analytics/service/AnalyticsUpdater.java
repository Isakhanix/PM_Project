package com.project.analytics.service;

import com.project.analytics.model.RegionSkillStats;
import com.project.analytics.repository.RegionSkillStatsRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Service
public class AnalyticsUpdater {

    private final RegionSkillStatsRepository statsRepository;

    public AnalyticsUpdater(RegionSkillStatsRepository statsRepository) {
        this.statsRepository = statsRepository;
    }

    @Transactional
    public RegionSkillStats updateForQuizSubmission(String h3RegionIndex, Long skillId, boolean correctAnswer) {
        RegionSkillStats stats = statsRepository
                .findByH3RegionIndexAndSkillId(h3RegionIndex, skillId)
                .orElseGet(() -> {
                    RegionSkillStats created = new RegionSkillStats();
                    created.setH3RegionIndex(h3RegionIndex);
                    created.setSkillId(skillId);
                    return created;
                });

        stats.setAttempts(stats.getAttempts() + 1);
        if (correctAnswer) {
            stats.setCorrect(stats.getCorrect() + 1);
        }
        stats.setUpdatedAt(Instant.now());

        return statsRepository.save(stats);
    }
}
