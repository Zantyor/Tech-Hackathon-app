package com.example.myjobapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Html;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

public class ShowJobActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_job);

        ImageView imageView = findViewById(R.id.companyLogo);
        TextView titleView = findViewById(R.id.title);
        TextView locationView = findViewById(R.id.companyLocation);
        TextView htmlView = findViewById(R.id.htmlDescription);
        Button applyButton = findViewById(R.id.applyButton);
        Intent intent = getIntent();

        String jsonString = intent.getStringExtra("selectedJobObject");
        try {
            JSONObject jObj = new JSONObject(jsonString);
            Picasso.get().load(jObj.getString("company_logo")).into(imageView);
            titleView.setText(jObj.getString("title"));
            locationView.setText(jObj.getString("location"));
            htmlView.setText(Html.fromHtml(jObj.getString("description")));
            applyButton.setText(Html.fromHtml(jObj.getString("how_to_apply")));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#3498db")));
        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

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