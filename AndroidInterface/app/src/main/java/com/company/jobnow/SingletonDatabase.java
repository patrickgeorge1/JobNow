package com.company.jobnow;

import com.company.jobnow.entity.Category;
import com.company.jobnow.entity.Currency;
import com.company.jobnow.entity.Job;
import com.company.jobnow.entity.User;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class SingletonDatabase {
    private static final SingletonDatabase ourInstance = new SingletonDatabase();

    User appUser;

    List<Job> jobListDemo;
    List<String> chatsDemo;
    List<Category> categoryListDemo;
    List<Currency> currencyListDemo;

    private SingletonDatabase() {
        appUser = new User("fullName", "email@email.com", "kasdkjas1231242dsadasdasdhdlasd");
        jobListDemo = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            jobListDemo.add(new Job("Job " + i, "Description " + i, String.valueOf((new Random()).nextInt(100)) + " RON"));
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

    public static SingletonDatabase getInstance() {
        return ourInstance;
    }

    public List<Job> getJobList() {
        return jobListDemo;
    }

    public List<String> getChats() {
        // NU IMPLEMENTA ASTA CA NU E NEVOIE ACUM
        return chatsDemo;
    }

    public List<Category> getListCategories() {
        return categoryListDemo;
    }

    public List<Currency> getCurrencyList() {
        return currencyListDemo;
    }

    public void addJob(String jobTitle, String jobPrice, String jobDescription, double latitude, double longitude, List<Category> jobCategory) {
        jobListDemo.add(new Job(jobTitle, jobPrice, jobDescription, latitude, longitude, jobCategory));
    }

    public User getCurrentUser() {
        return appUser;
    }

    public boolean authenticateUser(String userEmail, String userHashPassword) {
        // Check if the credidentials exist in DataBase
        // return false instead

        // if exists get user details and store it in appUser

        appUser = new User("FullName", userEmail, userHashPassword);
        return true;
    }


    public boolean registerUser(String fullName, String userEmail, String userHashPassword) {
        // check if information is corect
        // if yes add user to database and return true after store it in appUser

        appUser = new User(fullName, userEmail, userHashPassword);
        return true;
    }

    public List<Job> getFilteredJobList(int minPrice, int maxPrice, int maxDistance, Set<String> categorySet) {
        // TODO IMPLEMENT
        return jobListDemo;
    }
}
