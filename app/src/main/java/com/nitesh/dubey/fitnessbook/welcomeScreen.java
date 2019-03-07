package com.nitesh.dubey.fitnessbook;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class welcomeScreen extends AppCompatActivity {

    private static int SPLASH_TIME_OUT = 4000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent loginScreen = new Intent(welcomeScreen.this ,LoginActivity.class);
                startActivity(loginScreen);
                finish();
            }
        },SPLASH_TIME_OUT);

    }
}
