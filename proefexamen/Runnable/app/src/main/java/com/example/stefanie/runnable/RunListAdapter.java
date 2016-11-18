package com.example.stefanie.runnable;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by stefanie on 15/11/2016.
 */
//checkbox zorgt ervoor dat item niet klikbaar is uitzoeken hoeop te lossen
    //overal in comment om deze reden
public class RunListAdapter extends ArrayAdapter {
    public List<Run> runs;
    public RunListAdapter(Context context, int resource, List<Run> runs) {
        super(context, resource, runs);
        this.runs = runs;
    }
    public View getView(int position, View convertview, ViewGroup parent) {

        LayoutInflater infl = (LayoutInflater)
                getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = infl.inflate(R.layout.custom_list_item, parent, false);
        ImageView iv_icon = (ImageView) rowView.findViewById(R.id.runListImage);
        TextView tv_name = (TextView) rowView.findViewById(R.id.runListName);
        TextView tv_date = (TextView) rowView.findViewById(R.id.runListDate);
        TextView tv_location = (TextView) rowView.findViewById(R.id.runListLocation);
       // CheckBox cb_enlisted = (CheckBox) rowView.findViewById(R.id.runListCheckbox);
        Run run = runs.get(position);
        int distance = run.getDistance();
        switch (distance){
            case 5:
                iv_icon.setImageResource(R.mipmap.ic_5k);
                break;
            case 10:
                iv_icon.setImageResource(R.mipmap.ic_10k);
                break;
            case 21:
                iv_icon.setImageResource(R.mipmap.ic_21k);
                break;
            case 42:
                iv_icon.setImageResource(R.mipmap.ic_42k);
                break;
            default:
                iv_icon.setImageResource(R.drawable.ic_launcher);
        }
        tv_name.setText(run.getTitle());
        tv_date.setText(run.getDate());
        tv_location.setText(run.getLocation());
        //cb_enlisted.setChecked(run.isJoined());
        return rowView;
    }
}
