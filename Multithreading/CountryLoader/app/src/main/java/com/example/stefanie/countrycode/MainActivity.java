package com.example.stefanie.countrycode;

import android.app.LoaderManager;
import android.content.ContentValues;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;


import java.util.List;

public class MainActivity extends AppCompatActivity implements
        LoaderManager.LoaderCallbacks<Cursor> {
    private CursorAdapter mCursorAdapter;
    List<Country> countryList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        countryList = CountryData.getCountryList();
        addItems();
        try {
            String[] mWordListColumns = { // A "projection" defines the columns that will be returned for each row
                    DatabaseHelper.COLUMN_NAME,
                    DatabaseHelper.COLUMN_DISPLAYNAME,
                    DatabaseHelper.COLUMN_NUMCODE
            };

            int[] mSettingsListItems = {
                    R.id.nameView,
                    R.id.displayNameView,
                    R.id.numCodeView
            };

            getLoaderManager().initLoader(0, null, this);

            mCursorAdapter = new SimpleCursorAdapter(
                    getApplicationContext(),
                    R.layout.country_list_row,
                    null,
                    mWordListColumns,
                    mSettingsListItems,
                    0);

            ListView mSettingsList = (ListView) findViewById(R.id.countryListView);
            mSettingsList.setAdapter(mCursorAdapter);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void addItems() {
        ContentValues v;
        for (int i = 0; i < countryList.size(); i++) {
            Country country = countryList.get(i);
            v = new ContentValues();
            v.put(DatabaseHelper.COLUMN_NAME, country.getName());
            v.put(DatabaseHelper.COLUMN_DISPLAYNAME, country.getDisplayName());
            v.put(DatabaseHelper.COLUMN_ISO2, country.getIso2());
            v.put(DatabaseHelper.COLUMN_ISO3, country.getIso3());
            v.put(DatabaseHelper.COLUMN_NUMCODE, country.getNumCode());
            getContentResolver().insert(CountryProvider.CONTENT_URI, v);
        }
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        CursorLoader mCursor = null;

        try {
            String[] mProjection = { // A "projection" defines the columns that will be returned for each row
                    DatabaseHelper.COLUMN_ID,
                    DatabaseHelper.COLUMN_NAME,
                    DatabaseHelper.COLUMN_DISPLAYNAME,
                    DatabaseHelper.COLUMN_NUMCODE
            };
            String mSelectionClause = null; // Defines a string to contain the selection clause
            String[] mSelectionArgs = null; // Initializes an array to contain selection arguments
            String mOrderBy = null; // ORDER BY clause

            mCursor = new CursorLoader(this,
                    CountryProvider.CONTENT_URI,
                    mProjection,
                    mSelectionClause,
                    mSelectionArgs,
                    mOrderBy);

            for (int i = 1; i <= 100; i++) {
                Thread.sleep(50);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return mCursor;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        mCursorAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mCursorAdapter.swapCursor(null);
    }
}
