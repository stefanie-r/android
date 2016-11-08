package com.example.stefanie.ioudatabank;

import android.content.Intent;
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

import static java.security.AccessController.getContext;

public class MainActivity extends AppCompatActivity {
    private static EditText et_amount;
    private static Spinner sp_owedto;
    private static Spinner sp_owedby;
    private static EditText et_context;
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
            double amount = Double.parseDouble(et_amount.getText().toString());
            String owedTo = sp_owedto.getSelectedItem().toString();
            String owedBy = sp_owedby.getSelectedItem().toString();
            String context = et_context.getText().toString();
            DatabaseContract contract = new DatabaseContract(context, amount,owedTo, owedBy);
            databaseHelper.addContact(contract);
            Toast.makeText(getApplicationContext(), "Contract added", Toast.LENGTH_SHORT).show();
        }
    };
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
