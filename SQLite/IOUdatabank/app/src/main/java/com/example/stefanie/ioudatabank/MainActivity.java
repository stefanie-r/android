package com.example.stefanie.ioudatabank;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import static com.example.stefanie.ioudatabank.DatabaseHelper.COLUMN_AMOUNT;
import static com.example.stefanie.ioudatabank.DatabaseHelper.COLUMN_CONTEXT;
import static com.example.stefanie.ioudatabank.DatabaseHelper.COLUMN_OWED_BY;
import static com.example.stefanie.ioudatabank.DatabaseHelper.COLUMN_OWED_TO;
import static com.example.stefanie.ioudatabank.DatabaseHelper.TABLE_NAME;
import static java.security.AccessController.getContext;

public class MainActivity extends AppCompatActivity {
    private static final String AUTHORITY = "com.example.stefanie.ioudatabank";
    private static EditText et_amount;
    private static Spinner sp_owedto;
    private static Spinner sp_owedby;
    private static EditText et_context;
    double amount;
    String owedTo;
    String owedBy;
    String context;
    private static Button bt_add;
    public static DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        databaseHelper = new DatabaseHelper(this.getBaseContext());
        et_amount = (EditText) findViewById(R.id.et_amount);
        sp_owedto = (Spinner) findViewById(R.id.sp_to);
        sp_owedby = (Spinner) findViewById(R.id.sp_from);
        et_context = (EditText) findViewById(R.id.et_context);
        bt_add = (Button) findViewById(R.id.btn_add);
        bt_add.setOnClickListener(add_Contract);
    }

    final View.OnClickListener add_Contract = new View.OnClickListener() {
        public void onClick(final View v) {
            amount = Double.parseDouble(et_amount.getText().toString());
            owedTo = sp_owedto.getSelectedItem().toString();
            owedBy = sp_owedby.getSelectedItem().toString();
            context = et_context.getText().toString();
            addContact();
            Toast.makeText(getApplicationContext(), "Contract added", Toast.LENGTH_SHORT).show();
        }
    };
    // Adding new contact
    public void addContact() {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_OWED_BY, owedBy);
        values.put(COLUMN_OWED_TO, owedTo);
        values.put(COLUMN_AMOUNT, amount);
        values.put(COLUMN_CONTEXT, context);
        long rowInserted = db.insert(TABLE_NAME, null, values);
        db.close();
        if (rowInserted != -1)
            Toast.makeText(getApplicationContext(), "New row added, row id: " + rowInserted, Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(getApplicationContext(), "Something wrong", Toast.LENGTH_SHORT).show();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.option_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.show_list:
                Intent i = new Intent(MainActivity.this, ContractListActivity.class);
                startActivity(i);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
