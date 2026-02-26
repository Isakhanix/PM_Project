package com.project.analytics.repository;

import com.project.analytics.model.RegionSkillStats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface RegionSkillStatsRepository extends JpaRepository<RegionSkillStats, Long> {

    Optional<RegionSkillStats> findByH3RegionIndexAndSkillId(String h3RegionIndex, Long skillId);

    List<RegionSkillStats> findByH3RegionIndex(String h3RegionIndex);

    @Transactional
    void deleteByH3RegionIndex(String h3RegionIndex);

    @Modifying
    @Transactional
    @Query("delete from RegionSkillStats s where s.h3RegionIndex = :h3Index")
    void deleteByH3Index(@Param("h3Index") String h3Index);
}
