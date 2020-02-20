package com.company.jobnow.activity.createJob;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.company.jobnow.R;
import com.company.jobnow.SingletonDatabase;
import com.company.jobnow.entity.Category;
import com.google.android.gms.maps.model.LatLng;

import java.util.List;

public class DialogConfirmJobData extends AppCompatDialogFragment {
    private static final String TAG = "DialogConfirmJobData";

    AppCompatActivity mainActivity;
    private String jobTitle;
    private String jobPrice;
    private String jobDescription;
    private LatLng jobPosition;
    private List<Category> jobCategory;

    public DialogConfirmJobData(AppCompatActivity mainActivity, String jobTitle, String jobPrice, String jobDescription, List<Category> jobCategory, LatLng jobPosition) {
        this.mainActivity = mainActivity;
        this.jobTitle = jobTitle;
        this.jobPrice = jobPrice;
        this.jobDescription = jobDescription;
        this.jobPosition = jobPosition;
        this.jobCategory = jobCategory;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(mainActivity);
        LayoutInflater inflater = mainActivity.getLayoutInflater();

        StringBuilder stringCategory = new StringBuilder();
        for (Category c : jobCategory ) {
            stringCategory.append(c.getName() + '\n');
        }

        View view = inflater.inflate(R.layout.dialog_confirm_job_data, null);
        ((TextView) view.findViewById(R.id.dialog_confirm_title)).setText(jobTitle);
        ((TextView) view.findViewById(R.id.dialog_confirm_price)).setText(jobPrice);
        ((TextView) view.findViewById(R.id.dialog_confirm_description)).setText(jobDescription);
        ((TextView) view.findViewById(R.id.dialog_confirm_position)).setText(jobPosition.toString());
        ((TextView) view.findViewById(R.id.dialog_confirm_category)).setText(stringCategory.toString());

        builder.setView(view)
                .setTitle(getString(R.string.dialog_confirm_data_title))
                .setPositiveButton(getString(R.string.dialog_yes), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String feedbackMessage = getString(R.string.create_job_successful);
                        try {
                            SingletonDatabase.getInstance().addJob(jobTitle, jobPrice, jobDescription, jobPosition.latitude, jobPosition.longitude, jobCategory);
                        } catch (Exception e) {
                            Log.e(TAG, "onClick: addJob: Exception " + e.getMessage());
                            feedbackMessage = getString(R.string.create_job_failed);
                        }
                        try {
                            Toast.makeText(mainActivity, feedbackMessage, Toast.LENGTH_LONG).show();
                            mainActivity.setResult(Activity.RESULT_OK);
                            mainActivity.finish();
                        } catch (Exception e) {
                            Log.e(TAG, "onClick: Exception " + e.getMessage());
                        }
                    }
                })
                .setNegativeButton(getString(R.string.dialog_no), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        return builder.create();
    }
}
