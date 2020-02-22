package com.company.jobnow.activity.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.company.jobnow.R;
import com.company.jobnow.entity.Currency;

import java.util.List;

public class ArrayAdapterCurrency extends ArrayAdapter<Currency> {
    private Context context;
    private List<Currency> currencyList;

    public ArrayAdapterCurrency(@NonNull Context context, List<Currency> currencyList) {
        super(context, R.layout.item_spinner_currency, currencyList);
        this.context = context;
        this.currencyList = currencyList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.item_spinner_currency, parent, false);
        TextView textView = row.findViewById(R.id.spinner_textView_currency);
        textView.setText(currencyList.get(position).getAbbreviation());
        return row;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.item_spinner_currency, parent, false);
        TextView textView = row.findViewById(R.id.spinner_textView_currency);
        textView.setText(currencyList.get(position).getAbbreviation());
        return row;
    }
}
