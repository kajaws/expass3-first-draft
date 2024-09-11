package com.example.Expass.model;


import java.util.UUID;

public class User {
    private String username;
    private String email;
    private String userId;


    public User() {
        this.userId = UUID.randomUUID().toString();
    }

    // Getters and Setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserId() {
        return userId;
    }
}


