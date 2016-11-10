package com.example.android.master_detailfragments;

import android.app.Activity;
import android.support.v4.app.ListFragment;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by stefanie on 4/10/2016.
 */

public class PersonsFragment extends ListFragment {
    OnPersonSelectedListener mListener;

    public PersonsFragment() {
    }

    @Nullable
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //int layout  = R.layout.personlistview;
        Resources res = getResources();
        String[] persons = res.getStringArray(R.array.persons);

        ArrayAdapter personAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, persons);
        setListAdapter(personAdapter);
    }

    @Override
    public void onStart() {
        super.onStart();

        // When in two-pane layout, set the listview to highlight the selected list item
        // (We do this during onStart because at the point the listview is available.)
        if (getFragmentManager().findFragmentById(R.id.detailFragment) != null) {
            getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        }
    }

    public interface OnPersonSelectedListener{
        public void onPersonSelected(int position);
    }

    @Override
    public void onAttach(Activity activity){
        super.onAttach(activity);
        try {
            mListener = (OnPersonSelectedListener)activity;
        }catch (ClassCastException e){
            throw new ClassCastException(activity.toString() + "musst implement OnPersonSelectedListener");
        }
    }
    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
       // Send the event to the host activity
        mListener.onPersonSelected(position);
    }
}
