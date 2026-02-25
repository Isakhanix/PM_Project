package com.project.skill_system.repository;

import com.project.skill_system.entites.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<Users, Long> {
    List<Users> findByH3RegionIndex(String h3RegionIndex);
}
