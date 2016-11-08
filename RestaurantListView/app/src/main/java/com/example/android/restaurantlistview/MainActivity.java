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

public class MainActivity extends ListActivity {
private static final String DAY = "dag";
    public static final int DAY_REQUEST_CODE = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListAdapter adapter = ArrayAdapter.createFromResource(this, R.array.weken, R.layout.week_list_layout);
        ListView listView = getListView();
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), DayActivity.class);
                startActivityForResult(intent, DAY_REQUEST_CODE);

            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == DAY_REQUEST_CODE && resultCode == RESULT_OK) {
            String dag = data.getStringExtra(DayActivity.DAY);
            Toast.makeText(getApplicationContext(), dag, Toast.LENGTH_LONG).show();
        }
        }
}
