package com.example.myjobapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class language extends AppCompatActivity {
    String choosenLanguage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language);

        RadioGroup rg = (RadioGroup) findViewById(R.id.radioChoice);

        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(checkedId){
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
            }
        });


    }
    public void onLanguageSelection(String language){
        Intent intent = new Intent(language.this,activity_location.class);
        choosenLanguage = language ;
        intent.putExtra("choosenLanguage",choosenLanguage);
        startActivity(intent);
    }
  /*  public void onRadioButtonClick(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        switch(view.getId()) {
            case R.id.python:
                if (checked)
                    choosenLanguage = "python";
                break;
            case R.id.javascript:
                if (checked)
                    choosenLanguage = "javascript";
                break;
            case R.id.ruby:
                if (checked)
                    choosenLanguage = "ruby";
                break;
            case R.id.php:
                if (checked)
                    choosenLanguage = "php";
                break;
        }
    }*/
}


