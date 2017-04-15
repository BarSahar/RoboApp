package com.example.yair.roboapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.yair.roboapp.classes.Utils;
import com.example.yair.roboapp.classes.Camera;

public class CameraActivity extends AppCompatActivity {
    public final static String CAMERA = "camera";
    private final static String TAG = "CameraActivity";
    private Camera camera;
    private CameraFragment cameraFragment;
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

                                Toast.makeText(CameraActivity.this, "=)", Toast.LENGTH_LONG).show();

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


        // load the settings and cameras
        //Utils.loadData();

        // get the camera object
        //TODO: set a new camera object
        //Bundle data = getIntent().getExtras();
        //camera = data.getParcelable(CAMERA);

        // set the source fragment
        //cameraFragment = (CameraFragment)getSupportFragmentManager().findFragmentById(R.id.camera_source);
        //cameraFragment.configure(camera.source, true);
    }
}
