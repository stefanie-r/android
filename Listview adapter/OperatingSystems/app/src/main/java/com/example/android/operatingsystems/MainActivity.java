package com.example.android.operatingsystems;

import android.app.ListActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListAdapter adapter = ArrayAdapter.createFromResource(this, R.array.operatingSystems, R.layout.listitem);
        ListView listView = getListView();
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String text = "Clicked ListItem number " + position;
                Toast popup = Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG);
                popup.show();
            }
        });
    }
}
