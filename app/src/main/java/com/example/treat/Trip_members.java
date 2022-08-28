package com.example.treat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Trip_members extends AppCompatActivity {
    Button create;
    EditText trip_name , member_name,member_name2,member_name3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip_members);

        create = (Button)findViewById(R.id.create) ;
        trip_name = (EditText)findViewById(R.id.expense_text) ;
        member_name = (EditText)findViewById(R.id.member_text);
        member_name2 = (EditText)findViewById(R.id.member_text2);
        member_name3 = (EditText)findViewById(R.id.member_text3);
        SharedPreferences sharedPreferences = this.getSharedPreferences("TRIP", MODE_PRIVATE);
//        SharedPreferences memberPreferences = this.getSharedPreferences("memberNames", MODE_PRIVATE);
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    String t = trip_name.getText().toString().toUpperCase();
                    String m1 = member_name.getText().toString();
                    String m2 = member_name2.getText().toString();
                    String m3 = member_name3.getText().toString();
                    if (t.length()<3 )
                    {
                        trip_name.setError("Minimum 3 Characters");
                    }
                    else if (m1.length()<3 )
                    {
                        member_name.setError("Minimum 3 Characters");
                    }
                    else if (m2.length()<3 )
                    {
                        member_name2.setError("Minimum 3 Characters");
                    }
                    else if (m3.length()<3 )
                    {
                        member_name3.setError("Minimum 3 Characters");
                    }

                    if (m1.length()>=3 && t.length()>=3 && m3.length()>=3 && m2.length()>=3 )
                    {
                        sharedPreferences.edit().putString(t+"1",m1).apply();
                        sharedPreferences.edit().putString(t+"2",m2).apply();
                        sharedPreferences.edit().putString(t+"3",m3).apply();

                        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                        intent.putExtra("trip_ka_naam",t);
                        startActivity(intent);
                        finish();
                    }
                }
                catch (Exception e)
                {
                    create.setError("!");
                }
            }
        });
    }
}