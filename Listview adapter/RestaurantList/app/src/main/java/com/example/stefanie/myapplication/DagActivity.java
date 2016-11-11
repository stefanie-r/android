package com.example.stefanie.myapplication;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

/**
 * Created by stefanie on 13/11/2015.
 */
public class DagActivity  extends ListActivity {
    String[] dagen = {
            "maandag",
            "dinsdag",
            "woensdag",
            "donderdag",
            "vrijdag"
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = getIntent();

        this.setListAdapter(new ArrayAdapter<String>(
                this, R.layout.daglist,
                R.id.dagname, dagen));

        ListView lv = getListView();
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String dag = dagen[position].toString();
                Toast test = Toast.makeText(getApplicationContext(), dag, Toast.LENGTH_SHORT);
                test.show();

            }
        });
    }
}