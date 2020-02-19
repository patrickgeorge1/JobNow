package com.company.jobnow.activity.updatePreferences;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.company.jobnow.R;
import com.company.jobnow.SingletonDatabase;
import com.company.jobnow.activity.createJob.adapter.RecycleViewAdapterCategory;
import com.company.jobnow.common.Constant;
import com.company.jobnow.entity.Category;
import com.jaygoo.widget.RangeSeekBar;

import java.util.ArrayList;
import java.util.List;

public class UpdateJobPreferencesActivity extends AppCompatActivity {

    com.jaygoo.widget.RangeSeekBar seekBarPrice;
    TextView textMinPrice;
    TextView textMaxPrice;

    SeekBar seekBarDistance;
    EditText textMaxDistance;

    List<Category> selectedCategories;
    List<Category> unselectedCategories;

    private int maxDistance;
    private int minPrice = 50;
    private int maxPrice = 400;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_job_preferences);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setUpFilterPrice();
        setUpFilterDistance();
        setUpFilterCategory();
    }


    private void setUpFilterPrice() {

        textMinPrice = findViewById(R.id.min_price);
        textMaxPrice = findViewById(R.id.max_price);

        seekBarPrice = findViewById(R.id.seekbar_price);
        seekBarPrice.setRange(0f, Constant.MAX_PRICE_PREFERENCE);


        textMinPrice.setText(String.valueOf(minPrice));
        textMaxPrice.setText(String.valueOf(maxPrice));
        seekBarPrice.setValue(minPrice, maxPrice);

        seekBarPrice.setOnRangeChangedListener(new RangeSeekBar.OnRangeChangedListener() {
            @Override
            public void onRangeChanged(RangeSeekBar view, float min, float max, boolean isFromUser) {
                minPrice = (int) min;
                maxPrice = (int) max;
                textMinPrice.setText(String.valueOf(minPrice));
                textMaxPrice.setText(String.valueOf(maxPrice));
            }
        });
    }

    private void setUpFilterDistance() {
        seekBarDistance = findViewById(R.id.seekbar_distance);
        textMaxDistance = findViewById(R.id.max_distance);

        seekBarDistance.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                textMaxDistance.setText(String.valueOf(progress));
                textMaxDistance.setSelection(textMaxDistance.getEditableText().toString().length());
                maxDistance = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        textMaxDistance.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String textMaxDistance = s.toString();
                maxDistance = textMaxDistance.length() == 0 ? 0 : Integer.parseInt(textMaxDistance);
                seekBarDistance.setProgress(maxDistance);
            }
        });
    }

    private void setUpFilterCategory() {

        unselectedCategories = SingletonDatabase.getInstance().getListCategories();
        selectedCategories = new ArrayList<>();
        // Store on local and remove selected from unselected

        RecyclerView recyclerViewSelected = findViewById(R.id.RecycleView_selectedCategories);
        recyclerViewSelected.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerViewSelected.setAdapter(new RecycleViewAdapterCategory(this, selectedCategories, unselectedCategories, R.layout.item_recycleview_selected_category, R.id.textView_selectedCategory, R.id.button_selectedCategory));

        RecyclerView recyclerViewUnselected = findViewById(R.id.RecycleView_unselectedCategories);
        recyclerViewUnselected.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerViewUnselected.setAdapter(new RecycleViewAdapterCategory(this, unselectedCategories, selectedCategories, R.layout.item_recycleview_unselected_category, R.id.textView_unselectedCategory, R.id.button_unselectedCategory));
    }
}
