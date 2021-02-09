package com.example.myjobapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;

public class JobListActivity extends AppCompatActivity {
    String coco = "coco";
    //Valeur test
    String language = "ruby";
    String location = "new+york";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_list);
    }

    private void getJobsList(String language, String location) {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://jobs.github.com/positions.json?description=" + language + "&location=" + location;


        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        /* try {*/
                        JSONObject jobslist = response;
                        Log.d("MYINT", String.valueOf(response));
                        //Traitement ici
                        /*} catch (JSONException e) {
                            e.printStackTrace();
                        }*/
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.i("errortag1", "erreur à dans la réponse");
                        //Gestion de l'erreur
                    }
                });

        queue.add(jsonObjectRequest);

    }
}