package com.example.stefanie.volume;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.SeekBar;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    private String filename = "preferences";
    SeekBar beltoon, media, meldingen, alarm;
    CheckBox cbCombi;
    String value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        beltoon = (SeekBar) findViewById(R.id.seekBarBeltoon);
        media = (SeekBar) findViewById(R.id.seekBarMedia);
        meldingen = (SeekBar) findViewById(R.id.seekBarMelding);
        alarm = (SeekBar) findViewById(R.id.seekBarAlarm);
        cbCombi = (CheckBox) findViewById(R.id.checkBox);
        Button okButton = (Button) findViewById(R.id.button);
        readFromExternal();
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveToExternal();
            }
        });
    }private void readFromExternal() {
        if (isExternalStorageReadable()) {
            try {
                File file = new File(Environment.getExternalStorageDirectory(), filename);
                FileInputStream inputStream = new FileInputStream(file);
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                StringBuilder stringBuilder = new StringBuilder();
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append(line + "\n");
                }
                String[] textboxValues = stringBuilder.toString().split("[\n]+");
                value = textboxValues[0];
                beltoon.setProgress(Integer.parseInt(value));
                value = textboxValues[1];
                alarm.setProgress(Integer.parseInt(value));
                value = textboxValues[2];
                media.setProgress(Integer.parseInt(value));
                value = textboxValues[3];
                meldingen.setProgress(Integer.parseInt(value));
                if (textboxValues[4].equals("combiJa")){
                    cbCombi.setChecked(true);
                }else{
                    cbCombi.setChecked(false);
                }
                inputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void saveToExternal() {
        if (isExternalStorageWritable()) {
            File file = new File(Environment.getExternalStorageDirectory(), filename);
            FileOutputStream outputStream;
            String separator = System.getProperty("line.separator");
            int beltoonVolume = beltoon.getProgress();
            int alarmVolume = alarm.getProgress();
            int mediaVolume = media.getProgress();
            int meldingenVolume = meldingen.getProgress();
            boolean combiBeltoonMeldingen = cbCombi.isChecked();

            try {
                outputStream = new FileOutputStream(file);
                String fileContent = beltoonVolume + separator + alarmVolume + separator + mediaVolume + separator + meldingenVolume + separator;
                if (combiBeltoonMeldingen) {
                    fileContent += "combiJa";
                } else {
                    fileContent += "combiNee";
                }
                outputStream.write(fileContent.getBytes());
                outputStream.close();
                Toast.makeText(getApplicationContext(), "Saved settings!", Toast.LENGTH_SHORT).show();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /* Checks if external storage is available for read and write */
    public boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }

    /* Checks if external storage is available to at least read */
    public boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state) ||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            return true;
        }
        return false;
    }
}
