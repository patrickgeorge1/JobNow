package com.company.jobnow.activity.main.jobSearchPage.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.company.jobnow.entity.Job;
import com.company.jobnow.R;

import java.text.DecimalFormat;
import java.util.List;

public class ListViewAdapterJob extends ArrayAdapter<Job> {
    Context context;
    List<Job> jobList;

    DecimalFormat decimalFormat;

    public ListViewAdapterJob(Context context, int rowId , List<Job> jobList) {
        super(context, rowId, jobList);
        this.context = context;
        this.jobList = jobList;

        decimalFormat = new DecimalFormat();
        decimalFormat.setMaximumFractionDigits(2);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(getContext());
        View row = layoutInflater.inflate(R.layout.item_listview_job, parent, false);

        TextView name = row.findViewById(R.id.row_jobName);
        TextView description = row.findViewById(R.id.row_jobDescription);
        TextView price = row.findViewById(R.id.row_jobPrice);

        Job job = jobList.get(position);
        name.setText(job.getName());
        description.setText(job.getDescription());
        price.setText(job.getPrice() + " RON");

        return row;
    }
}
