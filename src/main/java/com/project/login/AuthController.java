package com.project.login;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:5173")
public class AuthController {

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {

        if (request.getEmail().equals("admin@gmail.com") &&
            request.getPassword().equals("12345")) {

            return new LoginResponse("fake-jwt-token");
        }

        throw new RuntimeException("Invalid email or password");
    }
}
