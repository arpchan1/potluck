package com.app.potluck.service;

import com.app.potluck.dto.AuthRequest;
import com.app.potluck.dto.AuthResponse;
import com.app.potluck.entity.User;
import com.app.potluck.exception.UserNotFoundException;
import com.app.potluck.repository.UserRepository;
import com.app.potluck.util.JwtUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    // Register a new user
    public AuthResponse registerUser(AuthRequest request) {
        // Check if email is already registered
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("Email is already in use");
        }

        // Create a new user entity
        User user = new User();
        user.setFullName(request.getFullName());
        user.setEmail(request.getEmail());
        user.setPasswordHash(passwordEncoder.encode(request.getPassword()));
        user.setProfilePicture(request.getProfilePicture()); // Optional
        user.setIsVerified(false); // Default false if not provided
        user.setRole(request.getRole() != null ? request.getRole() : "USER"); // Default to "USER"
        user.setLocationRadius(request.getLocationRadius());
        user.setLanguagePref(request.getLanguagePref() != null ? request.getLanguagePref() : "EN"); // Default to "EN"

        // Save the user to the database
        userRepository.save(user);

        // Generate a JWT token for the user
        String token = jwtUtil.generateToken(user.getUserId());

        return new AuthResponse(user.getFullName(), token,user.getUserId() );
    }

    // Authenticate an existing user
    public AuthResponse authenticateUser(AuthRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new UserNotFoundException("Invalid Email"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPasswordHash())) {
            throw new UserNotFoundException("Invalid credentials");
        }

        String token = jwtUtil.generateToken(user.getUserId());
        return new AuthResponse(user.getFullName(), token, user.getUserId());
    }
}
