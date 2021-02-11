package com.example.myjobapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.location.Location;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toolbar;


public class LocationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        EditText locationField = findViewById(R.id.locationField);
        Button buttonGetLocation = findViewById(R.id.buttonGetLocation);

        Intent getLanguage = getIntent();
        String languageValue = getLanguage.getStringExtra("choosenLanguage");

        buttonGetLocation.setEnabled(false);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

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
                Intent intentToResult = new Intent(LocationActivity.this, JobListActivity.class);
                intentToResult.putExtra("location", getLocation);
                intentToResult.putExtra("choosenLanguage", languageValue);
                startActivity(intentToResult);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            onBackPressed();  return true;
        }

        return super.onOptionsItemSelected(item);
    }

}