package com.example.yair.roboapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        findViewById(R.id.cameraBut).setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MenuActivity.this,CameraActivity.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.patrolBut).setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MenuActivity.this,PatrolActivity.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.photoBut).setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MenuActivity.this,PhotoActivity.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.aboutBut).setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MenuActivity.this,AboutActivity.class);
                startActivity(intent);
            }
        });
    }
}
