package com.example.stefanie.condiments;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.support.v4.content.CursorLoader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class MainActivity extends AppCompatActivity {
    private static final String PROVIDER_NAME = "com.example.stefanie.condiments.FoodProvider";//is authority
    private static final Uri CONTENT_URI = Uri.parse("content://" + PROVIDER_NAME + "/items");

    private static final String[] descList = {"Spaghetti", "Fries", "Water", "Beer", "Soda"};
    private static final double[] priceList = {11.0, 5.0, 1.0, 2.0, 2.5};

    private ListView listView;
    private SimpleCursorAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addItems();
        listView = (ListView) findViewById(R.id.listview);

        adapter = new SimpleCursorAdapter(
                getBaseContext(),
                R.layout.list_item,null,
                new String[] {"desc", "price"},
                new int[] {R.id.txtName, R.id.txtValue},
                0);
listView.setAdapter(adapter);

        CursorLoader cursorLoader = new CursorLoader(getBaseContext(), CONTENT_URI, null, null, null, null);
        Cursor cursor = cursorLoader.loadInBackground();
        adapter.swapCursor(cursor);
    }
    private void addItems() {
        ContentValues v;
        for (int i = 0; i < descList.length; i++) {
            v = new ContentValues();
            v.put("desc", descList[i]);
            v.put("price", priceList[i]);
            getContentResolver().insert(CONTENT_URI, v);
        }
    }
}
