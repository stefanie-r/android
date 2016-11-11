package com.example.stefanie.os1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {
    private String [] values =
            {"Android", "iPhone", "WindowsMobile",
                    "Blackberry", "WebOS", "Ubuntu",
                    "Windows7", "Max OS X", "Linux", "OS/2"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.list_item, R.id.tv_item, this.values);
        ListView listView = (ListView) findViewById(R.id.lv_array);
        listView.setAdapter(arrayAdapter);
    }
    
}
