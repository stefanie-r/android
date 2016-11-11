package com.example.stefanie.customoslist;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

/**
 * Created by stefanie on 8/08/2016.
 */
public class CustomActivity extends Activity{
    private String[] values = {"Android", "iPhone", "WindowsMobile",
            "Blackberry", "WebOS", "Ubuntu", "Windows7",
            "Mac OS X", "Linux", "OS/2"};
    @Override
    public void OnCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom);

        CustomAdapter custAd = new CustomAdapter(this, this.values);

        ListView ly_array = (ListView) findViewById(R.id.lv_array);
        ly_array.setAdapter(custAd);
    }
}
