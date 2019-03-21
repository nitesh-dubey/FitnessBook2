package com.nitesh.dubey.fitnessbook;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class FirstActivity extends AppCompatActivity implements SensorEventListener {

    //Button btn1,btn2;

    SensorManager sensormanager;
    TextView txt2,txt3,txt4,txt5,txt6;
    private long steps = 0;
    boolean isRunning = false;
    boolean startButtonPressed = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        sensormanager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        txt2 = (TextView) findViewById(R.id.txt2);
        txt4 = (TextView) findViewById(R.id.txt4);
        //btn2 = (Button) findViewById(R.id.btn2);
        txt3 = (TextView) findViewById(R.id.txt3);

        Sensor countSensor = sensormanager.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR);
        if(countSensor != null){
            sensormanager.registerListener(this,countSensor,SensorManager.SENSOR_DELAY_UI);
        } else {
            Toast.makeText(this,"NO sensor Found",Toast.LENGTH_LONG).show();
        }


    }

    public void stop (View v) {
        isRunning = false;
        startButtonPressed = false;
        if(steps < 1000)
        Toast.makeText(FirstActivity.this, "Congrats!! You Have Run " + steps + " steps",Toast.LENGTH_LONG).show();
        else {
            Toast.makeText(FirstActivity.this, "You are on Fire Today!!",Toast.LENGTH_LONG).show();
        }
        txt3.setText("Average calories burned:: " + (((float)steps/1000)*100) + " kcal");


    }

    public void reset (View v) {

        SharedPreferences saves = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        long stepsRunToday = saves.getLong("STEPS_RUN_TODAY", 0);
        long stepsRunThisMonth = saves.getLong("STEPS_RUN_THIS_MONTH", 0);
        long highestStepsRunInOneDay = saves.getLong("HIGHEST_STEPS_RUN_IN_ONE_DAY", 0);

        SharedPreferences.Editor editor = saves.edit();
        editor.putLong("STEPS_RUN_TODAY", steps + stepsRunToday);
        editor.putLong("STEPS_RUN_THIS_MONTH", steps + stepsRunThisMonth);
        if((steps+stepsRunToday) > highestStepsRunInOneDay) {
            editor.putLong("HIGHEST_STEPS_RUN_IN_ONE_DAY", steps+stepsRunToday);
        }
        editor.commit();

        steps = 0;
        txt2.setText("" + steps);
        isRunning = true;
        startButtonPressed = false;
        txt3.setText("");
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(startButtonPressed == true) isRunning = true;
//        Sensor countSensor = sensormanager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
//        if(countSensor != null){
//           sensormanager.registerListener(this,countSensor,SensorManager.SENSOR_DELAY_UI);
//        } else {
//            Toast.makeText(this,"NO sensor Found",Toast.LENGTH_LONG).show();
//        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        //Log.i("tagggggg",""+ steps);
        //isRunning = false;
        if(startButtonPressed == true) isRunning = true;
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if(isRunning && startButtonPressed) {
            steps++;
            Log.i("Tagggggg", "" + steps);
            txt2.setText("" + steps);
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    public void back (View view) {

        SharedPreferences saves = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        long stepsRunToday = saves.getLong("STEPS_RUN_TODAY", 0);
        long stepsRunThisMonth = saves.getLong("STEPS_RUN_THIS_MONTH", 0);
        long highestStepsRunInOneDay = saves.getLong("HIGHEST_STEPS_RUN_IN_ONE_DAY", 0);

        SharedPreferences.Editor editor = saves.edit();
        editor.putLong("STEPS_RUN_TODAY", steps + stepsRunToday);
        editor.putLong("STEPS_RUN_THIS_MONTH", steps + stepsRunThisMonth);
        if((steps+stepsRunToday) > highestStepsRunInOneDay) {
            editor.putLong("HIGHEST_STEPS_RUN_IN_ONE_DAY", steps+stepsRunToday);
        }
        editor.commit();


        sensormanager.unregisterListener(this);
        Intent back = new Intent(FirstActivity.this , MainActivity.class);
        startActivity(back);
        finish();
    }

    public void start (View view) {
        isRunning = true;
        startButtonPressed = true;
    }
}
