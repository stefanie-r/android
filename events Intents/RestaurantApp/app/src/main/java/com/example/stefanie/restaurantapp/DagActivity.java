package com.example.stefanie.restaurantapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by stefanie on 1/08/2016.
 */
public class DagActivity extends AppCompatActivity {
    private String dag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        dag = intent.getStringExtra(WeekActivity.dagVar);

        TextView view = (TextView) findViewById(R.id.textView);
        view.setText(dag);
        TextView soep = (TextView) findViewById(R.id.textViewSoepSoort);
        TextView dagschotel = (TextView) findViewById(R.id.textViewDagschotelIngevuld);
        TextView pasta = (TextView) findViewById(R.id.textViewPastaIngevuld);
        TextView vegegtarisch = (TextView) findViewById(R.id.textViewVegetarischIngevuld);
        TextView snack = (TextView) findViewById(R.id.textViewSnackIngevuld);
        TextView saladebar = (TextView) findViewById(R.id.textViewSaladebarIngevuld);
        TextView dessert = (TextView) findViewById(R.id.textViewDessertIngevuld);

        switch (dag) {
            case "Maandag":
                soep.setText("Tomatensoep");
                dagschotel.setText("frieten met videe");
                pasta.setText("lasanga");
                vegegtarisch.setText("chili can carde");
                snack.setText("naar keuze");
                saladebar.setText("koude schotel");
                dessert.setText("ijs");

            case "Dinsdag":
                soep.setText("Pompoensoep");
                dagschotel.setText("frieten met stoofvlees");
                pasta.setText("lasanga");
                vegegtarisch.setText("chili can carde");
                snack.setText("naar keuze");
                saladebar.setText("salade");
                dessert.setText("taart");

            case "Woensdag":
                soep.setText("Aspergesoep");
                dagschotel.setText("frieten met snitsel");
                pasta.setText("lasanga");
                vegegtarisch.setText("chili can carde");
                snack.setText("naar keuze");
                saladebar.setText("salade");
                dessert.setText("fruit");

            case "Donderdag":
                soep.setText("Kervelsoep");
                dagschotel.setText("hamburger");
                pasta.setText("lasanga");
                vegegtarisch.setText("chili can carde");
                snack.setText("naar keuze");
                saladebar.setText("salade");
                dessert.setText("yogurt");

            case "Vrijdag":
                soep.setText("Witloofsoep");
                dagschotel.setText("frieten met spareribs");
                pasta.setText("lasanga");
                vegegtarisch.setText("chili can carde");
                snack.setText("naar keuze");
                saladebar.setText("salade");
                dessert.setText("taart");

        }
    }
}
