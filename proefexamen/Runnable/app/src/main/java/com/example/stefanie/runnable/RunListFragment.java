package com.example.stefanie.runnable;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ListView;

import java.util.List;

/**
 * Created by stefanie on 15/11/2016.
 */
//Listitems zijn niet klikbaar nog uitzoeken waarom
public class RunListFragment extends ListFragment {
    OnRunSelectedListener mListener;
    @Nullable
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        List<Run> runs = RunUtils.getRuns(getContext());
        RunListAdapter runAdapter = new RunListAdapter(getActivity(), R.layout.run_list_fragment, runs);
        setListAdapter(runAdapter);
    }

    @Override
    public void onStart() {
        super.onStart();

        if (getFragmentManager().findFragmentById(R.id.detailFragment) != null) {
            getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        }
    }

public interface OnRunSelectedListener{
    public void onRunSelected(int position);
}

    @Override
    public void onAttach(Activity activity){
        super.onAttach(activity);
        try {
            mListener = (OnRunSelectedListener)activity;
        }catch (ClassCastException e){
            throw new ClassCastException(activity.toString() + "musst implement OnRunSelectedListener");
        }
    }
    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        mListener.onRunSelected(position);
    }
}