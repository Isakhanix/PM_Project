package com.project.assessment.repository;

import com.project.assessment.model.QuizResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface QuizResultRepository extends JpaRepository<QuizResult, Long> {

    interface SkillRegionAggregate {
        Long getSkillId();
        Double getAvgScore();
        Long getSubmissionsCount();
    }

    @Query("""
            select q.skillId as skillId,
                   avg((1.0 * q.score) / q.maxScore) as avgScore,
                   count(q) as submissionsCount
            from QuizResult q
            where q.h3RegionIndex = :h3Index
            group by q.skillId
            """)
    List<SkillRegionAggregate> avgScoreBySkillForRegion(@Param("h3Index") String h3Index);
}

