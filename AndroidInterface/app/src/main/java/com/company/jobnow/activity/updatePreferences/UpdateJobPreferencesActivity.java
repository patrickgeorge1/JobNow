package com.company.jobnow.activity.updatePreferences;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.company.jobnow.R;
import com.company.jobnow.SingletonDatabase;
import com.company.jobnow.activity.adapter.RecyclerViewAdapterCategoryString;
import com.company.jobnow.common.Constant;
import com.company.jobnow.entity.Category;
import com.jaygoo.widget.RangeSeekBar;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class UpdateJobPreferencesActivity extends AppCompatActivity {
    private SharedPreferences sharedPreferences;

    private int minPrice = 50;
    private int maxPrice = 400;
    private int maxDistance;

    RecyclerViewAdapterCategoryString recyclerViewAdapter;

    Set<String> selectedSet;
    boolean[] checks;

    List<Category> selectedCategories;
    List<Category> allCategories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_job_preferences);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setUPPreferences();
        setUpFilterPrice();
        setUpFilterDistance();
        setUpFilterCategory();
    }

    private void setUPPreferences() {
        sharedPreferences = getSharedPreferences(Constant.FILTER_PREFERENCES, Activity.MODE_PRIVATE);
        minPrice = sharedPreferences.getInt(Constant.PREFERED_MIN_PRICE, Constant.Numeric.DEFAULT_MIN_PRICE);
        maxPrice = sharedPreferences.getInt(Constant.PREFERED_MAX_PRICE, Constant.Numeric.DEFAULT_MAX_PRICE);
        maxDistance = sharedPreferences.getInt(Constant.PREFERED_DISTANCE, Constant.Numeric.DEFAULT_DISTANCE);
        selectedSet = sharedPreferences.getStringSet(Constant.SELECTED_CATEGORIES, new HashSet<String>());
    }

    private void setUpFilterPrice() {
        final TextView textMinPrice = findViewById(R.id.min_price);
        final TextView textMaxPrice = findViewById(R.id.max_price);
        com.jaygoo.widget.RangeSeekBar seekBarPrice = findViewById(R.id.seekbar_price);

        textMinPrice.setText(String.valueOf(minPrice));
        textMaxPrice.setText(String.valueOf(maxPrice));
        seekBarPrice.setRange(0f, Constant.Numeric.MAX_PRICE_FILTER);
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
        final EditText textMaxDistance = findViewById(R.id.max_distance);
        final SeekBar seekBarDistance = findViewById(R.id.seekbar_distance);

        textMaxDistance.setText(String.valueOf(maxDistance));
        seekBarDistance.setProgress(maxDistance);

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
        allCategories = SingletonDatabase.getInstance().getListCategories();
        selectedCategories = new ArrayList<>();
        checks = new boolean[allCategories.size()];
        for (Iterator<String> it = selectedSet.iterator(); it.hasNext(); ) {
            int idx = Integer.parseInt(it.next());
            selectedCategories.add(allCategories.get(idx));
            checks[idx] = true;
        }

        recyclerViewAdapter = new RecyclerViewAdapterCategoryString(this, selectedCategories);
        RecyclerView recyclerView = findViewById(R.id.recyclerView_preferences_categoty);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(recyclerViewAdapter);
    }

    public void showSelectCategoriesDialog(View view) {
        String[] items = new String[allCategories.size()];

        for (int i = 0; i < allCategories.size(); i++) {
            items[i] = allCategories.get(i).getName();
        }
        (new AlertDialog.Builder(this))
                .setTitle("asdasd")
                .setMultiChoiceItems(items, checks, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        checks[which] = isChecked;
                    }
                })
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        selectedCategories.clear();
                        selectedSet.clear();
                        for (int i = 0; i < checks.length; i++) {
                            if (checks[i]) {
                                selectedCategories.add(allCategories.get(i));
                                selectedSet.add(String.valueOf(i));
                            }
                        }
                        recyclerViewAdapter.notifyDataSetChanged();
                    }
                })
                .show()
                .create();
    }

    public void savePreferences(View view) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(Constant.PREFERED_MIN_PRICE, minPrice).apply();
        editor.putInt(Constant.PREFERED_MAX_PRICE, maxPrice).apply();
        editor.putInt(Constant.PREFERED_DISTANCE, maxDistance).apply();
        editor.putStringSet(Constant.SELECTED_CATEGORIES, selectedSet).apply();
    }
}
