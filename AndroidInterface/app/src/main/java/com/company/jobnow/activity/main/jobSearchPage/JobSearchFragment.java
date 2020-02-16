package com.company.jobnow.activity.main.jobSearchPage;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.company.jobnow.R;
import com.company.jobnow.SingletonDatabase;
import com.company.jobnow.activity.createJob.CreateJobActivity;
import com.company.jobnow.activity.main.jobSearchPage.adapter.ListViewAdapterJob;
import com.company.jobnow.activity.updatePreferences.UpdateJobPreferencesActivity;
import com.company.jobnow.entity.Job;

import java.util.List;

public class JobSearchFragment extends Fragment {
    private View view;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.search, menu);
        inflater.inflate(R.menu.filter, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search:
                return true;
            case R.id.action_filter:
                Intent intent = new Intent(getActivity(), UpdateJobPreferencesActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_job_panel_list, container, false);
        ListView listView = view.findViewById(R.id.listView_jobs);

        final List<Job> jobList = SingletonDatabase.getInstance().getJobList();
        final ListViewAdapterJob jobListAdapter = new ListViewAdapterJob(getActivity(), R.layout.item_listview_job, jobList);
        listView.setAdapter(jobListAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity(), "List Item " + position, Toast.LENGTH_SHORT).show();
                jobListAdapter.remove(jobList.get(position));
            }
        });

        view.findViewById(R.id.fab_addNewJob).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), CreateJobActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }
}