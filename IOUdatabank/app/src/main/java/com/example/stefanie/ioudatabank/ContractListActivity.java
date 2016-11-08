package com.example.stefanie.ioudatabank;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Stefanie on 25/10/2016.
 */

public class ContractListActivity extends AppCompatActivity {
    public static DatabaseHelper databaseHelper;
    private static ListView listView;
    private ContractListAdapter adapter;
    private List<DatabaseContract> list;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_debts);

        listView = (ListView) findViewById(R.id.list);
        UpdateList();
        adapter = new ContractListAdapter(getBaseContext(), R.layout.contract_list_item, list);
        listView.setAdapter(adapter);
        registerForContextMenu(listView);
    }

    private void UpdateList() {
        list = databaseHelper.getAllContracts();
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
           databaseHelper.deleteContact(list.get(info.position));
            UpdateList();
            adapter.UpdateData(list);
            Toast.makeText(ContractListActivity.this, "Contract deleted", Toast.LENGTH_SHORT).show();
        }

        return true;
    }
}
