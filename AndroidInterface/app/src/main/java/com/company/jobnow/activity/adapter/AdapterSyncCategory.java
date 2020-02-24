package com.company.jobnow.activity.adapter;

import java.util.ArrayList;
import java.util.List;

public class AdapterSyncCategory {
    private static final AdapterSyncCategory ourInstance = new AdapterSyncCategory();

    private List<RecyclerViewAdapterCategory> adapters;

    public AdapterSyncCategory() {
        adapters = new ArrayList<>();
    }


    public void addAdapter(RecyclerViewAdapterCategory a) {
        adapters.add(a);
    }

    public void notifyAllAdapters() {
        for (RecyclerViewAdapterCategory a : adapters) {
            a.notifyDataSetChanged();
        }
    }
}
