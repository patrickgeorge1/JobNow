package com.example.jobnow.activity.createJob;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jobnow.activity.createJob.adapter.RecycleViewAdapterCategory;
import com.example.jobnow.R;
import com.example.jobnow.SingletonDatabase;
import com.example.jobnow.common.Constants;
import com.example.jobnow.entity.Category;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;

public class CreateJobActivity extends AppCompatActivity {

    private TextInputLayout inputjobTitle;
    private TextInputLayout inputJobPrice;
    private TextInputLayout inputjobDescription;
    private TextInputLayout jobCategoryLayout;

    private TextView labelCategory;

    RecycleViewAdapterCategory selectedCategoryAdapter;
    ArrayAdapter<String> currencyAdapter;

    private List<Category> selectedCategories;
    private List<Category> unselectedCategories;
    private List<String> currencies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_job);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        init();

        unselectedCategories.addAll(SingletonDatabase.getInstance().getListCategories());
        currencies = SingletonDatabase.getInstance().getCurrencyList();

        createRecycleViewCategory();
        createSpinner();
    }


    private void init() {
        inputjobTitle = findViewById(R.id.textInput_jobTitle);
        inputJobPrice = findViewById(R.id.textInput_jobPrice);
        inputjobDescription = findViewById(R.id.textInput_jobDescription);
        jobCategoryLayout = findViewById(R.id.textInput_jobCategory);
        labelCategory = findViewById(R.id.textView_category);
        selectedCategories = new ArrayList<>();
        unselectedCategories = new ArrayList<>();
    }

    private void createSpinner() {
        Spinner spinner= findViewById(R.id.spinnerInput_currency);
        currencyAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, currencies);
        currencyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(currencyAdapter);
    }
    private void createRecycleViewCategory() {
        selectedCategoryAdapter = new RecycleViewAdapterCategory(this, selectedCategories, unselectedCategories, R.layout.listview_selected_category, R.id.textView_selectedCategory, R.id.button_selectedCategory);
        RecyclerView recyclerView = findViewById(R.id.recyclerView_categoty);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(selectedCategoryAdapter);
    }

    public boolean validateTitle() {
        String jobTitle = inputjobTitle.getEditText().getText().toString();
        if (!jobTitle.matches("[a-zA-z0-9 ]+")) {
            inputjobTitle.setError(getString(R.string.job_title_only_alphanumeric));
            return false;
        }
        if (jobTitle.replace(" ", "").length() < Constants.MIN_LENGTH_JOB_TITLE) {
            inputjobTitle.setError(getString(R.string.job_title_too_short));
            return false;
        } else {
            inputjobTitle.setError(null);
            return true;
        }
    }

    public boolean validateSelectedCategories() {
        if (selectedCategoryAdapter.getItemCount() == 0) {
            jobCategoryLayout.setError(getString(R.string.no_category_selected));
            labelCategory.setTextColor(getResources().getColor(R.color.red));
            return false;
        }
        if (selectedCategoryAdapter.getItemCount() > Constants.MAX_JOB_CATEGORIES) {
            jobCategoryLayout.setError(getString(R.string.no_too_much_category_selected));
            labelCategory.setTextColor(getResources().getColor(R.color.red));
            return false;
        }
        labelCategory.setTextColor(getResources().getColor(R.color.colorAccent));
        jobCategoryLayout.setError(null);
        return true;
    }

    public void userCreatesJob(View view) {
        if (!validateTitle() | !validateSelectedCategories()) {
            return;
        }
        String jobTitle = inputjobTitle.getEditText().getText().toString();
        String jobPrice = inputJobPrice.getEditText().getText().toString() + " " + currencyAdapter.getItem(0);
        if (jobPrice.length() == 4) {
            jobPrice = "FREE";
        }
        String jobDescription = inputjobDescription.getEditText().getText().toString();

        DialogConfirmJobData dialogConfirmJobData = new DialogConfirmJobData(jobTitle, jobPrice, jobDescription, selectedCategories);
        dialogConfirmJobData.show(getSupportFragmentManager(), "DIALOG_CONFIRM_JOB_DATA");

    }

    public void userEditsJobCategory(View view) {
        DialogUpdateCategories dialogUpdateCategories = new DialogUpdateCategories(selectedCategories, unselectedCategories);
        dialogUpdateCategories.show(getSupportFragmentManager(), "DIALOG_EDIT_JOB_CATEGORIES");
    }
}
