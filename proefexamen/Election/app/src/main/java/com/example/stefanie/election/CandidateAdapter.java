package com.example.stefanie.election;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by stefanie on 15/11/2016.
 */

public class CandidateAdapter extends ArrayAdapter {
    public List<Candidate>candidates;
    public CandidateAdapter(Context context, int resource, List<Candidate> candidates) {
        super(context, resource, candidates);
        this.candidates = candidates;
    }
    public View getView(int position, View convertview, ViewGroup parent) {

        LayoutInflater infl = (LayoutInflater)
                getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = infl.inflate(R.layout.list_item, parent, false);
        ImageView iv_icon = (ImageView) rowView.findViewById(R.id.partyImage);
        TextView tv_label = (TextView) rowView.findViewById(R.id.candidateName);
        Candidate candidate = candidates.get(position);
        String party = candidate.getParty();
        switch (party){
            case "Republican Party":
                iv_icon.setImageResource(R.mipmap.republican_icon);
                tv_label.setText(candidate.getName());
                break;
            case "Democratic party":
                iv_icon.setImageResource(R.mipmap.democratic_icon);
                tv_label.setText(candidate.getName());
                break;
            default:
                iv_icon.setImageResource(R.mipmap.ic_election);
                tv_label.setText(candidate.getName());
        }
        return rowView;
    }
    public void UpdateData(List<Candidate> candidates) {
        this.candidates.clear();
       this.candidates.addAll(candidates);
        notifyDataSetChanged();
    }

}
