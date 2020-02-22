package com.company.jobnow.activity.adapter;

import android.content.Context;
import android.os.Vibrator;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.company.jobnow.entity.Category;

import java.util.List;

public class RecycleViewAdapterCategory extends RecyclerView.Adapter<RecycleViewAdapterCategory.ViewHolder> {
    private static final String TAG = "RecycleViewAdapterCateg";

    private Context context;
    private List<Category> baseCategories;
    private List<Category> complementaryCategories;
    private int rowDisplay, nameId, buttonId;
    private AdapterSyncCategory adapterSync;

    public RecycleViewAdapterCategory(Context context, AdapterSyncCategory adapterSync, List<Category> basebaseCategories, List<Category> complementaryCategories, int rowDisplay, int nameId, int buttonId) {
        this.context = context;
        this.adapterSync = adapterSync;
        this.baseCategories = basebaseCategories;
        this.complementaryCategories = complementaryCategories;
        this.rowDisplay = rowDisplay;
        this.nameId = nameId;
        this.buttonId = buttonId;
        this.adapterSync.addAdapter(this);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView name;
        private ImageButton button;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(nameId);
            button = itemView.findViewById(buttonId);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowDisplay, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.name.setText(baseCategories.get(position).getName());
        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                complementaryCategories.add(baseCategories.remove(position));
                try {
                    ((Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE)).vibrate(100);
                } catch (NullPointerException e) {
                    Log.e(TAG, "onMapLongClick: Exception: " + e.getMessage());
                }
                adapterSync.notifyAllAdapters();
            }
        });
    }


    @Override
    public int getItemCount() {
        return baseCategories.size();
    }
}
