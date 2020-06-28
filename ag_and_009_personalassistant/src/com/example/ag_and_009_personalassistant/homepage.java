package com.example.ag_and_009_personalassistant;

import com.example.ag_and_009_dailystudentactivities.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

public class homepage extends Activity {

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

	public void login(View v) {
		Intent intent = new Intent(homepage.this, Login.class);
		startActivity(intent);
	}

	public void setpin(View v) {

		Intent intent = new Intent(homepage.this, SetPin.class);
		startActivity(intent);
	}
}