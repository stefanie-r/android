package com.example.stefanie.volume;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private SeekBar volumeControl = null;
    public static final String KEY_VOLUME = "Beltoon";

    public static final int VOLUME_REQUEST_CODE = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickVolume(View v) {
        Intent volInt = new Intent(getApplicationContext(), VolumeActivity.class);
        volInt.putExtra(VolumeActivity.CALL_VOLUME, 50);
        startActivityForResult(volInt, VOLUME_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == VOLUME_REQUEST_CODE && resultCode == RESULT_OK) {
            int call_volume = data.getIntExtra(VolumeActivity.CALL_VOLUME, 0);
            int media_volume = data.getIntExtra(VolumeActivity.MEDIA_VOLUME, 0);
            int meldingen_volume = data.getIntExtra(VolumeActivity.MELDINGEN_VOLUME, 0);
            int alarm_volume = data.getIntExtra(VolumeActivity.ALARM_VOLUME, 0);

            String msg = "Beltoon: " + call_volume + "\n" +
                    "Media volume: " + media_volume + "\n" +
                    "Meldingen volume: " + meldingen_volume + "\n" +
                    "Alarm volume: " + alarm_volume;
            Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
        }
    }
}