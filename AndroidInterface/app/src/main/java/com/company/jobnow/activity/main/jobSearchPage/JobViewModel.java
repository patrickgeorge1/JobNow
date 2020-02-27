package com.company.jobnow.activity.main.jobSearchPage;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.company.jobnow.activity.repository.JobRepository;
import com.company.jobnow.entity.Job;

import java.util.List;

public class JobViewModel extends AndroidViewModel {
    private JobRepository jobRepository;
    private LiveData<List<Job>> jobList;

    public JobViewModel(@NonNull Application application) {
        super(application);
        jobRepository = new JobRepository(application);
        jobList = jobRepository.getJobs(20);
    }

    public void insert(Job job) {
        jobRepository.insert(job);
    }

    public LiveData<List<Job>> getJobList() {
        return jobList;
    }
}
