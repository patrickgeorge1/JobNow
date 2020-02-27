package com.company.jobnow.activity.database.job;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.company.jobnow.entity.Job;

import java.util.List;

@Dao
public interface JobDao {

    @Insert
    void insert(Job job);

    @Query("SELECT * FROM jobs")
    LiveData<List<Job>> getAllJobs();
}
