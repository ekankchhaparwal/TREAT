package com.example.treat;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView listview;
    FloatingActionButton floatingActionButton;
    ImageView add_new_trip;
    TextView text;
    Button button;
    ArrayList <String> trip_storage_name = new ArrayList<>();
    ArrayList <String> add_name = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Getting the ID's
        listview = findViewById(R.id.listview1);
        text = (TextView)findViewById(R.id.checktext);
        floatingActionButton = findViewById(R.id.addtrip);
        listview = findViewById(R.id.listview1);
        button = findViewById(R.id.button);
        SharedPreferences sharedPreferences = this.getSharedPreferences("TRIP", MODE_PRIVATE);


        try {
            trip_storage_name = (ArrayList<String>)ObjectSerializer.deserialize(sharedPreferences.getString("MY_TRIPS",ObjectSerializer.serialize(new ArrayList<String>())));
        } catch (IOException e) {
            e.printStackTrace();
        }
        add_name = trip_storage_name;
        AddTrips addTrips = new AddTrips(MainActivity.this, R.layout.custom_listview,add_name);
        listview.setAdapter(addTrips);

        // add button to go to next activity
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Thread thread = new Thread() {
                    public void run() {
                        try {
                            sleep(400);
                        }
                        catch (Exception e)
                        {
                            e.printStackTrace();
                        }
                        finally {
                            Intent i = new Intent(getApplicationContext(),Trip_members.class);
                            startActivity(i);
                        }
                    }
                }; thread.start();
            }
        });

        //Getting the tripnames from next activity
        Intent intent = getIntent();
        String str_trip = intent.getStringExtra("trip_ka_naam");
        text.setText(str_trip);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String t = text.getText().toString();
                try {
                    if (t.length()>0)
                    {
                        add_name.add(t);
                        listview.setAdapter(addTrips);
                        sharedPreferences.edit().putString("MY_TRIPS",ObjectSerializer.serialize(add_name)).apply();
                        text.setText("");
                    }
                }
                catch (Exception e)
                {
                    text.setText("error");
                }
            }
        });

        // Deleting an Item from my tripList
        listview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int trip_item, long id) {
                String currentTripName = adapterView.getItemAtPosition((trip_item)).toString();
                new AlertDialog.Builder(MainActivity.this)
                        .setIcon(android.R.drawable.ic_delete)
                        .setTitle("Are you sure ?")
                        .setMessage("Would you like to delete the "+ currentTripName + " TREAT")
                        .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
//
                                String d1 = sharedPreferences.getString(currentTripName+"1","");
                                String d2 = sharedPreferences.getString(currentTripName+"2","");
                                String d3 = sharedPreferences.getString(currentTripName+"3","");

                                sharedPreferences.edit().remove(currentTripName+d1).apply();
                                sharedPreferences.edit().remove(currentTripName+d2).apply();
                                sharedPreferences.edit().remove(currentTripName+d3).apply();

                                sharedPreferences.edit().remove(currentTripName+"1").apply();
                                sharedPreferences.edit().remove(currentTripName+"2").apply();
                                sharedPreferences.edit().remove(currentTripName+"3").apply();

                                add_name.remove(trip_item);
                                addTrips.notifyDataSetChanged();

                                try {
                                    sharedPreferences.edit().putString("MY_TRIPS",ObjectSerializer.serialize(add_name)).apply();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        })

                        .setNegativeButton("NO", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }

                        }) .create().show();
                return true;
            }
        });
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {


                String currentTripName = adapterView.getItemAtPosition((position)).toString();
                Intent intent1 = new Intent(getApplicationContext(),Particular_TripDetails.class);
                intent1.putExtra("currentTripName",currentTripName);
                startActivity(intent1);
            }
        });
    }
    }
