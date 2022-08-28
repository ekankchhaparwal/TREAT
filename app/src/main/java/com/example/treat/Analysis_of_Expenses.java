package com.example.treat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class Analysis_of_Expenses extends AppCompatActivity {
    TextView t1,t2,e1,e2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_analysis_of_expenses);
        t1 = findViewById(R.id.t1);
        t2 = findViewById(R.id.t2);
        e1 = findViewById(R.id.e1);
        e2 = findViewById(R.id.e2);
        Intent finalintent = getIntent();

        int expense1 = finalintent.getIntExtra("data1",0);
        int expense2 = finalintent.getIntExtra("data2",0);
        int expense3 = finalintent.getIntExtra("data3",0);

        String name1 = finalintent.getStringExtra("name1");
        String name2 = finalintent.getStringExtra("name2");
        String name3 = finalintent.getStringExtra("name3");

        int total = expense1 + expense2 + expense3;
        int avg = total/3;
        String totals   = Integer.toString(total);
        String avgs = Integer.toString(avg);


        t1.setText("Total Expense is \u20B9"+totals );
        t2.setText("Average Expenses is \u20B9"+ avgs);
        String str1,str2;
        if (expense1 >avg && expense2 >avg )
        {
             str1 = name1 + " gets \u20B9"+(expense1-avg)+ " from "+name3;
             str2 = name2 + " gets \u20B9"+(expense2-avg)+ " from "+name3;
        }
        else if (expense1 >avg && expense3 >avg )
        {
             str1 = name1 + " gets \u20B9"+(expense1-avg)+ " from "+name2;
             str2 = name3 + " gets \u20B9"+(expense3-avg)+ " from "+name2;
        }
        else if (expense2 >avg && expense3 >avg )
        {
             str1 = name2 + " gets \u20B9"+(expense2-avg)+ " from "+name1;
             str2 = name3 + " gets \u20B9"+(expense3-avg)+ " from "+name1;
        }
        else if(expense1 >avg && expense2<avg && expense3<avg)
        {
             str1 = name2 + " gives \u20B9"+(avg-expense2)+ " to "+name1;
             str2 = name3 + " gives \u20B9"+(avg-expense3)+ " to "+name1;
        }
        else if(expense2 >avg && expense1<avg && expense3<avg)
        {
             str1 = name1 + " gives \u20B9"+(avg-expense1)+ " to "+name2;
             str2 = name3 + " gives \u20B9"+(avg-expense3)+ " to "+name2;
        }
        else
        {
             str1 = name2 + " gives \u20B9"+(avg-expense2)+ " to "+name3;
             str2 = name1 + " gives \u20B9"+(avg-expense1)+ " to "+name3;
        }
        e1.setText(str1);
        e2.setText(str2);
    }
}