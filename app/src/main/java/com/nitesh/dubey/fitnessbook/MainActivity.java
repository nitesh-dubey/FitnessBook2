package com.nitesh.dubey.fitnessbook;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

//    private static int SPLASH_TIME_OUT = 4000;
    TextView txt1,txt2,txt3,txt4,txt5,txt6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                Intent welcomeScreen = new Intent(MainActivity.this , welcomeScreen.class);
//                startActivity(welcomeScreen);
//            }
//        },SPLASH_TIME_OUT);

        txt1 = (TextView) findViewById(R.id.txt1);
        txt2 = (TextView) findViewById(R.id.txt2);
        txt3 = (TextView) findViewById(R.id.txt3);
        txt4 = (TextView) findViewById(R.id.txt4);
        txt5 = (TextView) findViewById(R.id.txt5);
        txt6 = (TextView) findViewById(R.id.txt6);

        txt1.setMovementMethod(LinkMovementMethod.getInstance());
        txt2.setMovementMethod(LinkMovementMethod.getInstance());
        txt3.setMovementMethod(LinkMovementMethod.getInstance());
        txt4.setMovementMethod(LinkMovementMethod.getInstance());
        txt6.setMovementMethod(LinkMovementMethod.getInstance());
    }

    public void openPedometer (View view) {
        Intent pedometer = new Intent(MainActivity.this , FirstActivity.class);
        startActivity(pedometer);
    }

}