package com.example.myjobapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

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
    String coco = "coco";
    //Valeur test
    String language;
    String location;

    ListView listView;
    JSONObject currentObject;
    ArrayList<String> arrayList = new ArrayList<String>();
    ArrayAdapter<String> arrayAdapter;
    /*Intent resultIntent = new Intent(JobListActivity.this,MainActivity.class);*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_list);

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
        getJobsList(language,location);
    }

    private void getJobsList(String language, String location) {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://jobs.github.com/positions.json?description=" + language + "&location=" + location;


        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {
                        for (int i=0; i<response.length(); i++) {
                            try {
                                JSONObject object = response.getJSONObject(i);
                                currentObject = object;
                                String title = object.getString("title");
                                String location = object.getString("location");
                                arrayList.add(title + "\n & location :" + location);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            arrayAdapter = new ArrayAdapter<>(getApplicationContext(),
                                    android.R.layout.simple_list_item_1,arrayList);
                            listView.setAdapter(arrayAdapter);
                            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                    Toast.makeText(getApplicationContext(),arrayList.get(position),Toast.LENGTH_SHORT).show();
                                    /*resultIntent.put("offer", currentObject);*/
                                }
                            });
                        }
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
}