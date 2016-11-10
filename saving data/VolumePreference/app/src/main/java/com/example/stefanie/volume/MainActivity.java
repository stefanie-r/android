package com.example.stefanie.volume;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.SeekBar;

public class MainActivity extends AppCompatActivity {

    public static final String PREFS_NAME = "MyPrefsFile";
    public String combinatie = "belvolume is Meldingvolume";
    public String belvolume = "belvolume";
    public String meldingvolume = "meldingvolume";
    public String alarmvolume = "alarmvolume";
    public String mediavolume = "mediavolume";
    SeekBar belBar;
    SeekBar mediaBar;
    SeekBar meldingenBar;
    SeekBar alarmBar;
    CheckBox combinatieBelToonMeldingen;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        belBar = (SeekBar) findViewById(R.id.seekBarBeltoon);
        mediaBar = (SeekBar) findViewById(R.id.seekBarMedia);
        meldingenBar = (SeekBar) findViewById(R.id.seekBarMelding);
        alarmBar = (SeekBar) findViewById(R.id.seekBarAlarm);
        combinatieBelToonMeldingen = (CheckBox) findViewById(R.id.checkBox);
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        boolean volBelIsVolMelding = settings.getBoolean(combinatie, false);
        int belVolume = settings.getInt(belvolume, 0);
        int mediaVolume = settings.getInt(mediavolume, 0);
        int alarmVolume = settings.getInt(alarmvolume, 0);
        int meldingenVolume = settings.getInt(meldingvolume, 0);
        belBar.setProgress(belVolume);
        mediaBar.setProgress(mediaVolume);
        alarmBar.setProgress(alarmVolume);
        meldingenBar.setProgress(meldingenVolume);
        combinatieBelToonMeldingen.setChecked(volBelIsVolMelding);

    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        boolean volBelIsVolMelding = combinatieBelToonMeldingen.isChecked();
        int belVolume = belBar.getProgress();
        int mediaVolume = mediaBar.getProgress();
        int alarmVolume = alarmBar.getProgress();
        int meldingenVolume = meldingenBar.getProgress();
        editor.putBoolean(combinatie, volBelIsVolMelding);
        editor.putInt(belvolume, belVolume);
        editor.putInt(mediavolume, mediaVolume);
        editor.putInt(alarmvolume, alarmVolume);
        editor.putInt(meldingvolume, meldingenVolume);
        editor.commit();

    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        boolean volBelIsVolMelding = settings.getBoolean(combinatie, false);
        int belVolume = settings.getInt(belvolume, 0);
        int mediaVolume = settings.getInt(mediavolume, 0);
        int alarmVolume = settings.getInt(alarmvolume, 0);
        int meldingenVolume = settings.getInt(meldingvolume, 0);
        belBar.setProgress(belVolume);
        mediaBar.setProgress(mediaVolume);
        alarmBar.setProgress(alarmVolume);
        meldingenBar.setProgress(meldingenVolume);
        combinatieBelToonMeldingen.setChecked(volBelIsVolMelding);
    }
    @Override
    protected void onStop(){
        super.onStop();
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        boolean volBelIsVolMelding = combinatieBelToonMeldingen.isChecked();
        int belVolume = belBar.getProgress();
        int mediaVolume = mediaBar.getProgress();
        int alarmVolume = alarmBar.getProgress();
        int meldingenVolume = meldingenBar.getProgress();
        editor.putBoolean(combinatie, volBelIsVolMelding);
        editor.putInt(belvolume, belVolume);
        editor.putInt(mediavolume, mediaVolume);
        editor.putInt(alarmvolume, alarmVolume);
        editor.putInt(meldingvolume, meldingenVolume);
        editor.commit();
    }
}
