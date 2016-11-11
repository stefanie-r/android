package com.example.stefanie.restaurantapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    public final static String weekVar = "com.example.stefanie.restaurantapp.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weken_overzicht);
    }

    public void onClick(View v) {
        int id = v.getId();
        String week = "";

        if(id == R.id.Eerste){
            week = "07 - 11 okt 2013";
        }
        else if(id == R.id.Tweede){
            week = "14 - 18 okt 2013";
        }
        else if(id == R.id.Derde){
            week = "21 - 25 okt 2013";
        }
        Intent intent = new Intent(this, WeekActivity.class);
        intent.putExtra(weekVar, week);
        startActivity(intent);
    }
}
