package com.example.stefanie.arrayadapter3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayAdapter<CharSequence> arrAd = ArrayAdapter.createFromResource(this,
                R.array.operating_sysems, android.R.layout.simple_list_item_1);

        ListView lv_array = (ListView) findViewById(R.id.lv_array);
        lv_array.setAdapter(arrAd);


        lv_array.setOnItemClickListener(new ListViewHandler());
    }

    private class ListViewHandler implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> av, View v, int pos, long id) {
            String text = "Clicked ListItem number " + pos;
            Toast popup = Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG);
            popup.show();
        }
    }
}