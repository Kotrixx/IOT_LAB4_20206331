package com.example.iot_lab4_20206331.data.sensor;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

public class AccelerometerManager implements SensorEventListener {

    public interface ShakeListener {
        void onShake();
    }

    private final SensorManager sensorManager;
    private final Sensor accelerometer;
    private final ShakeListener shakeListener;

    private float accelerationCurrent = SensorManager.GRAVITY_EARTH;
    private float accelerationLast = SensorManager.GRAVITY_EARTH;
    private float shake = 0f;
    private static final float SHAKE_THRESHOLD = 20f;

    public AccelerometerManager(Context context, ShakeListener listener) {
        this.sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        this.accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        this.shakeListener = listener;
    }

    public void register() {
        if (accelerometer != null) {
            sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_UI);
        }
    }

    public void unregister() {
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float x = event.values[0];
        float y = event.values[1];
        float z = event.values[2];

        accelerationLast = accelerationCurrent;
        accelerationCurrent = (float) Math.sqrt(x * x + y * y + z * z);
        float delta = accelerationCurrent - accelerationLast;
        shake = shake * 0.9f + delta;

        if (shake > SHAKE_THRESHOLD) {
            shakeListener.onShake();
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {}
}

