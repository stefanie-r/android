package com.example.stefanie.ioudatabank;

import android.app.ListActivity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CursorAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static com.example.stefanie.ioudatabank.DatabaseHelper.COLUMN_AMOUNT;
import static com.example.stefanie.ioudatabank.DatabaseHelper.COLUMN_CONTEXT;
import static com.example.stefanie.ioudatabank.DatabaseHelper.COLUMN_ID;
import static com.example.stefanie.ioudatabank.DatabaseHelper.COLUMN_OWED_BY;
import static com.example.stefanie.ioudatabank.DatabaseHelper.COLUMN_OWED_TO;
import static com.example.stefanie.ioudatabank.DatabaseHelper.TABLE_NAME;

/**
 * Created by Stefanie on 25/10/2016.
 */

public class ContractListActivity extends ListActivity {
    private final String PROVIDER_NAME =  "IOU.provider";
    private final Uri CONTENT_URI = Uri.parse("content://" + PROVIDER_NAME + "/contracts");
    //String[] columns ={COLUMN_AMOUNT, COLUMN_OWED_TO, COLUMN_OWED_BY, COLUMN_CONTEXT};
    //int[] listItems ={ R.id.tv_amount, R.id.tv_owed_to, R.id.tv_owed_by,R.id.tv_reason};
    public List<DatabaseContract> contracts;
    public static DatabaseHelper databaseHelper;
    private static ListView listView;
    public static final String TABLE_NAME = "IOUTable";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_debts);
        databaseHelper = new DatabaseHelper(getApplicationContext());
        listView = getListView();
        contracts = getContracts();
        ContractAdapter adapter = new ContractAdapter(getApplicationContext(), R.layout.contract_list_item,contracts);
        listView.setAdapter(adapter);
        registerForContextMenu(listView);
    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

    }
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
        return true;
    }
    // Deleting single contact
    public void deleteContact(DatabaseContract contact) {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        db.delete(TABLE_NAME, COLUMN_ID + " = ?",
                new String[] { String.valueOf(contact.getId()) });
    }

    // Getting All Contacts
    public List<DatabaseContract> getContracts() {
        List<DatabaseContract> contactList = new ArrayList<DatabaseContract>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_NAME;
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                DatabaseContract contact = new DatabaseContract();
                contact.setId(cursor.getInt(0));
                contact.setOwedBy(cursor.getString(1));
                contact.setOwedTo(cursor.getString(2));
                contact.setAmount(cursor.getDouble(3));
                contact.setContext(cursor.getString(4));
                // Adding contact to list
                contactList.add(contact);
            } while (cursor.moveToNext());
        }
        // return contact list
        return contactList;
    }
}
