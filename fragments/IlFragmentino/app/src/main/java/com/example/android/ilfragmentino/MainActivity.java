package com.example.android.ilfragmentino;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
implements WeeksFragment.OnWeekSelectedListener, DaysFragment.OnDaySelectedListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (findViewById(R.id.fragment_container) != null) {
            if (savedInstanceState != null) {
                return;
            }
            WeeksFragment firstFragment = new WeeksFragment();
           firstFragment.setArguments(getIntent().getExtras());
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, firstFragment).commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.AddOption:
                return false;
            case R.id.DeleteOption:
                Toast.makeText(getApplicationContext(), "Deleteoption selected", Toast.LENGTH_SHORT).show();
                return true;
            default:
                break;
        }
        return false;
    }

    public void onWeekSelected(String week){
DaysFragment daysFragment = (DaysFragment) getSupportFragmentManager().findFragmentById(R.id.dagenFragment);
       if (daysFragment != null){
           daysFragment.updateDaysView(week);
       }else{
           DaysFragment newFragment = new DaysFragment();
           Bundle args = new Bundle();
           args.putString(DaysFragment.ARG_POSITION, week);
           newFragment.setArguments(args);
           FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
           transaction.replace(R.id.fragment_container, newFragment);
           transaction.addToBackStack(null);
           transaction.commit();
       }
    }
    public void onDaySelected(String dag){
MenuFragment menuFragment = (MenuFragment) getSupportFragmentManager().findFragmentById(R.id.menuFragment);
        if (menuFragment != null){
            menuFragment.updateMenuView(dag);
        }else{
            MenuFragment newFragment = new MenuFragment();
            Bundle args = new Bundle();
            args.putString(MenuFragment.ARG_POSITION,dag);
            newFragment.setArguments(args);
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container, newFragment);
            transaction.addToBackStack(null);
            transaction.commit();
        }
    }


}
