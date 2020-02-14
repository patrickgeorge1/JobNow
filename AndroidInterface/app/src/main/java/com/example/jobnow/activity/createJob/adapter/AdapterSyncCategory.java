package com.example.jobnow.activity.createJob.adapter;

import java.util.ArrayList;
import java.util.List;

public class AdapterSyncCategory {
    private static final AdapterSyncCategory ourInstance = new AdapterSyncCategory();

    private List<RecycleViewAdapterCategory> adapters;

    public static AdapterSyncCategory getInstance() {
        return ourInstance;
    }

    private AdapterSyncCategory() {
        adapters = new ArrayList<>();
    }

    public void addAdapter(RecycleViewAdapterCategory a) {
        adapters.add(a);
    }

    public void notifyAllAdapters() {
        for (RecycleViewAdapterCategory a : adapters) {
            a.notifyDataSetChanged();
        }
    }
}
