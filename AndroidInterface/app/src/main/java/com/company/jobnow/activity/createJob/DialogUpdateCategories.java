package com.company.jobnow.activity.createJob;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.company.jobnow.R;
import com.company.jobnow.activity.createJob.adapter.RecycleViewAdapterCategory;
import com.company.jobnow.entity.Category;

import java.util.List;

public class DialogUpdateCategories extends AppCompatDialogFragment {
    private List<Category> selectedCategories;
    private List<Category> unselectedCategories;

    public DialogUpdateCategories(List<Category> selectedCategories, List<Category> unselectedCategories) {
        this.selectedCategories = selectedCategories;
        this.unselectedCategories = unselectedCategories;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();

        View view = inflater.inflate(R.layout.dialog_update_categories, null);

        RecyclerView recyclerViewSelected = view.findViewById(R.id.RecycleView_selectedCategories);
        recyclerViewSelected.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        recyclerViewSelected.setAdapter(new RecycleViewAdapterCategory(getActivity(), selectedCategories, unselectedCategories, R.layout.item_recycleview_selected_category, R.id.textView_selectedCategory, R.id.button_selectedCategory));


        RecyclerView recyclerViewUnselected = view.findViewById(R.id.RecycleView_unselectedCategories);
        recyclerViewUnselected.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        recyclerViewUnselected.setAdapter(new RecycleViewAdapterCategory(getActivity(), unselectedCategories, selectedCategories, R.layout.item_recycleview_unselected_category, R.id.textView_unselectedCategory, R.id.button_unselectedCategory));

        builder.setView(view)
                .setTitle(getString(R.string.dialog_edit_categories_title))
                .setPositiveButton(getString(R.string.dialog_ok), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ((CreateJobActivity) getActivity()).validateSelectedCategories();
                    }
                });
        return builder.create();
    }
}
