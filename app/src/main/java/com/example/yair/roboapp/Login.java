package com.example.yair.roboapp;

import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class Login extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);

        try {
            findViewById(R.id.login).setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    String ip = ((EditText) findViewById(R.id.ipbox)).getText().toString();
                    String username = ((EditText) findViewById(R.id.usernamebox)).getText().toString();
                    String password = ((EditText) findViewById(R.id.passwordbox)).getText().toString();
                    ip="79.178.101.120";
                    // Instantiate the RequestQueue.
                    RequestQueue queue = Volley.newRequestQueue(Login.this);
                    String url = "http://" + ip + ":8080/Login?user=" + username + "&pass=" + password;


                    StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    if (response.equals("ok")) {

                                        Toast.makeText(Login.this, "ok", Toast.LENGTH_LONG).show();
                                    }
                                    else
                                        Toast.makeText(Login.this, "not ok", Toast.LENGTH_LONG).show();


                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(Login.this, "NOP!" + error, Toast.LENGTH_LONG).show();
                        }
                    });
                    queue.add(stringRequest);

                }
            });
        } catch (Exception e) {
            Toast.makeText(Login.this, "NOP!" + e.toString(), Toast.LENGTH_LONG).show();


        }


    }
}