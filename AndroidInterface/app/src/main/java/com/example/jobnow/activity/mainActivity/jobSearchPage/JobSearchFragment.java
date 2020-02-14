package com.example.jobnow.activity.mainActivity.jobSearchPage;

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
import androidx.fragment.app.FragmentTransaction;

import com.example.jobnow.activity.createJob.CreateJobActivity;
import com.example.jobnow.entity.Job;
import com.example.jobnow.Adapters.ListViewAdapterJob;
import com.example.jobnow.R;
import com.example.jobnow.SingletonDatabase;
import com.example.jobnow.activity.updatePreferencesActivity.UpdateJobPreferencesActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class JobSearchFragment extends Fragment {

    private View view;
    private FragmentTransaction fragmentTransaction;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        fragmentTransaction = getFragmentManager().beginTransaction();
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
        FloatingActionButton addNewJobButton = view.findViewById(R.id.fab_addNewJob);

        final List<Job> jobList = SingletonDatabase.getInstance().getJobList();
        final ListViewAdapterJob jobListAdapter = new ListViewAdapterJob(getActivity(), R.layout.listview_job, jobList);
        listView.setAdapter(jobListAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity(), "List Item " + position, Toast.LENGTH_SHORT).show();
                jobListAdapter.remove(jobList.get(position));
            }
        });

        addNewJobButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), CreateJobActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }
}