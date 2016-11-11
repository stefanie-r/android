package com.example.stefanie.registerevent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickLogin(View v) {
        EditText usernameText = (EditText) findViewById(R.id.editText);
        EditText passwordText = (EditText) findViewById(R.id.editText2);
        String text = "test";
        String username = usernameText.getText().toString();
        String password = passwordText.getText().toString();
        boolean empty = true;
        boolean toshort = true;
        boolean letter = false;
        boolean nummer = false;

        if (username.isEmpty() && password.isEmpty()) {
            text = "geef een gebruikersnaam en wachtwoord in";
            Toast toastEmpty = Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT);
            toastEmpty.show();
        }
        if (username.isEmpty() && password.length() > 0) {
            text = "geef een gebruikersnaam in";
            Toast toastEmpty = Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT);
            toastEmpty.show();
        }
        if (password.isEmpty() && username.length() > 0) {
            text = "geef een wachtwoord in";
            Toast toastEmpty = Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT);
            toastEmpty.show();
        }
        if (password.length() > 0 && username.length() > 0) {
            empty = false;
        }

        if (!empty) {
            if (username.length() < 5 && password.length() < 5) {
                text = "geef een geldig gebruikersnaam en wachtwoord in";
                Toast toastshort = Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT);
                toastshort.show();
            }
            if (username.length() < 5 && password.length() > 4) {
                text = "geef een geldig gebruikersnaam in";
                Toast toastshort = Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT);
                toastshort.show();
            }
            if (password.length() < 5 && username.length() > 4) {
                text = "geef een geldig wachtwoord in";
                Toast toastshort = Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT);
                toastshort.show();
            }
            if (password.length() > 4 && username.length() > 4) {
                toshort = false;
            }
        }

        if (!toshort) {
            for (int i = 0; i < password.length(); i++) {
                char symbol = password.charAt(i);

                if (Character.isDigit(password.charAt(i))) {
                    nummer = true;
                }
                if (Character.isLetter(symbol)) {
                    letter = true;
                }
            }
            if (!nummer && !letter) {
                text = "geef een geldig wachtwoord in, er zitten geen letters of cijfers in";
            }
            if (!nummer && letter) {
                text = "geef een geldig wachtwoord in, er zitten geen cijfers in";
            }
            if (nummer && !letter) {
                text = "geef een geldig wachtwoord in, er zitten geen letters in";
            }
            if (letter&& nummer) {
                text = "registratie geslaagt";
            }
            Toast toastbericht = Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG);
            toastbericht.show();
        }

    }
}
