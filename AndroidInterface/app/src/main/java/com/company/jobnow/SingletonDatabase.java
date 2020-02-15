package com.company.jobnow;

import com.company.jobnow.entity.Category;
import com.company.jobnow.entity.Currency;
import com.company.jobnow.entity.Job;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SingletonDatabase {
    private static final SingletonDatabase ourInstance = new SingletonDatabase();

    List<Job> jobListDemo;
    List<String> chatsDemo;
    List<Category> categoryListDemo;
    List<Currency> currencyListDemo;

    public static SingletonDatabase getInstance() {
        return ourInstance;
    }


    private SingletonDatabase() {
        jobListDemo = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            jobListDemo.add(new Job("Job " + i, "Description " + i, String.valueOf((new Random()).nextInt(100))));
        }
        chatsDemo = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            chatsDemo.add("Chat " + i);
        }
        categoryListDemo = new ArrayList<>();
        categoryListDemo.add(new Category("IT"));
        categoryListDemo.add(new Category("managment si activitati economice"));
        for (int i = 0; i < 10; i++) {
            categoryListDemo.add(new Category("Category" + i));
        }
        currencyListDemo = new ArrayList<>();
        currencyListDemo.add(new Currency("EUR", "Euro"));
        currencyListDemo.add(new Currency("RON", "asdas"));
        currencyListDemo.add(new Currency("USD", "adasd"));

    }

    public List<Job> getJobList() {
        return jobListDemo;
    }

    public List<String> getChats() {
        return chatsDemo;
    }

    public List<Category> getListCategories() {
        return categoryListDemo;
    }

    public void addJob(String jobTitle, String jobPrice, String jobDescription, LatLng jobPosition, List<Category> jobCategory) {
        jobListDemo.add(new Job(jobTitle, jobPrice, jobDescription, jobPosition, jobCategory));
    }

    public List<Currency> getCurrencyList() {
        return currencyListDemo;
    }
}
