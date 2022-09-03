package com.example.treat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;

public class Analysis_of_Expenses extends AppCompatActivity {
    TextView t1,t2,e1,e2;
    PieChart pieChart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_analysis_of_expenses);
        t1 = findViewById(R.id.t1);
        t2 = findViewById(R.id.t2);
        e1 = findViewById(R.id.e1);
        e2 = findViewById(R.id.e2);
        pieChart = findViewById(R.id.piechart);
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
        if (expense1>0||expense2>0||expense3>0)
        {
            setDataPieChart(expense1,expense2,expense3,name1,name2,name3);
        }

    }
    private void setDataPieChart(int ee1,int ee2 , int ee3,String n1 , String n2 , String n3)
    {

        TextView a,b,c;
        a = findViewById(R.id.piea);
        b = findViewById(R.id.pieb);
        c = findViewById(R.id.piec);

        int total = ee1 + ee2 + ee3 ;
        int p1 = ee1*100/total;
        int p2 = ee2*100/total;
        int p3 = 100 - (p1+p2);

        n1 = spaceAligner(n1);
        n2 = spaceAligner(n2);
        n3 = spaceAligner(n3);

        a.setText("         "+n1+p1+" %");
        b.setText("         "+n2+p2+" %");
        c.setText("         "+n3+p3+" %");
        // Set the percentage of language used
//        tvR.setText(Integer.toString(40));
//        tvPython.setText(Integer.toString(30));
//        tvCPP.setText(Integer.toString(5));
//        tvJava.setText(Integer.toString(25));

        // Set the data and color to the pie chart
        pieChart.addPieSlice(
                new PieModel(
                        n1,
                        p1,
                        Color.parseColor("#FFA726")));
        pieChart.addPieSlice(
                new PieModel(
                        n2,
                        p2,
                        Color.parseColor("#66BB6A")));
        pieChart.addPieSlice(
                new PieModel(
                        n3,
                        p3,
                        Color.parseColor("#EF5350")));

        // To animate the pie chart
        pieChart.startAnimation();
    }
    private String spaceAligner(String s)
    {
        for (int i = 0 ;i<50-s.length();i++)
        {
            s = s+" ";
        }
        return s;
    }
}