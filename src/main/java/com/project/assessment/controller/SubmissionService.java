package com.project.assessment.controller;

import com.project.assessment.event.QuizEventPublisher;
import com.project.assessment.model.QuizResult;
import com.project.assessment.repository.QuizResultRepository;
import com.project.security.config.common.util.H3Util;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
public class SubmissionService {

    private final QuizResultRepository quizResultRepository;
    private final QuizEventPublisher eventPublisher;
    private final H3Util h3Util;

    public SubmissionService(QuizResultRepository quizResultRepository,
                             QuizEventPublisher eventPublisher,
                             H3Util h3Util) {
        this.quizResultRepository = quizResultRepository;
        this.eventPublisher = eventPublisher;
        this.h3Util = h3Util;
    }

    public QuizResultResponse submit(Long quizId, QuizSubmitRequest request, String submittedBy) {
        Assert.notNull(quizId, "quizId must not be null");
        Assert.notNull(request, "request must not be null");
        Assert.notNull(request.getUserId(), "userId must not be null");
        Assert.notNull(request.getScore(), "score must not be null");
        Assert.notNull(request.getMaxScore(), "maxScore must not be null");
        Assert.notNull(request.getLatitude(), "latitude must not be null");
        Assert.notNull(request.getLongitude(), "longitude must not be null");

        Long skillId = request.getSkillId() != null ? request.getSkillId() : quizId;
        int passingScore = request.resolvedPassingScore();
        boolean passed = request.getScore() >= passingScore;

        String h3 = h3Util.latLngToCell(
                request.getLatitude(),
                request.getLongitude(),
                request.resolvedH3Resolution()
        );

        QuizResult result = new QuizResult();
        result.setUserId(request.getUserId());
        result.setSkillId(skillId);
        result.setScore(request.getScore());
        result.setMaxScore(request.getMaxScore());
        result.setH3RegionIndex(h3);

        QuizResult saved = quizResultRepository.save(result);
        eventPublisher.publishQuizSubmitted(h3, skillId, passed);

        return QuizResultResponse.from(quizId, saved, passingScore, passed, submittedBy);
    }
}
