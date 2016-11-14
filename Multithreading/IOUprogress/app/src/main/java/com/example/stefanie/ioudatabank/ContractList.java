package com.example.stefanie.ioudatabank;

import android.app.LoaderManager;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.drm.ProcessedData;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import java.util.ArrayList;

public class ContractList extends AppCompatActivity implements
        LoaderManager.LoaderCallbacks<Cursor> {
    private final String PROVIDER_NAME =  "IOU.provider";
    private final Uri CONTENT_URI = Uri.parse("content://" + PROVIDER_NAME + "/contracts");
    private static ListView listView;
    private CursorAdapter mCursorAdapter;
    private ArrayList<Contract> list;
    private ProgressDialog progressDialog;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contractlist_main);

        listView = (ListView) findViewById(R.id.list);

        try {
            String[] mWordListColumns = { // A "projection" defines the columns that will be returned for each row
                    Helper.COLUMN_OWEDBY,
                    Helper.COLUMN_OWEDTO,
                    Helper.COLUMN_AMOUNT,
                    Helper.COLUMN_CONTEXT
            };

            int[] mSettingsListItems = {
                    R.id.tv_fromList,
                    R.id.tv_toList,
                    R.id.tv_amountList,
                    R.id.tv_contextList
            };
            getLoaderManager().initLoader(0, null, this);
            mCursorAdapter = new SimpleCursorAdapter(getApplicationContext(), R.layout.contractlist_item, null, mWordListColumns, mSettingsListItems, 0);
            listView.setAdapter(mCursorAdapter);
            registerForContextMenu(listView);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        if (v.getId() == R.id.list) {
            AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
            menu.setHeaderTitle(list.get(info.position).getContext());
            menu.add("Remove");
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
        if (item.getTitle() == "Remove") {
            getContentResolver().delete(Uri.withAppendedPath(CONTENT_URI, list.get(info.position).getId() + ""), null, null);
            getLoaderManager().restartLoader(0, null, this);
            Toast.makeText(ContractList.this, "Contract deleted", Toast.LENGTH_SHORT).show();
        }
        return true;
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        android.content.CursorLoader mCursor = null;

        try {
            String[] mProjection = { // A "projection" defines the columns that will be returned for each row
                    Helper.COLUMN_ID,
                    Helper.COLUMN_OWEDBY,
                    Helper.COLUMN_OWEDTO,
                    Helper.COLUMN_AMOUNT,
                    Helper.COLUMN_CONTEXT
            };
            String mSelectionClause = null; // Defines a string to contain the selection clause
            String[] mSelectionArgs = null; // Initializes an array to contain selection arguments
            String mOrderBy = null; // ORDER BY clause
            mCursor = new android.content.CursorLoader(this, ContractProvider.CONTENT_URI, mProjection, mSelectionClause, mSelectionArgs, mOrderBy);
        }
        catch(Exception ex) {
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

