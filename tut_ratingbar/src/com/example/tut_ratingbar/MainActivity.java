package com.example.tut_ratingbar;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.RatingBar;
import android.widget.Toast;

public class MainActivity extends Activity {
	RatingBar xValue;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		xValue = (RatingBar) findViewById(R.id.ratingBar1);
	}

	public void showrating(View v) {
		String xOutput = String.valueOf(xValue.getRating());
		Toast.makeText(getApplicationContext(), xOutput, 1000).show();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
