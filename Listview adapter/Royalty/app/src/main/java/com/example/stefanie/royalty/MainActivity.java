package com.example.stefanie.royalty;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ArrayAdapterWithImages arrayAdapter;
    private boolean isFrench, isEnglish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        isFrench = false;
        isEnglish = true;
        setContentView(R.layout.activity_main);
        registerForContextMenu(findViewById(R.id.listView));
        List<String> englishRoyals = MockData.getRoyalsOfEngland();
        ListView listView = (ListView) findViewById(R.id.listView);

        arrayAdapter = new ArrayAdapterWithImages(getApplicationContext(), R.layout.list_item, R.id.text1, englishRoyals);
        listView.setAdapter(arrayAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_lists, menu);
        return true;
    }

    private void switchAdapterToEngland() {
        isEnglish = true;
        isFrench = false;
        getSupportActionBar().setTitle(R.string.english_royals);
        arrayAdapter.clear();
        for (String s : MockData.getRoyalsOfEngland()) {
            arrayAdapter.add(s);
        }
    }

    private void switchAdapterToFrance() {
        isFrench = true;
        isEnglish = false;
        getSupportActionBar().setTitle(R.string.french_royals);
        arrayAdapter.clear();
        for (String s : MockData.getRoyalsOfFrance()) {
            arrayAdapter.add(s);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.optionEngland:
                if (!isEnglish)
                    switchAdapterToEngland();
                else
                    Toast.makeText(MainActivity.this,
                            "England is already being shown.",
                            Toast.LENGTH_SHORT).show();
                return true;
            case R.id.optionFrench:
                if (!isFrench)
                    switchAdapterToFrance();
                else
                    Toast.makeText(MainActivity.this,
                            "France is already being shown.",
                            Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        AdapterView.AdapterContextMenuInfo mi = (AdapterView.AdapterContextMenuInfo) menuInfo;
        menu.setHeaderTitle(arrayAdapter.getItem(mi.position));
        inflater.inflate(R.menu.context_menu, menu);
    }


    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.optionDelete:
                AdapterView.AdapterContextMenuInfo info =
                        (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
                arrayAdapter.remove(arrayAdapter.getItem(info.position));
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }
}
