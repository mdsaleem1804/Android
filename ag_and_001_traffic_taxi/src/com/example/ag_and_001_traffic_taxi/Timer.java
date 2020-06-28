package com.example.ag_and_001_traffic_taxi;

import java.util.TimerTask;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class Timer extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.timer);
		int delay = 1000;// in ms=5 SECS
		/*Timer timer = new Timer();

		timer.schedule(new TimerTask() {
			public void run() {
	
				Intent intent = new Intent(Timer.this,
						HomePage.class);
					startActivity(intent);
			}
		}, delay);
	*/
	Intent intent = new Intent(Timer.this,
			HomePage.class);
		startActivity(intent);
	}
}