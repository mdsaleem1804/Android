package com.example.ag_and_015_handwaeving;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.Toast;
import android.widget.ToggleButton;

public class DataManagement extends Activity implements SensorEventListener {

	int count = 0;
	private boolean init;
	private Sensor mAccelerometer;
	private SensorManager mSensorManager;
	private float x1, x2, x3;
	private static final float ERROR = (float) 7.0;
	ToggleButton toggleButton1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.datamanagement);
		toggleButton1 = (ToggleButton) findViewById(R.id.toggleButton1);

		mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
		List listOfSensorsOnDevice = mSensorManager
				.getSensorList(Sensor.TYPE_ALL);
		for (int i = 0; i < listOfSensorsOnDevice.size(); i++) {
			if (((Sensor) listOfSensorsOnDevice.get(i)).getType() == Sensor.TYPE_ACCELEROMETER) {

				Toast.makeText(this,
						"ACCELEROMETER sensor is available on device",
						Toast.LENGTH_SHORT).show();

				init = false;

				mAccelerometer = mSensorManager
						.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
				mSensorManager.registerListener(this, mAccelerometer,
						SensorManager.SENSOR_DELAY_NORMAL);

			} else {

				Toast.makeText(this,
						"ACCELEROMETER sensor is NOT available on device",
						Toast.LENGTH_SHORT).show();
			}
		}

	}

	@Override
	public void onSensorChanged(SensorEvent e) {

		// Get x,y and z values
		float x, y, z;
		x = e.values[0];
		y = e.values[1];
		z = e.values[2];

		if (!init) {
			x1 = x;
			x2 = y;
			x3 = z;
			init = true;
		} else {

			float diffX = Math.abs(x1 - x);
			float diffY = Math.abs(x2 - y);
			float diffZ = Math.abs(x3 - z);

			// Handling ACCELEROMETER Noise
			if (diffX < ERROR) {

				diffX = (float) 0.0;
			}
			if (diffY < ERROR) {
				diffY = (float) 0.0;
			}
			if (diffZ < ERROR) {

				diffZ = (float) 0.0;
			}

			x1 = x;
			x2 = y;
			x3 = z;

			// Horizontal Shake Detected!
			if (diffX > diffY) {

				// counter.setText("Shake Count : "+ count);
				count = count + 1;
				if (toggleButton1.getText().toString().equalsIgnoreCase("on")) {
					if (count >= 1 && count <= 5)// Lock Close
					{
						count = 0;
						finish();
						Intent intent = new Intent(DataManagement.this,
								HomePage.class);
						startActivity(intent);
					} else {
						count = 0;
					}
					Toast.makeText(getApplicationContext(), "Shake Detected!",
							Toast.LENGTH_SHORT).show();
				}
			}
		}

	}

	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// Noting to do!!
	}

	// Register the Listener when the Activity is resumed
	protected void onResume() {
		super.onResume();
		mSensorManager.registerListener(this, mAccelerometer,
				SensorManager.SENSOR_DELAY_NORMAL);
	}

	// Unregister the Listener when the Activity is paused
	protected void onPause() {
		super.onPause();
		mSensorManager.unregisterListener(this);
	}
}
