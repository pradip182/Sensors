package com.example.sensors;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

public class GyroscopeActivity extends AppCompatActivity {

    private SensorManager sensorManager;
    private EditText etxaxis, etyaxis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gyroscope);

        etxaxis = findViewById(R.id.etxaxis);
        etyaxis = findViewById(R.id.etyaxis);
        sensorGyro();

    }

    private void sensorGyro() {
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        Sensor sensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);

        SensorEventListener gyrolistener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                if (event.values[1]<0){
                    etxaxis.setText("left");
                }
                else if (event.values[1]>0){
                    etxaxis.setText("right");
                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        };
        if (sensor !=null){
            sensorManager.registerListener(gyrolistener,sensor,SensorManager.SENSOR_DELAY_NORMAL);

        }else {
            Toast.makeText(this, "no sensor found", Toast.LENGTH_SHORT).show();
        }

    }
}