package com.example.jobnow;

import com.example.jobnow.entity.Category;
import com.example.jobnow.entity.Job;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SingletonDatabase {
    private static final SingletonDatabase ourInstance = new SingletonDatabase();

    List<Job> jobListDemo;
    List<String> chatsDemo;
    List<Category> categoryListDemo;
    List<String> currencyListDemo;

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
        currencyListDemo.add("EUR");
        currencyListDemo.add("RON");
        currencyListDemo.add("USD");

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

    public void addJob(String jobTitle, String jobDescription, String jobPrice, List<Category> jobCategory) {
        jobListDemo.add(new Job(jobTitle, jobDescription, jobPrice));
    }

    public List<String> getCurrencyList() {
        return currencyListDemo;
    }
}
