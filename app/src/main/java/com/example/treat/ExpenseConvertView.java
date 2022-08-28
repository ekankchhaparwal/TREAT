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

public class ExpenseConvertView extends ArrayAdapter<String> {

    private ArrayList<String> ExpenseList;
    private Context context;
    public ExpenseConvertView(@NonNull Context context, int resource, @NonNull ArrayList<String> ExpenseList) {
        super(context, resource, ExpenseList);
        this.ExpenseList = ExpenseList;
        this.context = context;
    }

    @Nullable
    @Override
    public String getItem(int position) {
        return ExpenseList.get(position);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(getContext()).inflate(R.layout.custom_expenselist, parent, false);
        TextView t = convertView.findViewById(R.id.expense_text2);
        t.setText(getItem(position));
        return convertView;
    }
}
