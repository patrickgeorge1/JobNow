package com.company.jobnow.activity.database.job;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.company.jobnow.entity.Job;

@Database(entities = {Job.class}, version = 8)
public abstract class JobDatabase extends RoomDatabase {
    private static JobDatabase instance;
    public abstract JobDao jobDao();

    public static JobDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context, JobDatabase.class, "jobs")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }



    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
//            new PopulateAsyncTask(instance).execute();
        }
    };

    private static class PopulateAsyncTask extends AsyncTask<Void, Void, Void> {
        private JobDao jobDao;

        PopulateAsyncTask(JobDatabase noteDatabase) {
            jobDao = noteDatabase.jobDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            for (int i = 0; i < 10; i++) {
                jobDao.insert(new Job("Title " + i, "Description " + i, i * 10));
            }
            return null;
        }
    }
}
