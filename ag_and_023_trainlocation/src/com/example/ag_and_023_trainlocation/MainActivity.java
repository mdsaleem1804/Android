package com.example.ag_and_023_trainlocation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.homepage);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	public void traindata(View v) {
		Intent xIntent = new Intent(MainActivity.this, TrainLocation.class);
		startActivity(xIntent);

	}

	public void way(View v) {
		Intent xIntent = new Intent(MainActivity.this, TextTospeech.class);
		startActivity(xIntent);
	}
}
