package com.example.yair.roboapp;

import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.yair.roboapp.classes.Source;
import com.example.yair.roboapp.classes.Utils;
import com.example.yair.roboapp.classes.Camera;

public class CameraActivity  extends AppCompatActivity implements VideoFragment.OnFadeListener{
    public final static String CAMERA = "camera";
    private final static String TAG = "VideoActivity";
    private Camera camera;
    private FrameLayout frameLayout;
    private VideoFragment videoFragment;
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

        //Log.d(TAG, "onCreate");

        // load the settings and cameras
        //Utils.loadData();

        // get the camera object
        camera = new Camera(Source.ConnectionType.RawTcpIp,"","79.178.101.120",8080);

        //camera.name = "camera";

        // get the frame layout, handle system visibility changes
        frameLayout = (FrameLayout) findViewById(R.id.video);
        frameLayout.setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener()
        {
            @Override
            public void onSystemUiVisibilityChange(int visibility)
            {
                if ((visibility & View.SYSTEM_UI_FLAG_FULLSCREEN) == 0)
                {
                    videoFragment.startFadeIn();
                }
            }
        });

        // set full screen layout
        int visibility = frameLayout.getSystemUiVisibility();
        visibility |= View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION;
        frameLayout.setSystemUiVisibility(visibility);

        // create the video fragment
        videoFragment = videoFragment.newInstance(camera, true);
        FragmentTransaction fragTran = getSupportFragmentManager().beginTransaction();
        fragTran.add(R.id.video, videoFragment);
        fragTran.commit();
    }

    //******************************************************************************
    // onStartFadeIn
    //******************************************************************************
    @Override
    public void onStartFadeIn()
    {
    }

    //******************************************************************************
    // onStartFadeOut
    //******************************************************************************
    @Override
    public void onStartFadeOut()
    {
        // hide the status and navigation bars
        int visibility = frameLayout.getSystemUiVisibility();
        visibility |= View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
        frameLayout.setSystemUiVisibility(visibility);
    }

    //******************************************************************************
    // onRequestPermissionsResult
    //******************************************************************************
    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults)
    {
        videoFragment.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
