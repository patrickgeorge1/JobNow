package com.company.jobnow.activity.main.jobSearchPage;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.company.jobnow.R;
import com.company.jobnow.common.Constant;
import com.company.jobnow.entity.Job;

public class JobPanelActivity extends AppCompatActivity {
    TextView jobTitle;
    TextView jobPrice;
    TextView jobDescription;
    TextView jobDistance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_panel);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();

        jobTitle = findViewById(R.id.jobPanel_title);
        jobPrice = findViewById(R.id.jobPanel_price);
        jobDescription = findViewById(R.id.jobPanel_description);
        jobDistance = findViewById(R.id.jobPanel_distance);
        jobTitle.setSelected(true);

        if (intent.hasExtra(Constant.Key.JOB_TITLE) && intent.hasExtra(Constant.Key.JOB_PRICE) && intent.hasExtra(Constant.Key.JOB_DISTANCE ) && intent.hasExtra(Constant.Key.JOB_DESCRIPTION)) {
            jobTitle.setText(intent.getExtras().getString(Constant.Key.JOB_TITLE));
            jobPrice.setText(intent.getExtras().getString(Constant.Key.JOB_PRICE));
            jobDistance.setText(intent.getExtras().getString(Constant.Key.JOB_DISTANCE));
        } else {
            Toast.makeText(this, getString(R.string.error_loading_job), Toast.LENGTH_SHORT).show();
            setResult(RESULT_CANCELED);
            finish();
        }
    }

    public void applyForJob(View view) {
        Toast.makeText(this, getString(R.string.job_accepted), Toast.LENGTH_LONG).show();
        setResult(RESULT_OK);
        finish();
    }
}
