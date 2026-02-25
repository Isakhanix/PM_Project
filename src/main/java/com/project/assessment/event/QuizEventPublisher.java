package com.project.assessment.event;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class QuizEventPublisher {

    private final ApplicationEventPublisher publisher;

    public QuizEventPublisher(ApplicationEventPublisher publisher) {
        this.publisher = publisher;
    }

    public void publishQuizSubmitted(String h3RegionIndex, Long skillId, boolean correct) {
        publisher.publishEvent(new QuizSubmittedEvent(h3RegionIndex, skillId, correct));
    }
}
