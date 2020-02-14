package com.example.jobnow.activity.createJob;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.jobnow.R;
import com.example.jobnow.SingletonDatabase;
import com.example.jobnow.entity.Category;

import java.util.List;

public class DialogConfirmJobData extends AppCompatDialogFragment {

    private String jobTitle;
    private String jobPrice;
    private String jobDescription;
    private List<Category> jobCategory;

    public DialogConfirmJobData(String jobTitle, String jobPrice, String jobDescription, List<Category> jobCategory) {
        this.jobTitle = jobTitle;
        this.jobPrice = jobPrice;
        this.jobDescription = jobDescription;
        this.jobCategory = jobCategory;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();

        View view = inflater.inflate(R.layout.dialog_confirm_job_data, null);

        ((TextView) view.findViewById(R.id.dialog_confirm_title)).setText(jobTitle);
        ((TextView) view.findViewById(R.id.dialog_confirm_price)).setText(jobPrice);
        ((TextView) view.findViewById(R.id.dialog_confirm_description)).setText(jobDescription);
        ((TextView) view.findViewById(R.id.dialog_confirm_category)).setText(jobCategory.toString());


        builder.setView(view)
                .setTitle(getString(R.string.dialog_confirm_data_title))
                .setPositiveButton(getString(R.string.dialog_yes), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SingletonDatabase.getInstance().addJob(jobTitle, jobDescription, jobPrice, jobCategory);
                        Toast.makeText(getActivity(), R.string.create_job_successful, Toast.LENGTH_LONG).show();
                        getActivity().finish();
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
