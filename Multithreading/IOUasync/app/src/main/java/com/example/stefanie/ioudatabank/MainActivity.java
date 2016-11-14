package com.example.stefanie.ioudatabank;

import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import static com.example.stefanie.ioudatabank.Helper.COLUMN_AMOUNT;
import static com.example.stefanie.ioudatabank.Helper.COLUMN_CONTEXT;
import static com.example.stefanie.ioudatabank.Helper.COLUMN_OWEDBY;
import static com.example.stefanie.ioudatabank.Helper.COLUMN_OWEDTO;

public class MainActivity extends AppCompatActivity {
    private final String PROVIDER_NAME =  "IOU.provider";
    private final Uri CONTENT_URI = Uri.parse("content://" + PROVIDER_NAME + "/contracts");
    public static final String COLUMN_AMOUNT = "amount";
    public static final String COLUMN_OWEDTO = "owedto";
    public static final String COLUMN_OWEDBY = "owedby";
    public static final String COLUMN_CONTEXT = "context";

    private static EditText et_amount;
    private static Spinner sp_owedto;
    private static Spinner sp_owedby;
    private static EditText et_context;
    private static Button bt_add;
    public static Helper helper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        helper = new Helper(this.getBaseContext());
        et_amount = (EditText) findViewById(R.id.et_amount);
        sp_owedto = (Spinner) findViewById(R.id.sp_to);
        sp_owedby = (Spinner) findViewById(R.id.sp_from);
        et_context = (EditText) findViewById(R.id.et_context);
        bt_add = (Button) findViewById(R.id.btn_add);

        bt_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Contract c = new Contract(Double.valueOf(et_amount.getText().toString()), sp_owedto.getSelectedItem().toString(), sp_owedby.getSelectedItem().toString(), et_context.getText().toString());
                AddContract task = new AddContract();
                task.execute(c);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.show_list:
                Intent i = new Intent(MainActivity.this, ContractList.class);
                startActivity(i);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private class AddContract extends AsyncTask<Contract, Void, Void> {

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Toast.makeText(MainActivity.this, "contract added!", Toast.LENGTH_SHORT).show();
        }

        @Override
        protected Void doInBackground(Contract... params) {
            try {
                Contract contract = params[0];
                ContentValues v = new ContentValues();
                v.put(COLUMN_AMOUNT, contract.getAmount());
                v.put(COLUMN_OWEDTO, contract.getOwedTo());
                v.put(COLUMN_OWEDBY, contract.getOwedBy());
                v.put(COLUMN_CONTEXT, contract.getContext());
                getContentResolver().insert(CONTENT_URI, v);
            } catch (Exception e) {
                Log.e("onContractAddInMain", e.getMessage());
            }
            return null;
        }
    }
}


