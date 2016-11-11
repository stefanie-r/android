package com.example.stefanie.volume;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.CheckBox;
        import android.widget.SeekBar;
        import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private SeekBar volumeControl = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SeekBar sb1 = (SeekBar) findViewById(R.id.seekBarBeltoon);
        SeekBar sb2 = (SeekBar) findViewById(R.id.seekBarMelding);
        SeekBar sb3 = (SeekBar) findViewById(R.id.seekBarMedia);
        SeekBar sb4 = (SeekBar) findViewById(R.id.seekBarAlarm);

        sb1.setProgress(50);
        sb2.setProgress(25);
        sb3.setProgress(75);
        sb4.setProgress(100);
        volumeControl = sb1;
        volumeControl.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                SeekBar sb1 = (SeekBar) findViewById(R.id.seekBarBeltoon);
                SeekBar sb2 = (SeekBar) findViewById(R.id.seekBarMelding);
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
        String text="";
        int id = v.getId();
        SeekBar volume;
        volume = (SeekBar)findViewById(R.id.seekBarAlarm);
        text += "Alarm: " + volume.getProgress()+ "\n";
        volume = (SeekBar)findViewById(R.id.seekBarBeltoon);
        text += "Beltoon: " + volume.getProgress() + "\n";
        volume = (SeekBar)findViewById(R.id.seekBarMedia);
        text += "Media: " + volume.getProgress() + "\n";
        volume = (SeekBar)findViewById(R.id.seekBarMelding);
        text += "Meldingen: " + volume.getProgress();

        Toast toastbericht = Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG);
        toastbericht.show();
    }

    public void onClickReset(View v){
        SeekBar sb1 = (SeekBar) findViewById(R.id.seekBarBeltoon);
        SeekBar sb2 = (SeekBar) findViewById(R.id.seekBarMelding);
        SeekBar sb3 = (SeekBar) findViewById(R.id.seekBarMedia);
        SeekBar sb4 = (SeekBar) findViewById(R.id.seekBarAlarm);

        sb1.setProgress(50);
        sb2.setProgress(25);
        sb3.setProgress(75);
        sb4.setProgress(100);
    }

    public void onCheckedClick(View v) {
        boolean checked = ((CheckBox) v).isChecked();
        SeekBar sb2 = (SeekBar) findViewById(R.id.seekBarMelding);
        if (checked == true) {
            check = true;
            sb2.setEnabled(false);
        } else {
            check = false;
            sb2.setEnabled(true);
        }
    }
}
