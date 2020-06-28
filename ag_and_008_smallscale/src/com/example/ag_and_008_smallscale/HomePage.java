package com.example.ag_and_008_smallscale;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.telephony.gsm.SmsManager;
import android.view.View;
import android.widget.Toast;

public class HomePage extends Activity {
	Cursor xCursor;
	private SQLiteDatabase db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.homepage);
		openDatabase();
		try {
			fn_CollectGpsValues();
		} catch (Exception e) {
			String xError = e.toString();
		}
	}

	public void vendorregistration(View v) {
		Intent intent = new Intent(HomePage.this, VendorRegistration.class);
		startActivity(intent);

	}

	public void vendorlogin(View v) {
		Intent intent = new Intent(HomePage.this, VendorLogin.class);
		startActivity(intent);

	}

	public void userlogin(View v) {
		Intent intent = new Intent(HomePage.this, UserLogin.class);
		startActivity(intent);

	}

	protected void openDatabase() {
		db = openOrCreateDatabase("ag_and_008_smallscale",
				Context.MODE_PRIVATE, null);
	}

	public void fn_CollectGpsValues() {
		GPSTracker gps;
		gps = new GPSTracker(this);
		if (gps.canGetLocation()) {
			 double latitude = gps.getLatitude();
			 double longitude = gps.getLongitude();

			if (latitude >= Double.valueOf("8")
					&& latitude <= Double.valueOf("9")) {

				if (longitude >= Double.valueOf("77")
						&& longitude <= Double.valueOf("78")) {
					String xQry = "Select * from product";
					xCursor = db.rawQuery(xQry, null);
					while (xCursor.moveToNext()) {
						sendSMS("9578795653", "Name - " + xCursor.getString(0)
								+ " Price - " + xCursor.getString(1)+ " Vendor Name - " + xCursor.getString(2));
					}
				}
			}

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
