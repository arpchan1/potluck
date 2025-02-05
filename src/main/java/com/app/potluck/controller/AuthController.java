package com.app.potluck.controller;
import com.app.potluck.dto.AuthRequest;
import com.app.potluck.dto.AuthResponse;
import com.app.potluck.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    // Signup endpoint
    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> signup(@Valid @RequestBody AuthRequest request) {
        AuthResponse response = authService.registerUser(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // Login endpoint
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody AuthRequest request) {
        AuthResponse response = authService.authenticateUser(request);
        return ResponseEntity.ok(response);
    }
}