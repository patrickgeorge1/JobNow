package com.company.jobnow.common;

import com.company.jobnow.entity.Job;

public class Global {
    private static final Global ourInstance = new Global();

    private Job job;

    public static Global getInstance() {
        return ourInstance;
    }

    private Global() { }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }
}
