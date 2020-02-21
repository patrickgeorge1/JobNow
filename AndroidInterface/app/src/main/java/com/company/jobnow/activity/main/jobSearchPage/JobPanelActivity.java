package com.company.jobnow.activity.main.jobSearchPage;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.company.jobnow.R;

public class JobPanelActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_panel);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setTitle("123456789012345678901234567890");
        findViewById(R.id.job_title).setSelected(true);
    }
}
