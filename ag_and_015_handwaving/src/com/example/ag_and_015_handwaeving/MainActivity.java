package com.example.ag_and_015_handwaeving;

import java.util.List;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements SensorEventListener {

    int count = 1;
    private boolean init;
    private Sensor mAccelerometer;
    private SensorManager mSensorManager;
    private float x1, x2, x3;
    private static final float ERROR = (float) 7.0;

    private TextView counter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        counter = (TextView) findViewById(R.id.counter);
        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        List listOfSensorsOnDevice = mSensorManager.getSensorList(Sensor.TYPE_ALL);
       /* for (int i = 0; i < listOfSensorsOnDevice.size(); i++) {
            if (listOfSensorsOnDevice.get(i).getType() == Sensor.TYPE_ACCELEROMETER) {

                Toast.makeText(this, "ACCELEROMETER sensor is available on device", Toast.LENGTH_SHORT).show();


                init = false;

                mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
                mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);

            } else {

                Toast.makeText(this, "ACCELEROMETER sensor is NOT available on device", Toast.LENGTH_SHORT).show();
            }
        }*/


    }


    @Override
    public void onSensorChanged(SensorEvent e) {


        //Get x,y and z values
        float x,y,z;                                   
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

            //Handling ACCELEROMETER Noise
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


            //Horizontal Shake Detected!
            if (diffX > diffY) {

                counter.setText("Shake Count : "+ count);
                count = count+1;
                Toast.makeText(MainActivity.this, "Shake Detected!", Toast.LENGTH_SHORT).show();
            }
        }


    }


    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        //Noting to do!!
    }

    //Register the Listener when the Activity is resumed
    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }

    //Unregister the Listener when the Activity is paused
    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }
}
/*
import android.app.Activity;  
import android.graphics.Color;  
import android.hardware.Sensor;  
import android.hardware.SensorEvent;  
import android.hardware.SensorEventListener;  
import android.hardware.SensorManager;  
import android.os.Bundle;  
import android.view.View;  
import android.widget.Toast;  
  
public class MainActivity extends Activity implements SensorEventListener{  
    private SensorManager sensorManager;  
      private boolean isColor = false;  
      private View view;  
      private long lastUpdate;  
  
      @Override  
      public void onCreate(Bundle savedInstanceState) {  
        super.onCreate(savedInstanceState);  
        setContentView(R.layout.activity_main);  
        view = findViewById(R.id.textView);  
        view.setBackgroundColor(Color.GREEN);  
  
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);  
        lastUpdate = System.currentTimeMillis();  
      }  
      //overriding two methods of SensorEventListener  
      @Override  
      public void onAccuracyChanged(Sensor sensor, int accuracy) {}  
      @Override  
      public void onSensorChanged(SensorEvent event) {  
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {  
          getAccelerometer(event);  
        }  
  
      }  
  
      private void getAccelerometer(SensorEvent event) {  
        float[] values = event.values;  
        // Movement  
        float x = values[0];  
        float y = values[1];  
        float z = values[2];  
  
        float accelationSquareRoot = (x * x + y * y + z * z)  
            / (SensorManager.GRAVITY_EARTH * SensorManager.GRAVITY_EARTH);  
          
        long actualTime = System.currentTimeMillis();  
        Toast.makeText(getApplicationContext(),String.valueOf(accelationSquareRoot)+" "+  
                    SensorManager.GRAVITY_EARTH,Toast.LENGTH_SHORT).show();  
          
        if (accelationSquareRoot >= 2) //it will be executed if you shuffle  
        {  
            
          if (actualTime - lastUpdate < 200) {  
            return;  
          }  
          lastUpdate = actualTime;//updating lastUpdate for next shuffle  
           if (isColor) {  
            view.setBackgroundColor(Color.GREEN);  
  
          } else {  
            view.setBackgroundColor(Color.RED);  
          }  
          isColor = !isColor;  
        }  
      }  
  
      @Override  
      protected void onResume() {  
        super.onResume();  
        // register this class as a listener for the orientation and  
        // accelerometer sensors  
        sensorManager.registerListener(this,sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),  
                                    SensorManager.SENSOR_DELAY_NORMAL);  
      }  
  
      @Override  
      protected void onPause() {  
        // unregister listener  
        super.onPause();  
        sensorManager.unregisterListener(this);  
      }  
    }   
    
    /*
import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.ag_and_015_handwaeving.ShakeDetector.OnShakeListener;


public class MainActivity extends Activity {
	private SensorManager mSensorManager;
	private Sensor mAccelerometer;
	private ShakeDetector mShakeDetector;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		// ShakeDetector initialization
				mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
				mAccelerometer = mSensorManager
						.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
				mShakeDetector = new ShakeDetector();
				mShakeDetector.setOnShakeListener(new OnShakeListener() {
		 
					@Override
					public void onShake(int count) {
						
						 * The following method, "handleShakeEvent(count):" is a stub //
						 * method you would use to setup whatever you want done once the
						 * device has been shook.
						 
						handleShakeEvent(count);
					}
				});
	}
	public void handleShakeEvent(int xCount){
		Toast.makeText(getApplicationContext(), xCount, 1000).show();
		
	}
	@Override
	public void onResume() {
		super.onResume();
		// Add the following line to register the Session Manager Listener onResume
		mSensorManager.registerListener(mShakeDetector, mAccelerometer,	SensorManager.SENSOR_DELAY_UI);
	}
 
	@Override
	public void onPause() {
		// Add the following line to unregister the Sensor Manager onPause
		mSensorManager.unregisterListener(mShakeDetector);
		super.onPause();
	}
	
}*/