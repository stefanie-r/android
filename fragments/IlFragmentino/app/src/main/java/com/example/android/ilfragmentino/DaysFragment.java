package com.example.android.ilfragmentino;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by stefanie on 4/10/2016.
 */

public class DaysFragment extends Fragment {
    OnDaySelectedListener mListener;
    final static String ARG_POSITION = "position";
    String weekDatum = "14-18 feb";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            weekDatum = savedInstanceState.getString(ARG_POSITION);
        }
        return inflater.inflate(R.layout.days, container, false);
    }
    @Override
    public void onStart() {
        super.onStart();

        Bundle args = getArguments();
        if (args != null) {
            // Set article based on argument passed in
            updateDaysView(args.getString(ARG_POSITION));
        } else if (weekDatum != "14-18 feb") {
            // Set article based on saved instance state defined during onCreateView
            updateDaysView(weekDatum);
        }
        Button btnMonday = (Button) getActivity().findViewById(R.id.btnMonday);
        Button btnThuesday = (Button) getActivity().findViewById(R.id.btnThuesday);
        Button btnWednesday = (Button) getActivity().findViewById(R.id.btnWednesday);
        Button btnThursday = (Button) getActivity().findViewById(R.id.btnThursday);
        Button btnFriday = (Button) getActivity().findViewById(R.id.btnFriday);

        View.OnClickListener l = createListener();
        btnMonday.setOnClickListener(l);
        btnThuesday.setOnClickListener(l);
        btnWednesday.setOnClickListener(l);
        btnThursday.setOnClickListener(l);
        btnFriday.setOnClickListener(l);
    }
    private View.OnClickListener createListener() {
        View.OnClickListener l = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String dag = ((Button) v).getText().toString();
                mListener.onDaySelected(dag);
            }
        };
        return l;
    }
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        // Save the current article selection in case we need to recreate the fragment
        outState.putString(ARG_POSITION, weekDatum);
    }
    public interface OnDaySelectedListener{
        public void onDaySelected(String week);
    }
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnDaySelectedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + "musst implement OnWeekSelectedListener");
        }
    }
    public void updateDaysView(String week) {
        TextView weekDate = (TextView) getActivity().findViewById(R.id.txtWeekDate);
        weekDate.setText(week);
    }
}
