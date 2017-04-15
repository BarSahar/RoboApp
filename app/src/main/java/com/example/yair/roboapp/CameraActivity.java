package com.example.yair.roboapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class CameraActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        final String ip=getIntent().getStringExtra("Ip");

        findViewById(R.id.Lbutton).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                Toast.makeText(CameraActivity.this, "Ip: "+ip , Toast.LENGTH_LONG).show();
                RequestQueue queue = Volley.newRequestQueue(CameraActivity.this);
                String req="http://"+ip+":8080/Left";

                StringRequest stringRequest = new StringRequest(Request.Method.GET, req,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

                                Toast.makeText(CameraActivity.this, response, Toast.LENGTH_LONG).show();

                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(CameraActivity.this, "Bad Api: " + error, Toast.LENGTH_LONG).show();
                    }
                });
                queue.add(stringRequest);

            }
        });
        findViewById(R.id.Fbutton).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                Toast.makeText(CameraActivity.this, "Ip: "+ip , Toast.LENGTH_LONG).show();
                RequestQueue queue = Volley.newRequestQueue(CameraActivity.this);
                String req="http://"+ip+":8080/Forward";

                StringRequest stringRequest = new StringRequest(Request.Method.GET, req,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

                                Toast.makeText(CameraActivity.this, response, Toast.LENGTH_LONG).show();

                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(CameraActivity.this, "Bad Api: " + error, Toast.LENGTH_LONG).show();
                    }
                });
                queue.add(stringRequest);

            }
        });
        findViewById(R.id.Rbutton).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                Toast.makeText(CameraActivity.this, "Ip: "+ip , Toast.LENGTH_LONG).show();
                RequestQueue queue = Volley.newRequestQueue(CameraActivity.this);
                String req="http://"+ip+":8080/Right";

                StringRequest stringRequest = new StringRequest(Request.Method.GET, req,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

                                Toast.makeText(CameraActivity.this, response, Toast.LENGTH_LONG).show();

                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(CameraActivity.this, "Bad Api: " + error, Toast.LENGTH_LONG).show();
                    }
                });
                queue.add(stringRequest);

            }
        });



    }
}
