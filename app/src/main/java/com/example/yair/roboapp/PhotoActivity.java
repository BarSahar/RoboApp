package com.example.yair.roboapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class PhotoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);
        //final String ip = Login.ip;
        final String ip = "79.178.101.120";
        FloatingActionButton syncButton = (FloatingActionButton)findViewById(R.id.getPics);
        syncButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                Toast.makeText(PhotoActivity.this, "Ip: "+ip , Toast.LENGTH_LONG).show();
                RequestQueue queue = Volley.newRequestQueue(PhotoActivity.this);
                String req="http://"+ip+":8080/Pics";

                StringRequest stringRequest = new StringRequest(Request.Method.GET, req,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

                                byte[] decodedByte = Base64.decode(response, Base64.DEFAULT);
                                Bitmap decodedImg = BitmapFactory.decodeByteArray(decodedByte, 0, decodedByte.length);
                                ImageView img = (ImageView)findViewById(R.id.imageView);
                                img.setImageBitmap(decodedImg);
                                Bitmap huhuhuhu = BitmapFactory.decodeByteArray(decodedByte, 0, decodedByte.length);
                                //ListView lv = (ListView)findViewById(R.id.picList).add
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(PhotoActivity.this, "Bad Api: " + error, Toast.LENGTH_LONG).show();
                    }
                });
                queue.add(stringRequest);
            }
        });



    }
}
