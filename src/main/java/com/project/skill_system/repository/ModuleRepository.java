package com.project.skill_system.repository;

import com.project.skill_system.entites.Module;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModuleRepository extends JpaRepository<Module, Long> {
}