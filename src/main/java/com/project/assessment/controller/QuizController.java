package com.project.assessment.controller;

import com.project.assessment.event.QuizEventPublisher;
import com.project.assessment.model.QuizResult;
import com.project.assessment.repository.QuizResultRepository;
import com.project.security.config.common.util.H3Util;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/quizzes")
public class QuizController {

    private final QuizResultRepository quizResultRepository;
    private final QuizEventPublisher eventPublisher;
    private final H3Util h3Util;

    public QuizController(QuizResultRepository quizResultRepository,
                          QuizEventPublisher eventPublisher,
                          H3Util h3Util) {
        this.quizResultRepository = quizResultRepository;
        this.eventPublisher = eventPublisher;
        this.h3Util = h3Util;
    }

    @PostMapping("/submit")
    @ResponseStatus(HttpStatus.CREATED)
    public QuizResult submit(@RequestBody QuizSubmissionRequest request) {
        String h3 = h3Util.latLngToCell(request.latitude(), request.longitude(), request.resolvedH3Resolution());
        boolean correct = request.score() >= request.resolvedPassingScore();

        QuizResult result = new QuizResult();
        result.setUserId(request.userId());
        result.setSkillId(request.skillId());
        result.setScore(request.score());
        result.setMaxScore(request.maxScore());
        result.setH3RegionIndex(h3);

        QuizResult saved = quizResultRepository.save(result);
        eventPublisher.publishQuizSubmitted(h3, request.skillId(), correct);

        return saved;
    }

    public record QuizSubmissionRequest(
            Long userId,
            Long skillId,
            Integer score,
            Integer maxScore,
            Integer passingScore,
            Double latitude,
            Double longitude,
            Integer h3Resolution
    ) {
        public int resolvedH3Resolution() {
            return h3Resolution == null ? 9 : h3Resolution;
        }

        public int resolvedPassingScore() {
            return passingScore == null ? maxScore : passingScore;
        }
    }
}

