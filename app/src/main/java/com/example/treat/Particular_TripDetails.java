package com.example.treat;

import androidx.appcompat.app.AppCompatActivity;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class Particular_TripDetails extends AppCompatActivity {
    TextView welcome_text,expenseText1,expenseText2,expenseText3,google;
    LinearLayout linearLayout3;
    FloatingActionButton expAdd1,expAdd2,expAdd3;
    Button button3;

    ArrayList<String>  Expenses1 = new ArrayList<>();
    ArrayList<String>  Expenses2 = new ArrayList<>();
    ArrayList<String>  Expenses3 = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_particular_trip_details);

        expAdd1 = findViewById(R.id.member_expense1);
        expAdd2 = findViewById(R.id.member_expense2);
        button3 = findViewById(R.id.cancelUpdate);
        expAdd3 = findViewById(R.id.member_expense3);
        welcome_text = findViewById(R.id.welcome_text);
        linearLayout3 = findViewById(R.id.linearlayout_22);
        expenseText1 = findViewById(R.id.expensetext);
        expenseText2 = findViewById(R.id.expensetext2);
        expenseText3 = findViewById(R.id.expensetext3);
        google = findViewById(R.id.google);
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("TRIP", Context.MODE_PRIVATE);
        Intent intent1 = getIntent();
        Intent intent3 = getIntent();
        String current_trip = intent1.getStringExtra("currentTripName");
        welcome_text.setText(current_trip+" TREAT");

        String s1 = sharedPreferences.getString(current_trip+"1","");
        String s2 = sharedPreferences.getString(current_trip+"2","");
        String s3 = sharedPreferences.getString(current_trip+"3","");

        int e1 = sharedPreferences.getInt(current_trip+s1+"exp",0);
        int e2 = sharedPreferences.getInt(current_trip+s2+"exp",0);
        int e3 = sharedPreferences.getInt(current_trip+s3+"exp",0);

        welcome_text.setText(current_trip+" TREAT");
        expenseText1.setText("    "+s1+"           "+"\u20B9"+e1);
        expenseText2.setText("    "+s2+"           "+"\u20B9"+e2);
        expenseText3.setText("    "+s3+"           "+"\u20B9"+e3);


        expAdd1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Particular_TripDetails.this,Expenses_List.class);
                intent.putExtra("trackingMemberExpenses",s1);
                intent.putExtra("TRIP_NAME",current_trip);
                startActivity(intent);
                finish();
            }
        });

        expAdd2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Particular_TripDetails.this,Expenses_List.class);
                intent.putExtra("trackingMemberExpenses",s2);
                intent.putExtra("TRIP_NAME",current_trip);
                startActivity(intent);
                finish();
            }
        });

        expAdd3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Particular_TripDetails.this,Expenses_List.class);
                intent.putExtra("TRIP_NAME",current_trip);
                intent.putExtra("trackingMemberExpenses",s3);
                startActivity(intent);
                finish();
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            Intent finalintent = new Intent(Particular_TripDetails.this,Analysis_of_Expenses.class);
            finalintent.putExtra("data1",e1);
            finalintent.putExtra("data2",e2);
            finalintent.putExtra("data3",e3);

            finalintent.putExtra("name1",s1);
            finalintent.putExtra("name2",s2);
            finalintent.putExtra("name3",s3);
            startActivity(finalintent);

            }
        });

        google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_WEB_SEARCH);
                i.putExtra(SearchManager.QUERY,current_trip + " Beautiful places and restaurants ");
                startActivity(i);
//                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/"+current_trip)));
            }
        });
    }

}