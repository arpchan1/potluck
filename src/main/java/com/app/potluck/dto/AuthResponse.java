package com.app.potluck.dto;

public class AuthResponse {
    private String name;
    private String token;
    private Long userId;


    public AuthResponse(String name, String token, Long userId) {
        this.name = name;
        this.token = token;
        this.userId = userId;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }    
}