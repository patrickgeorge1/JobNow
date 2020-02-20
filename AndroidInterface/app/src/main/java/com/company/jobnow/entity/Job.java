package com.company.jobnow.entity;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.List;

public class Job {
    private String name;
    private String price;
    private String description;
    private List<Category> jobCategoty;
    private double latitude;
    private double longitude;

    public Job(String name, String price, String description, double latitude, double longitude, List<Category> jobCategoty) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.latitude = latitude;
        this.longitude = longitude;

        this.jobCategoty = new ArrayList<>();
        this.jobCategoty.addAll(jobCategoty);
    }

    public Job(String name, String description, String price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public List<Category> getJobCategoty() {
        return jobCategoty;
    }

    public void setJobCategoty(List<Category> jobCategoty) {
        this.jobCategoty = jobCategoty;
    }
}
