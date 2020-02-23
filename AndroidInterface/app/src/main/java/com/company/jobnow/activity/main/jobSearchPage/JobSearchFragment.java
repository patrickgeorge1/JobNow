package com.company.jobnow.activity.main.jobSearchPage;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
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
import com.company.jobnow.activity.adapter.RecyclerViewAdapterJob;
import com.company.jobnow.activity.updatePreferences.UpdateJobPreferencesActivity;
import com.company.jobnow.common.Constant;
import com.company.jobnow.entity.Job;

import java.util.HashSet;
import java.util.List;

public class JobSearchFragment extends Fragment {
    private RecyclerViewAdapterJob jobListAdapter;

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
                startActivityForResult(intent, Constant.RequestCode.FILTER_PREFERENCES);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_job_search, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView_jobs);

        final List<Job> jobList = SingletonDatabase.getInstance().getJobList();
        jobListAdapter = new RecyclerViewAdapterJob(getActivity(), jobList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(jobListAdapter);

        view.findViewById(R.id.fab_addNewJob).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), CreateJobActivity.class);
                startActivityForResult(intent, Constant.RequestCode.JOB_ADD);
            }
        });
        return view;
    }

    private void filterJobList() {
        // TODO REPLACE CURRENT LIST WITH ONE FROM DATABASE AND NOTIFY THE ADAPTER
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(Constant.FILTER_PREFERENCES, Activity.MODE_PRIVATE);
        jobListAdapter.updateWithUserPreferences(sharedPreferences.getInt(Constant.PREFERED_MIN_PRICE, Constant.Numeric.DEFAULT_MAX_PRICE),
                sharedPreferences.getInt(Constant.PREFERED_MAX_PRICE, Constant.Numeric.DEFAULT_MAX_PRICE),
                sharedPreferences.getInt(Constant.PREFERED_DISTANCE, Constant.Numeric.DEFAULT_DISTANCE),
                sharedPreferences.getStringSet(Constant.SELECTED_CATEGORIES, new HashSet<String>()));
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode == Activity.RESULT_OK && requestCode == Constant.RequestCode.JOB_ADD) {
            jobListAdapter.refreshList();
        }
        if (requestCode == Constant.RequestCode.FILTER_PREFERENCES) {
            filterJobList();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}