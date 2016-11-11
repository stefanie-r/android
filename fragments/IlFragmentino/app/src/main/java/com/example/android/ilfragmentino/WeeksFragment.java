package com.example.android.ilfragmentino;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

/**
 * Created by stefanie on 4/10/2016.
 */

public class WeeksFragment extends Fragment {
OnWeekSelectedListener mListener;

    public WeeksFragment() {
    }
@Nullable
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.weeks, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        Button eerste = (Button) getActivity().findViewById(R.id.btnWeek1);
        Button tweede = (Button) getActivity().findViewById(R.id.btnWeek2);
        Button derde = (Button) getActivity().findViewById(R.id.btnWeek3);
        View.OnClickListener l = createListener();
        eerste.setOnClickListener(l);
        tweede.setOnClickListener(l);
        derde.setOnClickListener(l);
    }
    private View.OnClickListener createListener() {
        View.OnClickListener l = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String week = ((Button) v).getText().toString();
                mListener.onWeekSelected(week);
            }
        };
        return l;
    }
    public interface OnWeekSelectedListener{
        public void onWeekSelected(String week);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnWeekSelectedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + "musst implement OnWeekSelectedListener");
        }
    }
}
