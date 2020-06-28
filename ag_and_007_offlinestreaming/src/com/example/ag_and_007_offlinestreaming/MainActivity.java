package com.example.ag_and_007_offlinestreaming;


import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.webkit.WebView;

public class MainActivity extends Activity {
	WebView xGooglePage;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		int delay = 1000;// in ms=5 SECS
		Timer timer = new Timer();

		timer.schedule(new TimerTask() {
			public void run() {
	
				Intent intent = new Intent(MainActivity.this,
						StreamingPage.class);
					startActivity(intent);
			}
		}, delay);
		


		//xGooglePage=(WebView)findViewById(R.id.webView1);
		//xGooglePage.loadUrl("http://www.javatpoint.com/");
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
