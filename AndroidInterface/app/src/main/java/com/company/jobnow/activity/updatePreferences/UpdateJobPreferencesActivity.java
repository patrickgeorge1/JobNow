package com.company.jobnow.activity.updatePreferences;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.company.jobnow.R;

public class UpdateJobPreferencesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_job_preferences);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
