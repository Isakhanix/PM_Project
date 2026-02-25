package com.project.assessment.event;

import com.project.analytics.service.AnalyticsUpdater;
import org.springframework.stereotype.Component;

@Component
public class EventListener {

    private final AnalyticsUpdater analyticsUpdater;

    public EventListener(AnalyticsUpdater analyticsUpdater) {
        this.analyticsUpdater = analyticsUpdater;
    }

    @org.springframework.context.event.EventListener
    public void onQuizSubmitted(QuizSubmittedEvent event) {
        analyticsUpdater.updateForQuizSubmission(event.h3RegionIndex(), event.skillId(), event.correct());
    }
}
