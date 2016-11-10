package com.example.stefanie.calinglog;

import android.database.Cursor;
import android.provider.CallLog;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String[] mProjection = {
                CallLog.Calls._ID,
                CallLog.Calls.NUMBER,
                CallLog.Calls.DURATION
        };
        String mSelectionClause = null; // Defines a string to contain the selection clause
        String[] mSelectionArgs = null; // Initializes an array to contain selection arguments
        String mOrderBy = null;

        try {
            Cursor mCursor = getContentResolver().query(
                    CallLog.Calls.CONTENT_URI,  // The content URI of the words table
                    mProjection,                       // The columns to return for each row
                    mSelectionClause,                   // Either null, or the word the user entered
                    mSelectionArgs,                    // Either empty, or the string the user entered
                    mOrderBy);
            if (mCursor == null){
                throw new Exception("error");
            } else if (mCursor.getCount()<1){
                Toast.makeText(getApplicationContext(), "Unsuccesful search", Toast.LENGTH_LONG).show();
            } else{
                String[] mWordListColumns =
                        {
                                Settings.System.NAME,
                                Settings.System.VALUE
                        };
                int[] mWordListItems = { R.id.callnumber, R.id.callduration};
                SimpleCursorAdapter mCursorAdapter = new SimpleCursorAdapter(
                        getApplicationContext(),
                        R.layout.list_item,
                        mCursor,
                        mWordListColumns,
                        mWordListItems,
                        0);
                ListView listView = (ListView)findViewById(R.id.settingsListView);
                listView.setAdapter(mCursorAdapter);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
