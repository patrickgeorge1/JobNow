package com.company.jobnow.activity.repository;

import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.company.jobnow.activity.database.job.JobDao;
import com.company.jobnow.activity.database.job.JobDatabase;
import com.company.jobnow.entity.Job;

import java.util.List;

public class JobRepository {
    private JobDao jobDao;
    private LiveData<List<Job>> jobs;

    public JobRepository(Context context) {
        JobDatabase jobDatabase = JobDatabase.getInstance(context);
        jobDao = jobDatabase.jobDao();
        jobs = jobDao.getAllJobs();
    }

    public LiveData<List<Job>> getJobs(int noJobs) {
        return jobs;
    }

    public void insert(Job job) {
        new InsertAsyncTask(jobDao).execute(job);
    }

    private static class InsertAsyncTask extends AsyncTask<Job, Void, Void> {
        private JobDao jobDao;

        public InsertAsyncTask(JobDao jobDao) {
            this.jobDao = jobDao;
        }

        @Override
        protected Void doInBackground(Job... jobs) {
            jobDao.insert(jobs[0]);
            return null;
        }
    }
}
