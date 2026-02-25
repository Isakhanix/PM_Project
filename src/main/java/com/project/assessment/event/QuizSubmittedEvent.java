package com.project.assessment.event;

public record QuizSubmittedEvent(String h3RegionIndex, Long skillId, boolean correct) {
}
