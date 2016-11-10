package com.example.stefanie.javamenu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    private static final int EDIT_MENU = 100;
    private static final int ITEM_CREATE = 1;
    private static final int ITEM_UPDATE = 2;
    private static final int ITEM_DELETE = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        menu.add(EDIT_MENU,ITEM_CREATE,0, "Create");
        menu.add(EDIT_MENU, ITEM_UPDATE, 1, "Update");
        menu.add(EDIT_MENU, ITEM_DELETE, 2, "Delete");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
