package com.project.skill_system.controller;

import com.project.skill_system.entites.Module;
import com.project.skill_system.entites.Skill;
import com.project.skill_system.repository.ModuleRepository;
import com.project.skill_system.repository.SkillRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/modules")
public class ModuleController {

    private final ModuleRepository moduleRepository;

    public ModuleController(ModuleRepository moduleRepository,
                            SkillRepository skillRepository) {
        this.moduleRepository = moduleRepository;
        this.skillRepository = skillRepository;
    }
    private final SkillRepository skillRepository;
    @PostMapping
    public Module createModule(
            @RequestParam(name = "title") String title,
            @RequestParam(name = "skillId") Long skillId
    ) {
        System.out.println("TITLE = " + title);
        System.out.println("SKILL ID = " + skillId);

        Skill skill = skillRepository.findById(skillId)
                .orElseThrow(() -> new RuntimeException("Skill not found"));

        Module module = new Module();
        module.setTitle(title);
        module.setSkill(skill);

        return moduleRepository.save(module);
    }

    @GetMapping
    public List<Module> getAllModules() {
        return moduleRepository.findAll();
    }
    @GetMapping("/{id}")
    public Module getModuleById(@PathVariable Long id) {
        return moduleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Module not found"));
    }
    @DeleteMapping("/{id}")
    public String deleteModule(@PathVariable Long id) {
        moduleRepository.deleteById(id);
        return "Module deleted successfully";
    }
    @PutMapping("/{id}")
    public Module updateModule(
            @PathVariable Long id,
            @RequestParam String title
    ) {
        Module module = moduleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Module not found"));

        module.setTitle(title);

        return moduleRepository.save(module);
    }
}