package com.example.stefanie.ioudatabank;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by stefanie on 13/11/2016.
 */

public class ContractAdapter extends ArrayAdapter<DatabaseContract> {

    List<DatabaseContract> data;

    public ContractAdapter(Context context, int resource, List<DatabaseContract> objects) {
        super(context, resource, objects);
        data = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if(v == null){
            LayoutInflater i = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = i.inflate(R.layout.contract_list_item,null);
        }
        DatabaseContract c = data.get(position);
        if (c !=null) {
            TextView amount = (TextView) v.findViewById(R.id.tv_amount);
            TextView owedTo = (TextView) v.findViewById(R.id.tv_owed_to);
            TextView owedBy = (TextView) v.findViewById(R.id.tv_owed_by);
            TextView reason = (TextView) v.findViewById(R.id.tv_reason);
            String amountS = String.valueOf(c.getAmount());
            amount.setText(amountS);
            owedTo.setText(c.getOwedTo());
            owedBy.setText(c.getOwedBy());
            reason.setText(c.getContext());
        }
        return v;
    }

    public void update(List<DatabaseContract> c) {
        this.data.clear();
        this.data.addAll(c);
        notifyDataSetChanged();
    }
}

