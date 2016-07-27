package com.pigeonstudios.russianpigeon.androidimpl.Input;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

/**
 * Created by DennisFedorchuk on 7/27/2016.
 */
public class AccelerometerHandler implements SensorEventListener {
    private float accelX;
    private float accelY;
    private float accelZ;

    public AccelerometerHandler(Context context){
        SensorManager manager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);   //get the service to get the accelerometer
        if(manager.getSensorList(Sensor.TYPE_ACCELEROMETER).size() != 0) {  // try to get the accelerometer list. if size 0 accelerometer does not exist
            Sensor accelerometer = manager.getSensorList(Sensor.TYPE_ACCELEROMETER).get(0); //get the first device in the list
            manager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_GAME); // register listener to the accelerometer with the refresh rate for a game
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        accelX = event.values[0];
        accelY = event.values[1];
        accelZ = event.values[2];
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        //accuracy will not change
    }

    public float getAccelX() {
        return accelX;
    }

    public float getAccelY() {
        return accelY;
    }

    public float getAccelZ() {
        return accelZ;
    }
}
