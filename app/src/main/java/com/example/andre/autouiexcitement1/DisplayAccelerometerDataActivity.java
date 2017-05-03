package com.example.andre.autouiexcitement1;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class DisplayAccelerometerDataActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor sensorAccelerometer;
    private long lastUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_accelerometer_data);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensorAccelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        lastUpdate = System.currentTimeMillis();
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            getAccelerometer(event);
        }
    }

    // updates the textViews with the new event values with a given frequency
    private void getAccelerometer(SensorEvent event) {

        // limit UI updates to a given frequency
        long currentTime = event.timestamp;
        if (currentTime - lastUpdate < 200) {
            return;
        }
        lastUpdate = currentTime;

        // movement
        float x = event.values[0];
        float y = event.values[1];
        float z = event.values[2];

        // update textViews
        TextView textViewXValue = (TextView) findViewById(R.id.textViewXValue);
        textViewXValue.setText(Float.toString(x));
        TextView textViewYValue = (TextView) findViewById(R.id.textViewYValue);
        textViewYValue.setText(Float.toString(y));
        TextView textViewZValue = (TextView) findViewById(R.id.textViewZValue);
        textViewZValue.setText(Float.toString(z));
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, sensorAccelerometer,
                SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // foos and bars lived here in peace and harmony... until one day a foo dared to steal a
        // cookie from a bar...
    }
}
