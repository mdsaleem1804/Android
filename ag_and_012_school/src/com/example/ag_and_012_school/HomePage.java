package com.example.ag_and_012_school;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

public class HomePage extends Activity {

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
	
	
	public void admin(View v)
	{
		Intent intent = new Intent(HomePage.this,
				AdminLogin.class);
		startActivity(intent);
	}
	
	public void studentresults(View v)
	{
		Intent intent = new Intent(HomePage.this,
				StudentLogin.class);
		startActivity(intent);
	}
	public void attendencereport(View v)
	{
		Intent intent = new Intent(HomePage.this,
				StudentLogin_Attend.class);
		startActivity(intent);
	}
	
}