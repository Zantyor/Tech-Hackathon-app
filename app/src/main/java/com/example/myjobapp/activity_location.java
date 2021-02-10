package com.example.myjobapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class activity_location extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        EditText locationField = findViewById(R.id.locationField);
        Button buttonGetLocation = findViewById(R.id.buttonGetLocation);

        Intent getLanguage = getIntent();
        String languageValue = getLanguage.getStringExtra("choosenLanguage");

        buttonGetLocation.setEnabled(false);

        locationField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().equals("")) {
                    buttonGetLocation.setEnabled(false);
                } else {
                    buttonGetLocation.setEnabled(true);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        buttonGetLocation.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                String getLocation = locationField.getText().toString();
                Intent intentToResult = new Intent(activity_location.this, MainActivity.class);
                intentToResult.putExtra("getLocation", getLocation);
                startActivity(intentToResult);
            }
        });
    }

}