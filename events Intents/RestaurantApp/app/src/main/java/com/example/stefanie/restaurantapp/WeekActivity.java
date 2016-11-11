package com.example.stefanie.restaurantapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

/**
 * Created by stefanie on 1/08/2016.
 */
public class WeekActivity extends AppCompatActivity {

    public final static String dagVar = "com.example.stefanie.restaurantapp.MESSAGE";
    public final static String weekVar = "com.example.stefanie.restaurantapp.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_week);

        Intent intent = getIntent();
        String week = intent.getStringExtra(WeekActivity.weekVar);

        TextView view = (TextView)findViewById(R.id.week);
        view.setText(week);
    }

    public void onClick(View v){
        int id = v.getId();
        String dag = "";
        if(id == R.id.buttonMaan){
            dag = "Maandag";
        }
        else if(id == R.id.buttonDins){
            dag = "Dinsdag";
        }
        else if(id == R.id.buttonWoen){
            dag = "Woensdag";
        }
        else if(id == R.id.buttonDond){
            dag = "Donderdag";
        }
        else if(id == R.id.buttonVrijd){
            dag = "Vrijdag";
        }
        Intent intent = new Intent(this, DagActivity.class);
        intent.putExtra(dagVar, dag);
        startActivity(intent);
    }

}
