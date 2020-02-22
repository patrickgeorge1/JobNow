package com.company.jobnow.activity.adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.company.jobnow.R;
import com.company.jobnow.activity.main.jobSearchPage.JobPanelActivity;
import com.company.jobnow.common.Constant;
import com.company.jobnow.entity.Job;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.List;

public class RecycleViewAdapterJob extends RecyclerView.Adapter<RecycleViewAdapterJob.ViewHolder> implements Filterable {
    private Activity context;
    private List<Job> jobList;
    private List<Job> displayJobList;

    public RecycleViewAdapterJob(Activity context, List<Job> jobList) {
        this.context = context;
        this.jobList = jobList;
        this.displayJobList = new ArrayList<>(jobList);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView name;
        private TextView description;
        private TextView price;
        private TextView distance;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.row_jobName);
            description = itemView.findViewById(R.id.row_jobDescription);
            price = itemView.findViewById(R.id.row_jobPrice);
            distance = itemView.findViewById(R.id.row_jobDistance);
        }
    }

    @NonNull
    @Override
    public RecycleViewAdapterJob.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycleiew_job, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        final Job job = displayJobList.get(position);
        holder.name.setText(job.getName());
        holder.description.setText(job.getDescription());
        holder.price.setText(job.getPrice());
        holder.distance.setText(job.getRelativeDistance(new LatLng(0, 0)));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, JobPanelActivity.class);
                intent.putExtra(Constant.Key.JOB_TITLE, job.getName());
                intent.putExtra(Constant.Key.JOB_PRICE, job.getPrice());
                intent.putExtra(Constant.Key.JOB_DESCRIPTION, job.getDescription());
                intent.putExtra(Constant.Key.JOB_DISTANCE, job.getRelativeDistance(new LatLng(0, 0)));
                context.startActivityForResult(intent, Constant.RequestCode.JOB_PREVIEW);
            }
        });
    }


    @Override
    public int getItemCount() {
        return displayJobList.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                List<Job> filterList = new ArrayList<>();
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (Job j : jobList) {
                    if (j.getName().toLowerCase().trim().contains(filterPattern)) {
                        filterList.add(j);
                    }
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = filterList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                displayJobList.clear();
                displayJobList.addAll((List) results.values);
                notifyDataSetChanged();
            }
        };
    }
}
