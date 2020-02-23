package com.company.jobnow.entity;

import com.google.android.gms.maps.model.LatLng;

public class User {
    private int id;
    // TODO autoincrement ID

    private String fullName;
    private String email;
    private String hashPassword;
    private float lastPosLat;
    private float lastPosLong;

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

    public void setLastPos(float latitude, float longitude) {
        lastPosLat = latitude;
        lastPosLong= longitude;
    }

    public LatLng getLastPos() {
        return new LatLng(lastPosLat, lastPosLong);
    }
}
