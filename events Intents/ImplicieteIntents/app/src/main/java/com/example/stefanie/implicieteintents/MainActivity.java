package com.example.stefanie.implicieteintents;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.view.View.OnClickListener;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button bellen = (Button) findViewById(R.id.buttonBel);
        bellen.setOnClickListener(bellenListener);
        Button website = (Button) findViewById(R.id.buttonWebsite);
        website.setOnClickListener(websiteListener);
    }

    private OnClickListener bellenListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent belIntent = new Intent(Intent.ACTION_DIAL);
            belIntent.setData(Uri.parse("tel +32477482825"));
            startActivity(belIntent);
        }
    };

    private OnClickListener websiteListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW);
            browserIntent.setData(Uri.parse("https://www.google.be/"));
            startActivity(browserIntent);
        }
    };
}
