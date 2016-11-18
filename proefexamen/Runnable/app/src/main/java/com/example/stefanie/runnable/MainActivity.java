package com.example.stefanie.runnable;

import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity implements RunListFragment.OnRunSelectedListener{
    private ProgressDialog progressDialog;
    List<Run>runs;
    RunProvider provider;
    DbHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (findViewById(R.id.fragment_container) != null) {
            if (savedInstanceState != null) {
                return;
            }
            helper = new DbHelper(getApplicationContext());
            runs = RunUtils.getRuns(getApplicationContext());

            RunListFragment firstFragment = new RunListFragment();
            firstFragment.setArguments(getIntent().getExtras());
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, firstFragment).commit();
            provider = new RunProvider();
        }
    }
    //adding the runs in the db
    public void addItems(){
        try {
            ContentValues v = new ContentValues();
            for (int i = 0; i < runs.size(); i++) {
                Run run = runs.get(i);
                v.put(helper.COLUMN_TITLE, run.getTitle());
                v.put(helper.COLUMN_DATE, run.getDate());
                v.put(helper.COLUMN_DISTANCE, run.getDistance());
                v.put(helper.COLUMN_EVENTURL, run.getEventUrl());
                v.put(helper.COLUMN_JOINED, run.isJoined());
                v.put(helper.COLUMN_ORGANISATION, run.getOrganization());
                v.put(helper.COLUMN_PLACE, run.getLocation());
                v.put(helper.COLUMN_PRICE, run.getPrice());
                getContentResolver().insert(provider.CONTENT_URI, v);
            }
        } catch (Exception e) {
            Log.e("onRunAddInMain", e.getMessage());
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.settings:
                Intent settingsIntent = new Intent(getApplicationContext(),  SettingActivity.class);
                startActivity(settingsIntent);
            default:
                break;
        }
        return false;
    }
    @Override
    public void onRunSelected(int position) {
        RunDetailFragment detailsFragment = (RunDetailFragment)getSupportFragmentManager().findFragmentById(R.id.detailFragment);
        if (detailsFragment != null){
            detailsFragment.updateDetailView(position);
        }else{
            RunDetailFragment newFragment = new RunDetailFragment();
            Bundle args = new Bundle();
            args.putInt(RunDetailFragment.ARG_POSITION, position);
            newFragment.setArguments(args);
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container, newFragment);
            transaction.addToBackStack(null);
            transaction.commit();
        }

    }
    private class AddRun extends AsyncTask<Run, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = ProgressDialog.show(MainActivity.this, "Please wait...", "adding contract", true);
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            progressDialog.dismiss();
            Toast.makeText(MainActivity.this, "participation saved!", Toast.LENGTH_SHORT).show();
        }
        private void UpdateProgresss(int progress) {
            progressDialog.setProgress(progress);
        }

        @Override
        protected Void doInBackground(Run... params) {
            try {
                Run run = params[0];
                ContentValues v = new ContentValues();
                v.put(helper.COLUMN_TITLE, run.getTitle());
                v.put(helper.COLUMN_DATE, run.getDate());
                v.put(helper.COLUMN_DISTANCE, run.getDistance());
                v.put(helper.COLUMN_EVENTURL, run.getEventUrl());
                v.put(helper.COLUMN_JOINED, run.isJoined());
                v.put(helper.COLUMN_ORGANISATION, run.getOrganization());
                v.put(helper.COLUMN_PLACE, run.getLocation());
                v.put(helper.COLUMN_PRICE, run.getPrice());
                getContentResolver().insert(provider.CONTENT_URI, v);
            } catch (Exception e) {
                Log.e("onRunAddInMain", e.getMessage());
            }
            int tel = 100;
            for (int i = 0; i < tel; i++) {
                try {
                    Thread.sleep(50);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return null;
        }
    }
}
