package com.company.jobnow.entity;

public class User {
    private String fullName;
    private String email;
    private String hashPassword;

    public User(String fullName, String email, String hashPassword) {
        this.fullName = fullName;
        this.email = email;
        this.hashPassword = hashPassword;
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

    public String getHashPassword() {
        return hashPassword;
    }

    public void setHashPassword(String hashPassword) {
        this.hashPassword = hashPassword;
    }
}
