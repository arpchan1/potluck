package com.app.potluck.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userId", nullable = false)
    private Long userId;    
    private String fullName;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String passwordHash;
    private String profilePicture;
    private boolean isVerified; // Use boolean for logical values
    private String role;
    private int locationRadius;
    private String languagePref;
    // Getters and setters
    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long id) {
        this.userId = id;
    }
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
    public String getPasswordHash() {
        return passwordHash;
    }
    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
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
    public int getLocationRadius() {
        return locationRadius;
    }
    public void setLocationRadius(int locationRadius) {
        this.locationRadius = locationRadius;
    }
    public String getLanguagePref() {
        return languagePref;
    }
    public void setLanguagePref(String languagePref) {
        this.languagePref = languagePref;
    }
}