package com.company.jobnow.activity.main.jobSearchPage;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.company.jobnow.R;
import com.company.jobnow.SingletonDatabase;
import com.company.jobnow.activity.createJob.CreateJobActivity;
import com.company.jobnow.activity.main.jobSearchPage.adapter.RecycleViewAdapterJob;
import com.company.jobnow.activity.updatePreferences.UpdateJobPreferencesActivity;
import com.company.jobnow.common.RequestCode;
import com.company.jobnow.entity.Job;

import java.util.List;

public class JobSearchFragment extends Fragment {
    private RecycleViewAdapterJob jobListAdapter;

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

        SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                jobListAdapter.getFilter().filter(newText);
                return false;
            }
        });

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

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode == Activity.RESULT_OK && requestCode == RequestCode.JOB_ADD) {
            jobListAdapter.notifyDataSetChanged();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_job_panel_list, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView_jobs);

        final List<Job> jobList = SingletonDatabase.getInstance().getJobList();
        jobListAdapter = new RecycleViewAdapterJob(getActivity(), jobList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(jobListAdapter);

        view.findViewById(R.id.fab_addNewJob).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), CreateJobActivity.class);
                startActivityForResult(intent, RequestCode.JOB_ADD);
            }
        });
        return view;
    }

}