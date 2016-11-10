package com.example.android.master_detailfragments;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

/**
 * Created by stefanie on 4/10/2016.
 */

public class DetailsFragment extends Fragment {
    final static String ARG_POSITION = "position";
    int mCurrentPosition = -1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            mCurrentPosition = savedInstanceState.getInt(ARG_POSITION);
        }
        return inflater.inflate(R.layout.details_view, container, false);
    }

    public void updateDetailView(int position) {
        EditText firstName = (EditText) getActivity().findViewById(R.id.editTextFirstName);
        EditText lastName = (EditText) getActivity().findViewById(R.id.editTextLastName);
        Resources res = getResources();
        String[] persons = res.getStringArray(R.array.persons);
        String name = persons[position];
        String[] splitStr = name.split("\\s+");
        firstName.setText(splitStr[0]);
        lastName.setText(splitStr[1]);

    }
    @Override
    public void onStart() {
        super.onStart();

        // During startup, check if there are arguments passed to the fragment.
        // onStart is a good place to do this because the layout has already been
        // applied to the fragment at this point so we can safely call the method
        // below that sets the article text.
        Bundle args = getArguments();
        if (args != null) {
            // Set article based on argument passed in
            updateDetailView(args.getInt(ARG_POSITION));
        } else if (mCurrentPosition != -1) {
            // Set article based on saved instance state defined during onCreateView
            updateDetailView(mCurrentPosition);
        }
    }
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        // Save the current article selection in case we need to recreate the fragment
        outState.putInt(ARG_POSITION, mCurrentPosition);
    }
}
