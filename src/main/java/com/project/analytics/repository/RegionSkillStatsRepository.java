package com.project.analytics.repository;

import com.project.analytics.model.RegionSkillStats;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RegionSkillStatsRepository extends JpaRepository<RegionSkillStats, Long> {
    Optional<RegionSkillStats> findByH3RegionIndexAndSkillId(String h3RegionIndex, Long skillId);
    List<RegionSkillStats> findByH3RegionIndex(String h3RegionIndex);
}
