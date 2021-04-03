package com.example.todoapp;
/*
Name: Suraj B.K
ID: C7205073
Developing Mobile Applications
* */
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                SharedPreferences preferences = getApplicationContext().getSharedPreferences("todo_pref", 0);
                Boolean authentication = preferences.getBoolean("authentication", false);
                if(authentication){
                    Intent intent = new Intent(SplashActivity.this,MainActivity.class
                    );
                    startActivity(intent);
                }else{
                    Intent intent =  new Intent(SplashActivity.this,LoginActivity.class);
                    startActivity(intent);
                }
                finish();
            }
        }, 3000);
    }
}