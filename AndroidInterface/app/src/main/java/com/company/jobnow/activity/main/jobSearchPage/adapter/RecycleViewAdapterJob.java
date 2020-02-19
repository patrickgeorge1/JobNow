package com.company.jobnow.activity.main.jobSearchPage.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.company.jobnow.R;
import com.company.jobnow.entity.Job;

import java.util.ArrayList;
import java.util.List;

public class RecycleViewAdapterJob extends RecyclerView.Adapter<RecycleViewAdapterJob.ViewHolder> implements Filterable {
    private Context context;
    private List<Job> jobList;
    private List<Job> fullJobList;

    public RecycleViewAdapterJob(Context context, List<Job> jobList) {
        this.context = context;
        this.jobList = jobList;
        fullJobList = new ArrayList<>(jobList);
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
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Job job = jobList.get(position);
        holder.name.setText(job.getName());
        holder.description.setText(job.getDescription());
        holder.price.setText(job.getPrice());
        holder.distance.setText("12.5");

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jobList.remove(position);
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return jobList.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                List<Job> filterList = new ArrayList<>();
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (Job j : fullJobList) {
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
                jobList.clear();
                jobList.addAll((List) results.values);
                notifyDataSetChanged();
            }
        };
    }
}
