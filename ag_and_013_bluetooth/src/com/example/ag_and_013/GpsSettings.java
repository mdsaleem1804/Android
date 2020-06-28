package com.example.ag_and_013;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.telephony.gsm.SmsManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class GpsSettings extends Activity {
	EditText xEdtLatitude, xEdtLongtitude;
	DrawerLayout mDrawerLayout;
	ListView mDrawerList;
	ActionBarDrawerToggle mDrawerToggle;	
	String mTitle="";
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
		fn_CollectGpsValues();
		openDatabase();
		String xQry = "Select * from alertrecieverdetails";
		xCursor = db.rawQuery(xQry, null);
		xCursor.moveToFirst();
		xMobileNo=xCursor.getString(0);
		fn_CollectGpsValues();
	}
	protected void openDatabase() {
		db = openOrCreateDatabase("ag_and_013_bluetooth.db",
				Context.MODE_PRIVATE, null);
	}
	public void getlocation(View v) {
		fn_CollectGpsValues();
	}

	public void fn_CollectGpsValues() {
		GPSTracker gps;
		gps = new GPSTracker(this);
		if (gps.canGetLocation()) {
			double latitude = gps.getLatitude();
			double longitude = gps.getLongitude();
			xEdtLatitude.setText(String.valueOf(latitude));
			xEdtLongtitude.setText(String.valueOf(longitude));
			String $xMapUrl="http://maps.google.com/?q="+latitude+","+longitude;
			sendSMS(xMobileNo,$xMapUrl);
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