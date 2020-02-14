package com.example.jobnow.entity;

import androidx.annotation.NonNull;

public class Category {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category(String name) {

        this.name = name;
    }

    @NonNull
    @Override
    public String toString() {
        return name;
    }
}
