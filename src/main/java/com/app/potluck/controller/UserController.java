package com.app.potluck.controller;

import com.app.potluck.dto.UserDTO;
import com.app.potluck.service.UserService;
import com.app.potluck.util.JwtUtil;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;
    private final JwtUtil jwtUtil;

    public UserController(UserService userService, JwtUtil jwtUtil) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    /**
     * Get User Profile
     *
     * @param userId The userId of the user (can be retrieved from security context in
     *              production).
     * @return User profile details.
     */
    @GetMapping("/{userId}/profile")
    public ResponseEntity<UserDTO> getProfile(
            @RequestHeader("Authorization") String token,
            @PathVariable Long userId) {
        // Step 1: Validate the token
        token = token.replace("Bearer ", "");
        if (!jwtUtil.validateToken(token)) {
            return ResponseEntity.status(401).build(); // Unauthorized
        }
        // Step 2: Extract userId from the token
        Long userIdFromToken = jwtUtil.extractUserId(token);
        // Step 3: Validate that the userId in the token matches the path variable
        if (!userId.equals(userIdFromToken)) {
            return ResponseEntity.status(403).build(); // Forbidden
        }
        // Step 4: Fetch the user profile
        UserDTO userProfile = userService.getUserProfile(userId);
        // Step 5: Return the user profile
        return ResponseEntity.ok(userProfile);
    }
    /**
     * Update User Profile
     *
     * @param userDTO The user profile details to update.
     * @return A success message.
     */
    @PutMapping("/profile")
    public ResponseEntity<String> updateProfile(@Validated @RequestBody UserDTO userDTO) {
        userService.updateUserProfile(userDTO);
        return ResponseEntity.ok("User profile updated successfully.");
    }

    /**
     * Upload User Avatar
     *
     * @param userId  The userId of the user.
     * @param avatar The avatar file to upload.
     * @return A success message.
     */
    @PostMapping("/upload-avatar")
    public ResponseEntity<String> uploadAvatar(@PathVariable Long userId, @RequestParam("file") MultipartFile avatar) {
        userService.uploadAvatar(userId, avatar);
        return ResponseEntity.ok("Profile picture uploaded successfully.");
    }

    /**
     * Check Verification Status
     *
     * @param userId The userId of the user.
     * @return A map containing the user's verification status.
     */
    @GetMapping("/verification-status")
    public ResponseEntity<Map<String, Boolean>> getVerificationStatus(@PathVariable Long userId) {
        boolean isVerified = userService.isUserVerified(userId);
        return ResponseEntity.ok(Map.of("isVerified", isVerified));
    }

    /**
     * Submit Verification ID
     *
     * @param userId      The userId of the user.
     * @param idDocument The verification document file to upload.
     * @return A success message.
     */
    @PostMapping("/verify")
    public ResponseEntity<String> submitVerification(@PathVariable Long userId,
            @RequestParam("file") MultipartFile idDocument) {
        userService.verifyUser(userId, idDocument);
        return ResponseEntity.ok("Verification document submitted successfully.");
    }
}