package com.company.jobnow.activity.main.jobSearchPage;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.company.jobnow.R;
import com.company.jobnow.SingletonDatabase;
import com.company.jobnow.common.Constant;
import com.company.jobnow.common.Global;
import com.company.jobnow.entity.Category;
import com.company.jobnow.entity.Job;

public class JobPanelActivity extends AppCompatActivity {
    TextView jobTitle;
    TextView jobPrice;
    TextView jobDescription;
    TextView jobDistance;
    TextView jobCategory;

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
        jobCategory = findViewById(R.id.jobPanel_category);
        jobTitle.setSelected(true);

        Job job = Global.getInstance().getJob();

        if (job != null) {
            jobTitle.setText(job.getName());
            jobPrice.setText(job.getDescription());
            jobDistance.setText(String.valueOf(job.getRelativeDistance(SingletonDatabase.getInstance().getCurrentUser().getLastPos())));

            StringBuilder stringBuilder = new StringBuilder();
            for (Category c : job.getJobCategoty()) {
                stringBuilder.append("; ");
                stringBuilder.append(c.getName());
            }
            if (stringBuilder.length() > 2) {
                stringBuilder.delete(0, 2);
            }
            jobCategory.setText(stringBuilder.toString());
            Global.getInstance().setJob(null);
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
