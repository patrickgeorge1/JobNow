package com.company.jobnow.activity.main.jobSearchPage;

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
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.company.jobnow.R;
import com.company.jobnow.activity.adapter.JobAdapter;
import com.company.jobnow.activity.createJob.CreateJobActivity;
import com.company.jobnow.activity.updatePreferences.UpdateJobPreferencesActivity;
import com.company.jobnow.common.Constant;
import com.company.jobnow.common.Global;
import com.company.jobnow.entity.Job;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class JobSearchFragment extends Fragment {
    JobViewModel jobViewModel;

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
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                jobListAdapter.getFilter().filter(newText);
//                return false;
//            }
//        });

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
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        final JobAdapter adapter = new JobAdapter();
        recyclerView.setAdapter(adapter);

        jobViewModel = ViewModelProviders.of(this).get(JobViewModel.class);
        jobViewModel.getJobList().observe(getViewLifecycleOwner(), new Observer<List<Job>>() {
            @Override
            public void onChanged(List<Job> jobs) {
                adapter.submitList(jobs);
            }
        });

        adapter.setOnItemClickListerne(new JobAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Job job) {
                // TODO REMOVE GLOBAL AFTER MAKE JOB PARCELABLE
                Global.getInstance().setJob(job);
                Intent intent = new Intent(getActivity(), JobPanelActivity.class);
                startActivityForResult(intent, Constant.RequestCode.JOB_PREVIEW);
            }
        });

        final FloatingActionButton addButton = view.findViewById(R.id.fab_addNewJob);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addButton.setEnabled(false);
                Intent intent = new Intent(getActivity(), CreateJobActivity.class);
                startActivityForResult(intent, Constant.RequestCode.JOB_ADD);
                addButton.setEnabled(true);
            }
        });

        return view;
    }

//        jobListAdapter.updateWithUserPreferences(sharedPreferences.getInt(Constant.PREFERED_MIN_PRICE, Constant.Numeric.DEFAULT_MAX_PRICE),
//                sharedPreferences.getInt(Constant.PREFERED_MAX_PRICE, Constant.Numeric.DEFAULT_MAX_PRICE),
//                sharedPreferences.getInt(Constant.PREFERED_DISTANCE, Constant.Numeric.DEFAULT_DISTANCE),
//                sharedPreferences.getStringSet(Constant.SELECTED_CATEGORIES, new HashSet<String>()));
}