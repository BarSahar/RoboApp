package com.example.yair.roboapp;

import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);
        findViewById(R.id.login).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                String ip = ((EditText) findViewById(R.id.ipbox)).getText().toString();
                String username = ((EditText) findViewById(R.id.usernamebox)).getText().toString();
                String password = ((EditText) findViewById(R.id.passwordbox)).getText().toString();
            }
        });
    }
}