package com.example.myjobapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;

public class LanguageActivity extends AppCompatActivity {
    String choosenLanguage;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#3498db")));
        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        RadioGroup rg = (RadioGroup) findViewById(R.id.radioChoice);

        Button btnValidLanguage = findViewById(R.id.confirm);
        btnValidLanguage.setEnabled(false);
        btnValidLanguage.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startActivity(intent);
            }
        });

        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.python:
                        onLanguageSelection("python");
                        break;
                    case R.id.javascript:
                        onLanguageSelection("javascript");
                        break;
                    case R.id.ruby:
                        onLanguageSelection("ruby");
                        break;
                    case R.id.php:
                        onLanguageSelection("php");
                        break;
                }
                btnValidLanguage.setEnabled(true);
            }
        });
    }

    public void onLanguageSelection(String language) {
        choosenLanguage = language;
        intent = new Intent(LanguageActivity.this, LocationActivity.class);
        intent.putExtra("choosenLanguage", choosenLanguage);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}


