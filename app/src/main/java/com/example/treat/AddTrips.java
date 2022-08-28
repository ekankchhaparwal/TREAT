package com.example.treat;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class AddTrips extends ArrayAdapter<String> {
    private ArrayList<String> trip_name;
    private Context context;

    public AddTrips(@NonNull Context context, int resource, @NonNull ArrayList<String> trip_name) {
        super(context, resource, trip_name);
        this.context = context;
        this.trip_name = trip_name;
    }

    @Nullable
    @Override
    public String getItem(int position) {
        return trip_name.get(position);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(getContext()).inflate(R.layout.custom_listview, parent, false);
        TextView t = convertView.findViewById(R.id.expense_text);
        t.setText(getItem(position));
        return convertView;

    }

}
