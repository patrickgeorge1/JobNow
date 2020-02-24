package com.company.jobnow.activity.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.company.jobnow.R;
import com.company.jobnow.entity.Category;

import java.util.List;

public class RecyclerViewAdapterCategoryString extends RecyclerView.Adapter<RecyclerViewAdapterCategoryString.ViewHolder> {
    private Activity context;
    private List<Category> categoryList;

    public RecyclerViewAdapterCategoryString(Activity context, List<Category> categoryList) {
        this.context = context;
        this.categoryList = categoryList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView name;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.recyclerView_text_category);
        }
    }

    @NonNull
    @Override
    public RecyclerViewAdapterCategoryString.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recyclerview_category_string, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        final Category category = categoryList.get(position);
        holder.name.setText(category.getName());
    }


    @Override
    public int getItemCount() {
        return categoryList.size();
    }
}
