package com.company.jobnow.activity.createJob;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.company.jobnow.R;
import com.company.jobnow.SingletonDatabase;
import com.company.jobnow.activity.adapter.AdapterSyncCategory;
import com.company.jobnow.activity.adapter.ArrayAdapterCurrency;
import com.company.jobnow.activity.adapter.RecycleViewAdapterCategory;
import com.company.jobnow.common.Constant;
import com.company.jobnow.entity.Category;
import com.company.jobnow.entity.Currency;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;

public class CreateJobActivity extends AppCompatActivity implements DialogUpdateCategories.DialogUpdateCategoriesListener {
    private static final String TAG = "CreateJobActivity";
    private RecycleViewAdapterCategory selectedCategoryAdapter;
    private AdapterSyncCategory adapterSyncCategory;
    private Spinner currencySpinner;

    private TextInputLayout inputJobTitle;
    private TextInputLayout inputJobPrice;
    private TextInputLayout inputJobDescription;
    private LatLng jobPosition;
    private List<Category> selectedCategories;
    private List<Category> unselectedCategories;
    private List<Currency> currencyList;

    private TextView labelCategory;
    private TextInputLayout jobCategoryErrorDisplay;
    private TextView labelPosition;
    private TextInputLayout jobPositionErrorDisplay;
    private ImageView imageFeedbackPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_job);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        init();

        createRecycleViewCategory();
        createSpinner();
        setUpListeners();
    }

    private void init() {
        inputJobTitle = findViewById(R.id.textInput_jobTitle);
        inputJobPrice = findViewById(R.id.textInput_jobPrice);
        inputJobDescription = findViewById(R.id.textInput_jobDescription);

        labelCategory = findViewById(R.id.textView_category);
        jobCategoryErrorDisplay = findViewById(R.id.textError_jobCategory);

        labelPosition = findViewById(R.id.textView_location);
        jobPositionErrorDisplay = findViewById(R.id.textError_jobLocation);
        imageFeedbackPosition = findViewById(R.id.imageView_location_feedback);

        selectedCategories = new ArrayList<>();
        unselectedCategories = SingletonDatabase.getInstance().getListCategories();

        currencyList = SingletonDatabase.getInstance().getCurrencyList();
    }

    private void createSpinner() {
        currencySpinner = findViewById(R.id.spinnerInput_currency);
        ArrayAdapterCurrency currencyAdapter = new ArrayAdapterCurrency(this, currencyList);
        currencySpinner.setAdapter(currencyAdapter);
    }

    private void createRecycleViewCategory() {
        adapterSyncCategory = new AdapterSyncCategory();
        selectedCategoryAdapter = new RecycleViewAdapterCategory(this, adapterSyncCategory, selectedCategories, unselectedCategories, R.layout.item_recycleview_selected_category, R.id.textView_selectedCategory, R.id.button_selectedCategory);
        RecyclerView recyclerView = findViewById(R.id.recyclerView_categoty);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(selectedCategoryAdapter);
    }

    private void setUpListeners() {
        findViewById(R.id.textEdit_jobTitle).setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    findViewById(R.id.textEdit_jobDescription).requestFocus();
                    return true;
                }
                return false;
            }
        });
        findViewById(R.id.textEdit_jobPrice).setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    findViewById(R.id.spinnerInput_currency).performClick();
                    ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(v.getWindowToken(), 0);
                    return true;
                }
                return false;
            }
        });
    }

    public boolean validateTitle() {
        String jobTitle = inputJobTitle.getEditText().getText().toString();
        if (jobTitle.length() == 0) {
            inputJobTitle.setError(getString(R.string.error_job_title_empty));
            return false;
        }
        if (!jobTitle.matches("[a-zA-z0-9 ]+")) {
            inputJobTitle.setError(getString(R.string.error_job_title_only_alphanumeric));
            return false;
        }
        if (jobTitle.replace(" ", "").length() < Constant.Numeric.MIN_LENGTH_JOB_TITLE) {
            inputJobTitle.setError(getString(R.string.error_job_title_too_short));
            return false;
        }
        inputJobTitle.setError(null);
        return true;
    }

    public boolean validateSelectedCategories() {
        if (selectedCategoryAdapter.getItemCount() == 0) {
            jobCategoryErrorDisplay.setError(getString(R.string.error_category_selected));
            labelCategory.setTextColor(getResources().getColor(R.color.red));
            return false;
        }
        if (selectedCategoryAdapter.getItemCount() > Constant.Numeric.MAX_JOB_CATEGORIES) {
            jobCategoryErrorDisplay.setError(getString(R.string.error_too_much_category_selected));
            labelCategory.setTextColor(getResources().getColor(R.color.red));
            return false;
        }
        labelCategory.setTextColor(getResources().getColor(R.color.colorAccent));
        jobCategoryErrorDisplay.setError(null);
        return true;
    }

    public boolean validateJobPosition() {
        if (jobPosition == null) {
            jobPositionErrorDisplay.setError(getString(R.string.error_no_location_selected));
            labelPosition.setTextColor(getResources().getColor(R.color.red));
            imageFeedbackPosition.setImageResource(R.drawable.ic_close_red_24dp);
            return false;
        }
        labelPosition.setTextColor(getResources().getColor(R.color.colorAccent));
        imageFeedbackPosition.setImageResource(R.drawable.ic_check_accent_24dp);
        jobPositionErrorDisplay.setError(null);
        return true;
    }

    public void goToDialogConfirmationJobData(View view) {
        if (!validateTitle() | !validateSelectedCategories() | !validateJobPosition()) {
            Toast.makeText(this, getString(R.string.error_forms_not_completed_properly), Toast.LENGTH_LONG).show();
            return;
        }
        String jobTitle = inputJobTitle.getEditText().getText().toString();
        String jobPrice = inputJobPrice.getEditText().getText().toString();
        if (jobPrice.length() == 0 || Integer.parseInt(jobPrice) == 0) {
            jobPrice = getString(R.string.no_price);
        } else {
            jobPrice += " " + ((Currency) currencySpinner.getSelectedItem()).getAbbreviation();
        }
        String jobDescription = inputJobDescription.getEditText().getText().toString();

        DialogConfirmJobData dialogConfirmJobData = new DialogConfirmJobData(this, jobTitle, jobPrice, jobDescription, selectedCategories, jobPosition);
        dialogConfirmJobData.show(getSupportFragmentManager(), Constant.Tag.DIALOG_CONFIRM_JOB_DATA);
    }

    public void goToDialogUpdateCategory(View view) {
        DialogUpdateCategories dialogUpdateCategories = new DialogUpdateCategories(this, adapterSyncCategory, selectedCategories, unselectedCategories);
        dialogUpdateCategories.show(getSupportFragmentManager(), Constant.Tag.DIALOG_EDIT_JOB_CATEGORIES);
    }

    public void goToPickLocationActivity(View view) {
        Intent intent = new Intent(this, PickLocationActivity.class);
        startActivityForResult(intent, Constant.RequestCode.MAP);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode == RESULT_OK && requestCode == Constant.RequestCode.MAP) {
            jobPosition = new LatLng(data.getExtras().getDouble(Constant.Key.LATITUDE), data.getExtras().getDouble(Constant.Key.LONGITUDE));
            Log.e(TAG, "onActivityResult: jobPosition is " + jobPosition.latitude + " " + jobPosition.longitude);
            validateJobPosition();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


    @Override
    public void exectueValidateCategory() {
        validateSelectedCategories();
    }
}
