package com.example.android.ilfragmentino;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends FragmentActivity
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
