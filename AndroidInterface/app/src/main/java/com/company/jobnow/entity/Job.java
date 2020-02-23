package com.company.jobnow.entity;

import com.company.jobnow.SingletonDatabase;
import com.company.jobnow.common.Utilities;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Job {
    private int id;
    // TODO autoincrement ID

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
        this.jobCategoty = new ArrayList<>();
        this.jobCategoty.add( new Category("default cat"));
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

    public float getRelativeDistance(LatLng currentPosition) {
        return Utilities.computeDistance(new LatLng(latitude, longitude), currentPosition);
    }

    public boolean filterByPrice(int minPrice, int maxPrice, int maxDistance, Set<String> categorySet) {
        int intPrice;
        try {
            intPrice = Integer.parseInt(price.substring(0, price.length() - 4));
        } catch (Exception e) {
            intPrice = 0;
        }
        if (intPrice < minPrice || maxPrice < intPrice) {
            return false;
        }

        if (maxDistance < getRelativeDistance(SingletonDatabase.getInstance().getCurrentUser().getLastPos())) {
            return false;
        }

        for (Category c : jobCategoty) {
            if (categorySet.contains(String.valueOf(c.getId()))) {
                return true;
            }
        }
        return false;
    }
}
