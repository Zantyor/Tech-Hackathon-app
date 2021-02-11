package com.example.myjobapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

public class ShowJobActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_job);

        ImageView imageView = findViewById(R.id.companyLogo);
        TextView titleView = findViewById(R.id.title);
        TextView locationView = findViewById(R.id.companyLocation);
        TextView dateView = findViewById(R.id.creationDate);
        TextView htmlView = findViewById(R.id.htmlDescription);
        TextView applyView = findViewById(R.id.applyContent);

        Intent intent = getIntent();

        String jsonString = intent.getStringExtra("selectedJobObject");
        try {
            JSONObject jObj = new JSONObject(jsonString);
            Picasso.get().load(jObj.getString("company_logo")).into(imageView);
            titleView.setText(jObj.getString("title"));
            locationView.setText(jObj.getString("location"));
            htmlView.setText(Html.fromHtml(jObj.getString("description")));
            applyView.setText(Html.fromHtml(jObj.getString("how_to_apply")));
        } catch (JSONException e) {
            e.printStackTrace();
        }



    }
}