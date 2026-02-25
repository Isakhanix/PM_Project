package com.project.assessment.repository;

import com.project.assessment.model.QuizResult;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizResultRepository extends JpaRepository<QuizResult, Long> {
}
