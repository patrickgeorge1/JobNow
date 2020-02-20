package com.company.jobnow.entity;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.List;

public class Job {
    private String name;
    private String price;
    private String description;
    private LatLng jobPosition;
    private List<Category> jobCategoty;

    public Job(String name, String price, String description, List<Category> jobCategoty, LatLng jobPosition) {
        this.name = name;
        this.price = price;
        this.description = description;

        this.jobCategoty = new ArrayList<>();
        this.jobCategoty.addAll(jobCategoty);

        this.jobPosition = new LatLng(jobPosition.latitude, jobPosition.longitude);
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

    public LatLng getJobPosition() {
        return jobPosition;
    }

    public void setJobPosition(LatLng jobPosition) {
        this.jobPosition = jobPosition;
    }

    public List<Category> getJobCategoty() {
        return jobCategoty;
    }

    public void setJobCategoty(List<Category> jobCategoty) {
        this.jobCategoty = jobCategoty;
    }
}
