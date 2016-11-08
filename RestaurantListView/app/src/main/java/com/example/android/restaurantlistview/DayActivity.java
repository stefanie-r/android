package com.example.android.restaurantlistview;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

/**
 * Created by stefanie on 27/09/2016.
 */

public class DayActivity extends ListActivity {

    public static final String DAY = "dag";
    public static final int DAY_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = getIntent();

        ListAdapter adapter = ArrayAdapter.createFromResource(this, R.array.days, R.layout.day_list_layout);
        ListView listView = getListView();
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent resInt = new Intent();
                String[] some_array = getResources().getStringArray(R.array.days);
                String text = some_array[position].toString();
                resInt.putExtra(DAY, text);
                setResult(RESULT_OK, resInt);
                finish();
            }
        });
    }
}

