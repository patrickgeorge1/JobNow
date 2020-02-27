package com.company.jobnow.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.android.gms.maps.model.LatLng;

@Entity(tableName = "users")
public class User {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;

    @ColumnInfo(name = "realName")
    private String fullName;

    @ColumnInfo(name = "username")
    private String email;

    @ColumnInfo(name = "password")
    private String hashPassword;

    @ColumnInfo(name = "lastPosLat")
    private float lastPosLat;

    @ColumnInfo(name = "lastPosLon")
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
