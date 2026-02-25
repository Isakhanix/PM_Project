package com.project.skill_system.entites;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "modules")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Module {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;


    public void setTitle(String title) {
        this.title = title;
    }
    public void setSkill(Skill skill) {
        this.skill = skill;
    }
    @ManyToOne
    @JoinColumn(name = "skill_id")
    private Skill skill;
}