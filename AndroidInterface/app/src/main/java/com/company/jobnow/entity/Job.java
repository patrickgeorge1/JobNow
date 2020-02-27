package com.company.jobnow.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.company.jobnow.common.Utilities;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.List;

@Entity(tableName = "jobs")
public class Job {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private Integer id;

    @ColumnInfo(name = "title")
    private String title;

    @ColumnInfo(name = "description")
    private String description;

    @ColumnInfo(name = "price")
    private Integer price;

    @Ignore
    private List<Category> jobCategoty;

    @ColumnInfo(name = "latitude")
    private Double latitude;

    @ColumnInfo(name = "longitude")
    private Double longitude;

    public Job(String title, String description, Integer price, Double latitude, Double longitude) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.latitude = latitude;
        this.longitude = longitude;

        // TODO CATEGORY IMPLEMENT
        this.jobCategoty = new ArrayList<>();
        this.jobCategoty.add( new Category("default cat"));
    }

    public Job(String title, Integer price, String description, Double latitude, Double longitude, List<Category> jobCategoty) {
        this.title = title;
        this.price = price;
        this.description = description;
        this.latitude = latitude;
        this.longitude = longitude;

        this.jobCategoty = new ArrayList<>();
        this.jobCategoty.addAll(jobCategoty);
    }

    public Job(String name, String description, Integer price) {
        this.title = name;
        this.description = description;
        this.price = price;
        this.jobCategoty = new ArrayList<>();
        this.jobCategoty.add( new Category("default cat"));
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String name) {
        this.title = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
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

//    public boolean filterByPrice(int minPrice, int maxPrice, int maxDistance, Set<String> categorySet) {
//        int intPrice;
//        try {
//            intPrice = Integer.parseInt(price.substring(0, price.length() - 4));
//        } catch (Exception e) {
//            intPrice = 0;
//        }
//        if (intPrice < minPrice || maxPrice < intPrice) {
//            return false;
//        }
//
//        if (maxDistance < getRelativeDistance(SingletonDatabase.getInstance().getCurrentUser().getLastPos())) {
//            return false;
//        }
//
//        for (Category c : jobCategoty) {
//            if (categorySet.contains(String.valueOf(c.getId()))) {
//                return true;
//            }
//        }
//        return false;
//    }
}
