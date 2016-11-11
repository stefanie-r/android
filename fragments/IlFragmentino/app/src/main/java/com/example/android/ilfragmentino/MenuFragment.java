package com.example.android.ilfragmentino;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by stefanie on 4/10/2016.
 */

public class MenuFragment extends Fragment {
    final static String ARG_POSITION = "position";
    String dagVanDeWeek = "Maandag";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            dagVanDeWeek = savedInstanceState.getString(ARG_POSITION);
        }
        return inflater.inflate(R.layout.menu, container, false);
    }
    @Override
    public void onStart() {
        super.onStart();

        Bundle args = getArguments();
        if (args != null) {
            // Set article based on argument passed in
            updateMenuView(args.getString(ARG_POSITION));
        } else if (dagVanDeWeek != "Maandag") {
            // Set article based on saved instance state defined during onCreateView
            updateMenuView(dagVanDeWeek);
        }
    }
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        // Save the current article selection in case we need to recreate the fragment
        outState.putString(ARG_POSITION, dagVanDeWeek);
    }
    public void updateMenuView(String dag){
        TextView day = (TextView) getActivity().findViewById(R.id.dayOfWeek);
        day.setText(dag);
    }
}
