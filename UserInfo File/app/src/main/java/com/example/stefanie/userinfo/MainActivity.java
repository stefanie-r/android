package com.example.stefanie.userinfo;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    private String filename = "userinformation";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText nameTextbox = ((EditText) findViewById(R.id.editTextName));
        EditText addressTextbox = ((EditText) findViewById(R.id.editTextAddress));
        EditText phoneTextbox = ((EditText) findViewById(R.id.editTextPhone));
        EditText emailTextbox = ((EditText) findViewById(R.id.editTextEmail));
        CheckBox newsletterCheckBox = ((CheckBox) findViewById(R.id.cbNewsletter));
        try{
            FileInputStream inputStream = openFileInput(filename);
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine())!= null){
                stringBuilder.append(line + "\n");
            }
            String[] textboxValues = stringBuilder.toString().split("[\n]+");
            nameTextbox.setText(textboxValues[0]);
            addressTextbox.setText(textboxValues[1]);
            phoneTextbox.setText(textboxValues[2]);
            emailTextbox.setText(textboxValues[3]);
            if (textboxValues[4].equals("getsNewsletter")){
                newsletterCheckBox.setChecked(true);
            }
            else {
                newsletterCheckBox.setChecked(false);
            }
            inputStream.close();

        }catch (Exception e){
            e.printStackTrace();
        }

        Button okbtn = (Button) findViewById(R.id.SaveBtn);
        okbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveToFile();
            }
        });
    }

    private void saveToFile() {
        FileOutputStream outputStream;
        String separator = System.getProperty("line.separator");

        String name, address, phone, email;
        name = ((EditText) findViewById(R.id.editTextName)).getText().toString();
        address = ((EditText) findViewById(R.id.editTextAddress)).getText().toString();
        phone = ((EditText) findViewById(R.id.editTextPhone)).getText().toString();
        email = ((EditText) findViewById(R.id.editTextEmail)).getText().toString();
        boolean getsNewsletter = ((CheckBox) findViewById(R.id.cbNewsletter)).isChecked();

        try {
            outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
            String fileContent = name + separator + address + separator + phone + separator + email + separator;
            if (getsNewsletter){
                fileContent += "getsNewsletter";
            } else{
                fileContent += "doesntGetNewsletter";
            }
            outputStream.write(fileContent.getBytes());
            outputStream.close();
            Toast.makeText(getApplicationContext(), "Saved settings!", Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
