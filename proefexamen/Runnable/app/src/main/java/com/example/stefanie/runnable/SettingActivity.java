package com.example.stefanie.runnable;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import static com.example.stefanie.runnable.R.id.tv_distance;

/**
 * Created by stefanie on 15/11/2016.
 */

public class SettingActivity extends AppCompatActivity{
    public static final String PREFS_NAME = "MyPrefsFile";
    private static EditText et_shoes;
    private static Spinner sp_apps;
    private static SeekBar sb_distance;
    private static CheckBox cb_pastEvents;
    private static TextView tv_distance;
    private int distance;
    private String shoes="shoes";
    private String apps="app";
    private String distances = "distance";
private String events = "events";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        et_shoes = (EditText) findViewById(R.id.et_shoe);
        sp_apps = (Spinner) findViewById(R.id.sp_app);
        sb_distance = (SeekBar) findViewById(R.id.sb_distance);
        cb_pastEvents = (CheckBox) findViewById(R.id.cb_pastGames);
        tv_distance = (TextView) findViewById(R.id.tv_distance);
        getPreferences();
        sb_distance.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                changeDistance(i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });
    }
    public void changeDistance(int i){
        switch (i){
            case 1:
                distance = 1;
                break;
            case 2:
                distance = 5;
                break;
            case 3:
                distance = 10;
                break;
            case 4:
                distance = 21;
                break;
            case 5:
                distance = 42;
                break;
        }
        tv_distance.setText(String.valueOf(distance));
    }
    public void getPreferences(){
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        boolean pastEvents = settings.getBoolean(events, true);
        cb_pastEvents.setChecked(pastEvents);
        String shoeChose = settings.getString(shoes, "Asics");
        et_shoes.setText(shoeChose);
        int appChose = settings.getInt(apps, 1);
        sp_apps.setSelection(appChose);//set the selected item to the position from preferences
        int progress = settings.getInt(distances, 1);
        sb_distance.setProgress(progress);
        changeDistance(progress);
    }

    public void setPreferences(){
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        boolean pastEvents = cb_pastEvents.isChecked();
        String shoeChose = et_shoes.getText().toString();
        int appChose = sp_apps.getSelectedItemPosition();
        int progress = sb_distance.getProgress();
        editor.putBoolean(events, pastEvents);
        editor.putInt(apps, appChose);
        editor.putString(shoes, shoeChose);
        editor.putInt(distances, progress);
        editor.commit();
    }

    @Override
    protected void onResume() {
        super.onResume();
        getPreferences();
    }
    @Override
    protected void onPause() {
        super.onPause();
        setPreferences();
    }
    @Override
    protected void onStop() {//same as onPause but by terminating the app
        super.onStop();
        setPreferences();
    }
}
