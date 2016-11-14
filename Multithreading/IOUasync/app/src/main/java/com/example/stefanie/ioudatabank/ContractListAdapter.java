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

public class ContractListAdapter extends ArrayAdapter {
    private List<Contract> contracts;

    public ContractListAdapter(Context context, int resource, List<Contract> contracts) {
        super(context, resource, contracts);
        this.contracts = contracts;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;

        if (v == null) {
            LayoutInflater vi = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = vi.inflate(R.layout.contractlist_item, null);
        }

        Contract contract = contracts.get(position);

        if (contract != null) {
            TextView left = (TextView) v.findViewById(R.id.tv_ListItemLeft);
            TextView right = (TextView) v.findViewById(R.id.tv_ListItemRight);

            left.setText(contract.getOwedBy() + " owes " + contract.getOwedTo() + " " + contract.getAmount() + "€");
            right.setText("for " + contract.getContext().replaceAll("[\\t\\n\\r]"," "));

        }
        return v;
    }

    public void UpdateData(List<Contract> contracts) {
        this.contracts.clear();
        this.contracts.addAll(contracts);
        notifyDataSetChanged();
    }
}
