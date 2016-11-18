package com.example.stefanie.runnable;

import android.app.ProgressDialog;
import android.content.ContentValues;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by stefanie on 15/11/2016.
 */

public class RunDetailFragment extends Fragment {
    final static String ARG_POSITION = "position";
    int mCurrentPosition = -1;
    Run run;
    ImageView image;
    TextView datum;
    TextView naam;
    TextView plaats;
    TextView prijs;
    TextView organistatie;
    CheckBox deelneming;
    TextView website;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            mCurrentPosition = savedInstanceState.getInt(ARG_POSITION);
        }
        return inflater.inflate(R.layout.run_detail_fragment, container, false);
    }

    public void updateDetailView(int position) {
        List<Run> runs = RunUtils.getRuns(getContext());
        run = runs.get(position);
        image = (ImageView) getActivity().findViewById(R.id.runPicture);
        datum = (TextView) getActivity().findViewById(R.id.runDate);
        plaats = (TextView) getActivity().findViewById(R.id.runPlace);
        naam = (TextView) getActivity().findViewById(R.id.runName);
        prijs = (TextView) getActivity().findViewById(R.id.runPrice);
        organistatie = (TextView) getActivity().findViewById(R.id.runOrganisation);
        deelneming = (CheckBox) getActivity().findViewById(R.id.runParticipate);
        website = (TextView) getActivity().findViewById(R.id.runWebsite);
        int distance = run.getDistance();
        imagePlacer(distance);
        datum.setText(run.getDate());
        naam.setText(run.getTitle());
        plaats.setText(run.getLocation());
        prijs.setText(String.valueOf(run.getPrice()));
        organistatie.setText(run.getOrganization());
        website.setText(run.getEventUrl());
        deelneming.setChecked(run.isJoined());
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
        /*
        deelneming.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                try to add the run in async
                addRun task = new addRun();
                task.execute(run);

            }
        });*/
    }

    public void imagePlacer(int distance) {
        switch (distance) {
            case 5:
                image.setImageResource(R.mipmap.ic_5k);
                image.setBackgroundColor(getResources().getColor(-R.color.green));
                break;
            case 10:
                image.setImageResource(R.mipmap.ic_10k);
                image.setBackgroundColor(getResources().getColor(R.color.yellow));
                break;
            case 21:
                image.setImageResource(R.mipmap.ic_21k);
                image.setBackgroundColor(getResources().getColor(R.color.orange));
                break;
            case 42:
                image.setImageResource(R.mipmap.ic_42k);
                image.setBackgroundColor(getResources().getColor(R.color.red));
                break;
            default:
                image.setBackgroundColor(getResources().getColor(android.R.color.black));
                image.setImageResource(R.drawable.ic_launcher);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(ARG_POSITION, mCurrentPosition);
    }

}


