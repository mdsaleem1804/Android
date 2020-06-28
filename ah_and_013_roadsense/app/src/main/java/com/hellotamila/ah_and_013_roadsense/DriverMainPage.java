package com.hellotamila.ah_and_013_roadsense;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class DriverMainPage extends Activity {
	String xGetDriverName;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.drivermainpage);
		Bundle b = new Bundle();
		b = getIntent().getExtras();
		xGetDriverName = b.getString("drivername");

/*		Intent intent = new Intent(DriverMainPage.this,
				GpsSettings.class);
		intent.putExtra("drivername", xGetDriverName);
		startActivity(intent);*/
	}

	public void getuserrequests(View v) {
		Intent intent = new Intent(DriverMainPage.this, ListUserRequests.class);
		intent.putExtra("drivername", xGetDriverName);
		startActivity(intent);
	}

	public void trafficupdates(View v) {
		Intent intent = new Intent(DriverMainPage.this,
				ListTrafficUpdates.class);
		startActivity(intent);
	}
	public void mapupdates(View v) {
		Intent intent = new Intent(DriverMainPage.this,
				GpsSettings.class);
		intent.putExtra("drivername", xGetDriverName);
		startActivity(intent);
	}
	public void payments(View v) {
		Toast.makeText(getApplicationContext(), "Payment Recieved",
				Toast.LENGTH_LONG).show();
	}
	
}