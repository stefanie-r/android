package com.example.stefanie.os3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListAdapter listAd = ArrayAdapter.createFromResource(this, R.array.operating_sysems, android.R.layout.simple_list_item_1);
        ListView lv_array = (ListView) findViewById(R.id.lv_array);
        lv_array.setAdapter(listAd);
    }


}