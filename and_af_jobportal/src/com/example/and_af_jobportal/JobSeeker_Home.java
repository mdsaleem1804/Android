package com.example.and_af_jobportal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

public class JobSeeker_Home extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.f_jobseeker_home);
	}

	public void js_login(View v) {
		Intent xIntent = new Intent(getApplicationContext(),
				JobSeeker_Login.class);
		startActivity(xIntent);
	}
	
	public void js_signup(View v) {
		Intent xIntent = new Intent(getApplicationContext(),
				JobSeeker_Signup.class);
		startActivity(xIntent);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
