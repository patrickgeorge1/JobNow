package com.company.jobnow.activity.createJob;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.company.jobnow.R;
import com.company.jobnow.activity.adapter.AdapterSyncCategory;
import com.company.jobnow.activity.adapter.RecyclerViewAdapterCategory;
import com.company.jobnow.entity.Category;

import java.util.List;

public class DialogUpdateCategories extends AppCompatDialogFragment {
    AppCompatActivity mainActivity;
    AdapterSyncCategory adapterSyncCategory;
    private List<Category> selectedCategories;
    private List<Category> unselectedCategories;

    private DialogUpdateCategoriesListener listener;

    public DialogUpdateCategories(AppCompatActivity mainActivity, AdapterSyncCategory adapterSyncCategory, List<Category> selectedCategories, List<Category> unselectedCategories) {
        this.mainActivity = mainActivity;
        this.adapterSyncCategory = adapterSyncCategory;
        this.selectedCategories = selectedCategories;
        this.unselectedCategories = unselectedCategories;
    }

    public interface DialogUpdateCategoriesListener {
        void exectueValidateCategory();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(mainActivity);
        LayoutInflater inflater = mainActivity.getLayoutInflater();

        View view = inflater.inflate(R.layout.dialog_update_categories, null);

        RecyclerView recyclerViewSelected = view.findViewById(R.id.recyclerView_selectedCategories);
        recyclerViewSelected.setLayoutManager(new LinearLayoutManager(mainActivity, LinearLayoutManager.VERTICAL, false));
        recyclerViewSelected.setAdapter(new RecyclerViewAdapterCategory(mainActivity, adapterSyncCategory, selectedCategories, unselectedCategories, R.layout.item_recyclerview_selected_category, R.id.textView_selectedCategory, R.id.button_selectedCategory));

        RecyclerView recyclerViewUnselected = view.findViewById(R.id.recyclerView_unselectedCategories);
        recyclerViewUnselected.setLayoutManager(new LinearLayoutManager(mainActivity, LinearLayoutManager.VERTICAL, false));
        recyclerViewUnselected.setAdapter(new RecyclerViewAdapterCategory(mainActivity, adapterSyncCategory, unselectedCategories, selectedCategories, R.layout.item_recyclerview_unselected_category, R.id.textView_unselectedCategory, R.id.button_unselectedCategory));

        builder.setView(view)
                .setTitle(getString(R.string.dialog_edit_categories_title))
                .setPositiveButton(getString(R.string.dialog_ok), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        listener.exectueValidateCategory();
                    }
                });
        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Activity activity) {
        super.onAttach(activity);
        listener = (DialogUpdateCategoriesListener) activity;
    }
}
