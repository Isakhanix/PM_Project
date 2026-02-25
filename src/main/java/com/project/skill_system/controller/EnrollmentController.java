package com.project.skill_system.controller;

import com.project.skill_system.entites.*;
import com.project.skill_system.entites.Module;
import com.project.skill_system.repository.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/enrollments")
public class EnrollmentController {

    private final EnrollmentRepository enrollmentRepository;
    private final UserRepository userRepository;
    private final ModuleRepository moduleRepository;

    public EnrollmentController(EnrollmentRepository enrollmentRepository,
                                UserRepository userRepository,
                                ModuleRepository moduleRepository) {
        this.enrollmentRepository = enrollmentRepository;
        this.userRepository = userRepository;
        this.moduleRepository = moduleRepository;
    }

    @PostMapping
    public Enrollment enrollUser(
            @RequestParam Long userId,
            @RequestParam Long moduleId
    ) {
        Users user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Module module = moduleRepository.findById(moduleId)
                .orElseThrow(() -> new RuntimeException("Module not found"));

        Enrollment enrollment = new Enrollment(user, module);

        return enrollmentRepository.save(enrollment);
    }

    @GetMapping
    public List<Enrollment> getAllEnrollments() {
        return enrollmentRepository.findAll();
    }
}