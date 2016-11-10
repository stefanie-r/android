package com.example.stefanie.optionsmenuvsactionbarapi10;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        String result ="";
        if (id == R.id.menu_create) {
            result = "Created";
        }
        if (id == R.id.menu_delete) {
            result = "Deleted";
        }
        if (id == R.id.menu_update) {
            result = "Updated";
        }
        Context context = getApplicationContext();

        int duration = Toast.LENGTH_LONG;
        Toast t = Toast.makeText(context, result, duration);
        t.show();
        return super.onOptionsItemSelected(item);
    }

    }
