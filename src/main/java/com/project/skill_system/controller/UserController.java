package com.project.skill_system.controller;

import com.project.skill_system.entites.Users;
import com.project.skill_system.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping
    public Users createUser(@RequestBody Users user) {
        return userRepository.save(user);
    }

    @GetMapping
    public List<Users> getAllUsers() {
        return userRepository.findAll();
    }
}