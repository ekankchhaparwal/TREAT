package com.example.treat;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;

public class Expenses_List extends AppCompatActivity {
    TextView name;
    EditText newExpense;
    ListView listview5;
    Button button,button5;
    ArrayList<String> Expenses = new ArrayList<>();
    ArrayList<String> Stroage_Expenses = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expenses_list);

        listview5 = findViewById(R.id.listview5);
        name = findViewById(R.id.name);
        button = findViewById(R.id.button2);
        newExpense = findViewById(R.id.add_expense_text);
        button5 = findViewById(R.id.button5);
        Intent intent = getIntent();
        String TREAT_NAME = intent.getStringExtra("trackingMemberExpenses");
        String TRIP_NAME  = intent.getStringExtra("TRIP_NAME");

        SharedPreferences sharedPreferences = this.getSharedPreferences("TRIP", MODE_PRIVATE);

        try {
            Stroage_Expenses = (ArrayList<String>)ObjectSerializer.deserialize(sharedPreferences.getString(TRIP_NAME+TREAT_NAME,ObjectSerializer.serialize(new ArrayList<String>())));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Expenses = Stroage_Expenses;
        name.setText(TREAT_NAME + "       :- "+ " Expenses");
        ExpenseConvertView expenseConvertView = new ExpenseConvertView(Expenses_List.this,R.layout.custom_expenselist,Expenses);
        listview5.setAdapter(expenseConvertView);

        Intent intent1 = new Intent(Expenses_List.this,Particular_TripDetails.class);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String expense = newExpense.getText().toString();
                if (expense.length()>0)
                {
                    Expenses.add(expense);
                   makeToast("Expense Added");
                    listview5.setAdapter(expenseConvertView);
                    try {
                        sharedPreferences.edit().putString(TRIP_NAME+TREAT_NAME,ObjectSerializer.serialize(Expenses)).apply();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    newExpense.setText("");
                }
                else
                {
                    newExpense.setError("Enter Valid Number");
                }
                if (Expenses.size()>0)
                {
                    int a = 0;
                    for (int i = 0;i<Expenses.size();i++)
                    {
                        a = Integer.parseInt(Expenses.get(i))+a;
                    }
                    sharedPreferences.edit().putInt(TRIP_NAME+TREAT_NAME+"exp",a).apply();
                    int x = (Integer)sharedPreferences.getInt(TRIP_NAME+TREAT_NAME+"exp",0);

                }
            }
        });
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);
            }
        });


        listview5.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String presentAmount = adapterView.getItemAtPosition(i).toString();
                Toast.makeText(Expenses_List.this, adapterView.getItemAtPosition(i).toString(), Toast.LENGTH_SHORT).show();

                final AlertDialog.Builder alert = new AlertDialog.Builder(Expenses_List.this);

                View mview = getLayoutInflater().inflate(R.layout.custom_dialog_box,null);
                TextView presentExpense= mview.findViewById(R.id.textView3);
                TextView st = mview.findViewById(R.id.textView6);
                EditText updateExpense = mview.findViewById(R.id.updateExpense);
                Button updatedButton = mview.findViewById(R.id.updated);
                Button cancel = mview.findViewById(R.id.cancelUpdate);

                alert.setView(mview);

                final AlertDialog alertDialog = alert.create();
                alertDialog.setCanceledOnTouchOutside(false);
                presentExpense.setText("Your current Expense is : \u20B9"+presentAmount);
                updatedButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        String newExpense = updateExpense.getText().toString();
                        if (newExpense.length()==0)
                        {
                            updateExpense.setError("Add new Expense");
                        }
                        else
                        {
                            int nE = Integer.parseInt(newExpense);
                            Expenses.set(i,newExpense);
                            listview5.setAdapter(expenseConvertView);
                            try {
                                sharedPreferences.edit().putString(TRIP_NAME+TREAT_NAME,ObjectSerializer.serialize(Expenses)).apply();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            if (Expenses.size()>0)
                            {
                                int a = 0;
                                for (int i = 0;i<Expenses.size();i++)
                                {
                                    a = Integer.parseInt(Expenses.get(i))+a;
                                }
                                sharedPreferences.edit().putInt(TRIP_NAME+TREAT_NAME+"exp",a).apply();
                                int x = (Integer)sharedPreferences.getInt(TRIP_NAME+TREAT_NAME+"exp",0);

                            }
                            try {
                                Thread.sleep(200);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            alertDialog.dismiss();
                        }
                    }
                });
                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        alertDialog.dismiss();
                    }
                });
                alertDialog.show();
            }
        });

    }
    public void makeToast(String s)
    {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }
}