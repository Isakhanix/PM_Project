package com.project.analytics.controller;

import com.project.analytics.model.RegionSkillStats;
import com.project.analytics.repository.RegionSkillStatsRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/analytics")
public class AnalyticsController {

    private final RegionSkillStatsRepository repository;

    public AnalyticsController(RegionSkillStatsRepository repository) {
        this.repository = repository;
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('ANALYTICS_OFFICER')")
    @GetMapping("/region/{h3}")
    public List<RegionSkillStats> getByRegion(@PathVariable String h3) {
        return repository.findByH3RegionIndex(h3);
    }
}
