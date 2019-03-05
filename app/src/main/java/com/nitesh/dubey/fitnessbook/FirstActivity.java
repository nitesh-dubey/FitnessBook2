package com.nitesh.dubey.fitnessbook;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class FirstActivity extends AppCompatActivity implements SensorEventListener {

    Button btn1,btn2;

    SensorManager sensormanager;
    TextView txt2;
    private int steps = 0;
    boolean isRunning = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        sensormanager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        txt2 = (TextView) findViewById(R.id.txt2);
        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);

        Sensor countSensor = sensormanager.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR);
        if(countSensor != null){
            sensormanager.registerListener(this,countSensor,SensorManager.SENSOR_DELAY_UI);
        } else {
            Toast.makeText(this,"NO sensor Found",Toast.LENGTH_LONG).show();
        }
        isRunning = true;


    }

    public void stop (View v) {
        isRunning = false;
    }

    public void reset (View v) {
        steps = 0;
        txt2.setText("" + steps);
        isRunning = true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        isRunning = true;
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
        isRunning = false;
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if(!isRunning) return;
        steps++;
        Log.i("Tagggggg",""+ steps);
        txt2.setText("" + steps);


    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    public void back (View view) {
        sensormanager.unregisterListener(this);
        Intent back = new Intent(FirstActivity.this , MainActivity.class);
        startActivity(back);
    }
}
