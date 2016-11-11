package com.example.stefanie.checkedrestaurant;

import android.app.ListActivity;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends ListActivity {
    String[] values;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        values = new String[]{"TomatenSoep", "fish and chips", "Pasta carbonara", "chili sin carne","worstebroodje","koude schotel", "roomijs"};
        ArrayAdapter<String> arrAd = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_multiple_choice, this.values);
        this.setListAdapter(arrAd);

        this.getListView().setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
    }

    protected void onListItemClick(ListView l, View v, int position, long id){
        SparseBooleanArray checkedItems = getListView().getCheckedItemPositions();
        String text = "Result: ";
        for (int i = 0; i<getListView().getCount(); i++){
            if (checkedItems.get(i) == true){
                text += getListAdapter().getItem(i).toString() + "\n";
            }
        }
        Toast popup = Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG);
        popup.show();
    }
}
