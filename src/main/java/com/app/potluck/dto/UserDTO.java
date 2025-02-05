package com.app.potluck.dto;

public class UserDTO {
    private String fullName;
    private String email;
    private String preferences;
    private String profilePicture;
    private boolean isVerified;
    private String role;
    private Long userId;

    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    // Getters and Setters
    public String getFullName() {
        return fullName;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPreferences() {
        return preferences;
    }
    public void setPreferences(String preferences) {
        this.preferences = preferences;
    }
    public String getProfilePicture() {
        return profilePicture;
    }
    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }
    public boolean getIsVerified() {
        return isVerified;
    }
    public void setIsVerified(boolean isVerified) {
        this.isVerified = isVerified;
    }
    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }
}