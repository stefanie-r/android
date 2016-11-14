package com.example.stefanie.ioudatabank;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Andy on 9/11/2015.
 */
public class ContractList extends AppCompatActivity {
    private final String PROVIDER_NAME =  "IOU.provider";
    private final Uri CONTENT_URI = Uri.parse("content://" + PROVIDER_NAME + "/contracts");

    private static ListView listView;
    private ContractListAdapter adapter;
    private ArrayList<Contract> list;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contractlist_main);

        listView = (ListView) findViewById(R.id.list);
        UpdateList();
        adapter = new ContractListAdapter(getBaseContext(), R.layout.contractlist_item, list);
        listView.setAdapter(adapter);
        registerForContextMenu(listView);
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
            UpdateList();
            adapter.UpdateData(list);
            Toast.makeText(ContractList.this, "Contract deleted", Toast.LENGTH_SHORT).show();
        }

        return true;
    }
    public void UpdateList() {
        Cursor cursor = getContentResolver().query(CONTENT_URI, null, null, null, null);
        list = new ArrayList<>();
        while (cursor.moveToNext()) {
            Contract c = new Contract(cursor.getInt(0), cursor.getDouble(1), cursor.getString(2), cursor.getString(3), cursor.getString(4));
            list.add(c);
        }
    }
}
