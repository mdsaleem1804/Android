package com.example.ag_and_001_traffic_taxi;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.telephony.gsm.SmsManager;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class GpsSettings extends Activity {
	EditText xEdtLatitude, xEdtLongtitude;
	DrawerLayout mDrawerLayout;
	ListView mDrawerList;
	ActionBarDrawerToggle mDrawerToggle;
	String mTitle = "", xGetDriverName;
	private SQLiteDatabase db;
	private Cursor xCursor;
	String xMobileNo;

	// Button xGetLocation;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.gpssettings);
		xEdtLatitude = (EditText) findViewById(R.id.fedtLatitude);
		xEdtLongtitude = (EditText) findViewById(R.id.fedtLongtitude);
		// xGetLocation=(Button) findViewById(R.id.fbtnGetLocation);
		Bundle b = new Bundle();
		b = getIntent().getExtras();
		xGetDriverName = b.getString("drivername");
		fn_CollectGpsValues();
		openDatabase();

		fn_CollectGpsValues();
	}

	protected void openDatabase() {
		db = openOrCreateDatabase("ag_and_001_trafficandtaxi",
				Context.MODE_PRIVATE, null);
	}

	public void getlocation(View v) {
		fn_CollectGpsValues();
	}

	public void drivergpsupdate(View v) {
		try {

			ContentValues contentValues = new ContentValues();
			contentValues.put("latitude", xEdtLatitude.getText().toString());
			contentValues.put("longitude", xEdtLongtitude.getText().toString());

			db.update("driverregistration", contentValues, "username='"
					+ xGetDriverName + "'", null);
			Toast.makeText(getApplicationContext(), "Updated", 1000).show();
		} catch (Exception e) {
			e.toString();
		}

	}

	public void fn_CollectGpsValues() {
		GPSTracker gps;
		gps = new GPSTracker(this);
		if (gps.canGetLocation()) {
			double latitude = gps.getLatitude();
			double longitude = gps.getLongitude();
			xEdtLatitude.setText(String.valueOf(latitude));
			xEdtLongtitude.setText(String.valueOf(longitude));
			// String $xMapUrl = "http://maps.google.com/?q=" + latitude + ","
			// + longitude;
			// sendSMS(xMobileNo, $xMapUrl);
		} else {

			gps.showSettingsAlert();
		}
	}

	public void sendSMS(String phoneNo, String msg) {
		try {
			SmsManager smsManager = SmsManager.getDefault();
			smsManager.sendTextMessage(phoneNo, null, msg, null, null);
			Toast.makeText(getApplicationContext(), "Message Sent",
					Toast.LENGTH_LONG).show();
		} catch (Exception ex) {
			Toast.makeText(getApplicationContext(), ex.getMessage().toString(),
					Toast.LENGTH_LONG).show();
			ex.printStackTrace();
		}
	}
}