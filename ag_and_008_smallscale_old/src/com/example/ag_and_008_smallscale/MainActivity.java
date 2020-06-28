package com.example.ag_and_008_smallscale;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	public void vendorregistration(View v)
	{
		Intent intent = new Intent(MainActivity.this,
				VendorRegistration.class);
		startActivity(intent);
	}
	public void vendorlogin(View v)
	{
		Intent intent = new Intent(MainActivity.this,
				VendorLogin.class);
		startActivity(intent);
	}
	public void user(View v)
	{
		Intent intent = new Intent(MainActivity.this,
				ListView_User.class);
		startActivity(intent);
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
