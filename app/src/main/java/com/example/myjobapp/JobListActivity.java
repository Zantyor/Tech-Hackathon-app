package com.example.myjobapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class JobListActivity extends AppCompatActivity {
    String language;
    String location;
    int resultCount;
    ListView listView;
    JSONObject currentObject;
    ArrayList<String> arrayList = new ArrayList<String>();
    ArrayAdapter<String> arrayAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_list);


        ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#3498db")));
        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        Intent getLanguage = getIntent();
        String languageValue = getLanguage.getStringExtra("choosenLanguage");
        String locationValue = getLanguage.getStringExtra("location");

        if (languageValue != null) {
            language = languageValue;
        }
        if (locationValue != null) {
            location = locationValue;
        }

        listView = findViewById(R.id.resultList);
        getJobsList(language, location);
    }


    private void getJobsList(String language, String location) {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://jobs.github.com/positions.json?description=" + language + "&location=" + location;


        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {
                        if (arrayList != null) {
                            arrayList.clear();
                        }
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject object = response.getJSONObject(i);
                                currentObject = object;
                                String title = object.getString("title");
                                String location = object.getString("location");
                                arrayList.add("Title : " + title + "\n" + "Location : " + location);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            arrayAdapter = new ArrayAdapter<>(getApplicationContext(),
                                    android.R.layout.simple_list_item_1, arrayList);
                            listView.setAdapter(arrayAdapter);
                        }
                        resultCount = response.length();
                        TextView ResultView = findViewById(R.id.resultView);
                        if (resultCount == 1) {
                            ResultView.setText(resultCount + " result found");
                        } else {
                            ResultView.setText(resultCount + " results found");

                        }

                        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                Intent resultIntent = new Intent(JobListActivity.this, ShowJobActivity.class);
                                try {
                                    resultIntent.putExtra("selectedJobObject", response.getJSONObject(position).toString());
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                startActivity(resultIntent);
                            }
                        });
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.i("errortag1", "erreur à dans la réponse");
                        //Gestion de l'erreur
                    }
                });

        queue.add(jsonArrayRequest);
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