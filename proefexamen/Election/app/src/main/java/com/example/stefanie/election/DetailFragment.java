package com.example.stefanie.election;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by stefanie on 15/11/2016.
 */

public class DetailFragment extends Fragment {
    final static String ARG_POSITION = "position";
    int mCurrentPosition = -1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            mCurrentPosition = savedInstanceState.getInt(ARG_POSITION);
        }
        return inflater.inflate(R.layout.fragment_detail, container, false);
    }
    //TODO
    public void updateDetailView(int position) {
        Candidate selectedCandidate = ElectionUtils.getCandidates(getContext()).get(position);
        TextView name = (TextView) getActivity().findViewById(R.id.detailCandidateName);
        TextView age = (TextView) getActivity().findViewById(R.id.age);
        TextView likes = (TextView) getActivity().findViewById(R.id.amountOfVotes);
        TextView party = (TextView) getActivity().findViewById(R.id.partyName);
        ImageView photo = (ImageView) getActivity().findViewById(R.id.candidatePicture);
        name.setText(selectedCandidate.getName());
        age.setText(String.valueOf(selectedCandidate.getAge()));
        likes.setText(String.valueOf(selectedCandidate.getVotes()));
        party.setText(selectedCandidate.getParty());
        switch(selectedCandidate.getName()){
            case "Hillary Clinton":
                photo.setImageResource(R.mipmap.hillary_clinton);
            break;
            case "Jeb Bush":
                photo.setImageResource(R.mipmap.ic_jeb_bush);
                break;
            case "Bernie Sanders":
                photo.setImageResource(R.mipmap.ic_bernie_sanders);
                break;
            case "Donald Trump":
                photo.setImageResource(R.mipmap.ic_donald_trump);
                break;
            case"Rand Paul":
                photo.setImageResource(R.mipmap.ic_rand_paul);
                break;
            default:
                photo.setImageResource(R.mipmap.ic_election);
        }
        switch (selectedCandidate.getParty()){
            case "Republican Party":
                photo.setBackgroundColor(getResources().getColor(android.R.color.holo_red_dark));
                break;
            case "Democratic party":
                photo.setBackgroundColor(getResources().getColor(android.R.color.holo_blue_dark));
                break;
            default:
                photo.setBackgroundColor(getResources().getColor(android.R.color.holo_green_dark));
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        Bundle args = getArguments();
        if (args != null) {
            updateDetailView(args.getInt(ARG_POSITION));
        } else if (mCurrentPosition != -1) {
            updateDetailView(mCurrentPosition);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(ARG_POSITION, mCurrentPosition);
    }
}
