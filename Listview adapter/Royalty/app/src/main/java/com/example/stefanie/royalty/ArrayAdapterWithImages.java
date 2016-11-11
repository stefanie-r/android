package com.example.stefanie.royalty;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by stefanie on 8/08/2016.
 */
public class ArrayAdapterWithImages extends ArrayAdapter<String> {
    private List<String> strings;

    public ArrayAdapterWithImages(Context context, int resource, int textViewResourceId, List<String> objects) {
        super(context, resource, textViewResourceId, objects);
        strings = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        View view = convertView;

        if (view == null){
            LayoutInflater lI;
            lI = LayoutInflater.from(getContext());
            view = lI.inflate(R.layout.list_item, null);
        }
        String string = strings.get(position);

        if(string != null){
            TextView textView = (TextView)view.findViewById(R.id.text1);
            if (textView != null){
                textView.setText(string);
            }
        }
        return view;
    }
    public  List<String> getStrings(){
        return strings;
    }
}
