package com.app.potluck.service;
import com.app.potluck.dto.UserDTO;
import com.app.potluck.entity.User;
import com.app.potluck.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Fetch user profile
    public UserDTO getUserProfile(Long  userId) {
        // Fetch user from the database
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isEmpty()) {
            throw new RuntimeException("User not found.");
        }

        User user = userOptional.get();
        UserDTO userDTO = new UserDTO();
        userDTO.setFullName(user.getFullName());
        userDTO.setEmail(user.getEmail());
        userDTO.setPreferences(user.getLanguagePref());
        userDTO.setProfilePicture(user.getProfilePicture());
        userDTO.setIsVerified(user.getIsVerified());
        userDTO.setRole(user.getRole());
        userDTO.setUserId(userId);
        return userDTO;
    }

    // Update user profile
    public void updateUserProfile(UserDTO userDTO) {
        // Fetch user from the database
        Optional<User> userOptional = userRepository.findById(userDTO.getUserId());
        if (userOptional.isEmpty()) {
            throw new RuntimeException("User not found.");
        }

        User user = userOptional.get();
        user.setFullName(userDTO.getFullName());
        user.setLanguagePref(userDTO.getPreferences());
        userRepository.save(user); // Save updated user details
    }

    // Upload profile picture
    public void uploadAvatar(Long userId, MultipartFile avatar) {
        // Fetch user from the database
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isEmpty()) {
            throw new RuntimeException("User not found.");
        }

        User user = userOptional.get();
        // Save avatar file logic (e.g., save to storage and get the path)
        String avatarPath = "/path/to/avatar/" + avatar.getOriginalFilename();
        user.setProfilePicture(avatarPath);
        userRepository.save(user);
    }

    // Check verification status
    public boolean isUserVerified(Long userId) {
        // Fetch user from the database
        Optional<User> userOptional = userRepository.findById(userId);
        return userOptional.map(User::getIsVerified).orElse(false);
    }

    // Submit ID for verification
    public void verifyUser(Long userId, MultipartFile idDocument) {
        // Fetch user from the database
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isEmpty()) {
            throw new RuntimeException("User not found.");
        }

        User user = userOptional.get();
        // Save ID document logic (e.g., save to storage and flag the user as under review)
     //   String documentPath = "/path/to/verification/documents/" + idDocument.getOriginalFilename();
        user.setIsVerified(false); // Mark as not yet verified
        userRepository.save(user);
    }
}