package com.example.stefanie.election;

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

public class CandidatesListFragment extends ListFragment {
    OnPersonSelectedListener mListener;
    @Nullable
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        List<Candidate>candidates = ElectionUtils.getCandidates(getContext());
        CandidateAdapter personAdapter = new CandidateAdapter(getActivity(), R.layout.fragment_list, candidates);
        setListAdapter(personAdapter);
    }

    @Override
    public void onStart() {
        super.onStart();

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
        mListener.onPersonSelected(position);
    }
}