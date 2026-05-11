package com.ticketflow.FullStack_back.models;

public class LoginResponse {
    private boolean success;
    private String message;
    private User user;
    private String role;

    public LoginResponse() {}

    public LoginResponse(boolean success, String message, User user, String role) {
        this.success = success;
        this.message = message;
        this.user = user;
        this.role = role;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
