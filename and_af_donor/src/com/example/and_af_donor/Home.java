package com.example.and_af_donor;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

public class Home extends Activity {
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.home);	    
	}
	public void adddonors(View v)
	{
		Intent intent = new Intent(Home.this, AddContacts.class);
		intent.putExtra("task", "add");
		startActivityForResult(intent, 100);	
	}
	public void needblood(View v)
	{

		Intent intent = new Intent(Home.this, NeedLocation.class);
		startActivityForResult(intent, 100);
	}

	public void adminlogin(View v)
	{
		Intent intent = new Intent(Home.this, Login.class);
		startActivityForResult(intent, 100);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}