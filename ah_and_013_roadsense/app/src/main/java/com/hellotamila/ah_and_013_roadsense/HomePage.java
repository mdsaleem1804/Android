package com.hellotamila.ah_and_013_roadsense;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

public class HomePage extends Activity {

	private SQLiteDatabase db;
	private Cursor xCursor;
	String xMobileNo;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
	    try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.homepage);
        }
        catch(Exception e)
        {
            String xError=e.toString();
        }
	}

	protected void openDatabase() {
		db = openOrCreateDatabase("ag_and_001_trafficandtaxi",
				Context.MODE_PRIVATE, null);
	}

	public void user(View v) {
		Intent intent = new Intent(HomePage.this, UserMainPage.class);
		startActivity(intent);
	}

	public void driverregistration(View v) {
		Intent intent = new Intent(HomePage.this, DriverRegistration.class);
		startActivity(intent);
	}

	public void driverlogin(View v) {
		Intent intent = new Intent(HomePage.this, DriverLogin.class);
		startActivity(intent);
	}

	public void admin(View v) {
		Intent intent = new Intent(HomePage.this, AdminLogin.class);
		startActivity(intent);
	}

	public void setno(View v) {
		Intent intent = new Intent(HomePage.this, Register.class);
		startActivity(intent);
	}

	public void parent(View v) {
		Intent intent = new Intent(HomePage.this, ParentMainPage.class);
		startActivity(intent);
	}

	/*
	 * public void sendalert(View v) { openDatabase(); String xQry =
	 * "Select * from alertrecieverdetails"; xCursor = db.rawQuery(xQry, null);
	 * xCursor.moveToFirst(); xMobileNo=xCursor.getString(0);
	 * fn_CollectGpsValues(); }
	 */

	/*
	 * public void fn_CollectGpsValues() { GPSTracker gps; gps = new
	 * GPSTracker(this); if (gps.canGetLocation()) { double latitude =
	 * gps.getLatitude(); double longitude = gps.getLongitude(); String $xMapUrl
	 * = "http://maps.google.com/?q=" + latitude + "," + longitude;
	 * sendSMS(xMobileNo, $xMapUrl); } else {
	 * 
	 * gps.showSettingsAlert(); } }
	 * 
	 * public void sendSMS(String phoneNo, String msg) { try { SmsManager
	 * smsManager = SmsManager.getDefault(); smsManager.sendTextMessage(phoneNo,
	 * null, msg, null, null); Toast.makeText(getApplicationContext(),
	 * "Message Sent to "+phoneNo, Toast.LENGTH_LONG).show(); } catch (Exception
	 * ex) { Toast.makeText(getApplicationContext(), ex.getMessage().toString(),
	 * Toast.LENGTH_LONG).show(); ex.printStackTrace(); } }
	 */

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
