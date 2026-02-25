package com.project.skill_system.repository;

import com.project.skill_system.entites.Users
        ;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users, Long> {
}