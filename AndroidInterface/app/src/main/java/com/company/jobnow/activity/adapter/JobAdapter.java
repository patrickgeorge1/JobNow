package com.company.jobnow.activity.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.company.jobnow.R;
import com.company.jobnow.entity.Job;
import com.google.android.gms.maps.model.LatLng;

public class JobAdapter extends ListAdapter<Job, JobAdapter.JobHolder> {
    private OnItemClickListener listener;

    public static final DiffUtil.ItemCallback<Job> DIFF_CALLBACK = new DiffUtil.ItemCallback<Job>() {
        @Override
        public boolean areItemsTheSame(@NonNull Job oldItem, @NonNull Job newItem) {
            return newItem.getId() == oldItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Job oldItem, @NonNull Job newItem) {
            return newItem.getTitle().equals(oldItem.getTitle()) &&
                    newItem.getDescription().equals(oldItem.getDescription()) &&
                    newItem.getPrice().equals(oldItem.getPrice());
        }
    };

    public JobAdapter() {
        super(DIFF_CALLBACK);
    }

    public interface OnItemClickListener {
        void onItemClick(Job job);
    }

    public void setOnItemClickListerne(OnItemClickListener listener) {
        this.listener = listener;
    }

    class JobHolder extends RecyclerView.ViewHolder {
        private TextView name;
        private TextView description;
        private TextView price;
        private TextView distance;

        public JobHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.row_jobName);
            description = itemView.findViewById(R.id.row_jobDescription);
            price = itemView.findViewById(R.id.row_jobPrice);
            distance = itemView.findViewById(R.id.row_jobDistance);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(getItem(position));
                    }
                }
            });
        }
    }

    @NonNull
    @Override
    public JobHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycleiew_job, parent, false);
        return new JobHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull JobHolder holder, int position) {
        Job job = getItem(position);
        holder.name.setText(job.getTitle());
        holder.description.setText(job.getDescription());
        holder.price.setText(String.valueOf(job.getPrice()));
        holder.distance.setText(String.valueOf(job.getRelativeDistance(new LatLng(0, 0))));
    }
}
