package com.example.stefanie.election;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.SeekBar;
import android.widget.TextView;

/**
 * Created by stefanie on 15/11/2016.
 */

public class SettingsActivity extends AppCompatActivity {
    private int year;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        final TextView years = (TextView) findViewById(R.id.tv_year);
        SeekBar sbYear = (SeekBar) findViewById(R.id.sb_electionYear);
        sbYear.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int min = 1800;
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                int size = 4;
                year = min + (i*4);
                years.setText(year);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });
    }
}
