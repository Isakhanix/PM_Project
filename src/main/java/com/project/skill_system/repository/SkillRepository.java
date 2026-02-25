package com.project.skill_system.repository;

import com.project.skill_system.entites.Skill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SkillRepository extends JpaRepository<Skill, Long> {
}