package com.example.stefanie.volume;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.SeekBar;
import android.widget.Toast;

/**
 * Created by stefanie on 1/08/2016.
 */
public class VolumeActivity extends AppCompatActivity {

    private SeekBar volumeControl = null;
    final int KEY_VOLUME_Value = 50;
    final int KEY_MELDINGEN_Value = 65;
    final int KEY_MEDIA_Value = 45;
    final int KEY_ALARM_Value = 80;

    public static final String CALL_VOLUME = "beltoon";
    public static final String MEDIA_VOLUME = "media";
    public static final String MELDINGEN_VOLUME = "meldingen";
    public static final String ALARM_VOLUME = "alarm";


    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.volume);

        Intent intent = getIntent();
        Integer defVol = intent.getIntExtra(CALL_VOLUME, 0);

        String text = "DEFAULT: " + defVol;
        Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();


        SeekBar sb1 = (SeekBar) findViewById(R.id.seekBarBeltoon);
        SeekBar sb2 = (SeekBar) findViewById(R.id.seekBarMeldingen);
        SeekBar sb3 = (SeekBar) findViewById(R.id.seekbarMedia);
        SeekBar sb4 = (SeekBar) findViewById(R.id.seekBarAlarm);

        sb1.setProgress(KEY_VOLUME_Value);
        sb2.setProgress(KEY_MELDINGEN_Value);
        sb3.setProgress(KEY_MEDIA_Value);
        sb4.setProgress(KEY_ALARM_Value);
        volumeControl = sb1;
        volumeControl.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                SeekBar sb1 = (SeekBar) findViewById(R.id.seekBarBeltoon);
                SeekBar sb2 = (SeekBar) findViewById(R.id.seekBarMeldingen);
                if (check == true) {
                    sb2.setProgress(sb1.getProgress());
                }
            }
            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }
        });
    }
    boolean check = false;

    public void onClickOk(View v){
        Intent resInt = new Intent();
        String text="";
        int id = v.getId();
        SeekBar volume;
        volume = (SeekBar)findViewById(R.id.seekBarAlarm);
        resInt.putExtra(ALARM_VOLUME, volume.getProgress());
        volume = (SeekBar)findViewById(R.id.seekBarBeltoon);
        resInt.putExtra(CALL_VOLUME, volume.getProgress());
        volume = (SeekBar)findViewById(R.id.seekbarMedia);
        resInt.putExtra(MEDIA_VOLUME, volume.getProgress());
        volume = (SeekBar)findViewById(R.id.seekBarMeldingen);
        resInt.putExtra(MELDINGEN_VOLUME, volume.getProgress());
        setResult(RESULT_OK, resInt);
        finish();
    }

    public void onClickReset(View v){
        SeekBar sb1 = (SeekBar) findViewById(R.id.seekBarBeltoon);
        SeekBar sb2 = (SeekBar) findViewById(R.id.seekBarMeldingen);
        SeekBar sb3 = (SeekBar) findViewById(R.id.seekbarMedia);
        SeekBar sb4 = (SeekBar) findViewById(R.id.seekBarAlarm);

        sb1.setProgress(KEY_VOLUME_Value);
        sb2.setProgress(KEY_MELDINGEN_Value);
        sb3.setProgress(KEY_MEDIA_Value);
        sb4.setProgress(KEY_ALARM_Value);
    }

    public void onCheckedClick(View v){
        boolean checked = ((CheckBox) v).isChecked();
        SeekBar sb2 = (SeekBar) findViewById(R.id.seekBarMeldingen);
        if (checked == true){
            check = true;
            sb2.setEnabled(false);
        } else{
            check = false;
            sb2.setEnabled(true);
        }

    }
}