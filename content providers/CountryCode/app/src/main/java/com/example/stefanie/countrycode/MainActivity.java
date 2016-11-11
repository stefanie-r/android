package com.example.stefanie.countrycode;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.support.v4.content.CursorLoader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String AUTHORITY = "com.example.stefanie.countrycode.CountryProvider";
    private static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/countries");
    List<Country> countryList;
    private ListView listView;
    private SimpleCursorAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        countryList = CountryData.getCountryList();

        addItems();
        listView = (ListView) findViewById(R.id.countryListView);

        String[] mWordListColumns = {DatabaseHelper.COLUMN_NAME, DatabaseHelper.COLUMN_DISPLAYNAME, DatabaseHelper.COLUMN_NUMCODE};
        int[] mSettingsListItems = {R.id.nameView, R.id.displayNameView, R.id.numCodeView};
        adapter = new SimpleCursorAdapter(getBaseContext(),R.layout.country_list_row, null, mWordListColumns, mSettingsListItems, 0);

        listView.setAdapter(adapter);

        CursorLoader cursorLoader = new CursorLoader(getBaseContext(), CONTENT_URI, null, null, null, null);
        Cursor cursor = cursorLoader.loadInBackground();
        adapter.swapCursor(cursor);
    }

    private void addItems() {
        ContentValues v;
        for (int i = 0; i < countryList.size(); i++) {
            Country country =countryList.get(i);
            v = new ContentValues();
            v.put(DatabaseHelper.COLUMN_NAME, country.getName());
            v.put(DatabaseHelper.COLUMN_DISPLAYNAME, country.getDisplayName());
            v.put(DatabaseHelper.COLUMN_ISO2, country.getIso2());
            v.put(DatabaseHelper.COLUMN_ISO3, country.getIso3());
            v.put(DatabaseHelper.COLUMN_NUMCODE, country.getNumCode());
            getContentResolver().insert(CONTENT_URI, v);
        }
    }

}
