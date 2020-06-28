package com.hellotamila.ah_and_013_roadsense;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class TrafficMainPage extends Activity {
	DataBaseConnection mCon;
	private SQLiteDatabase db;
	EditText xEdtSource, xEdtDestination, xEdtTollGate, xEdtDetails;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.trafficmainpage);
		mCon = new DataBaseConnection(this);

		xEdtSource = (EditText) findViewById(R.id.fEdtTrafficSource);
		xEdtDestination = (EditText) findViewById(R.id.fEdtTrafficDestination);
		xEdtTollGate = (EditText) findViewById(R.id.fEdtTollGate);
		xEdtDetails = (EditText) findViewById(R.id.fEdtTrafficDetails);
	}

	public void savetraffic(View v) {

		mCon.insertTraffic(xEdtSource.getText().toString(), xEdtDestination
				.getText().toString(), xEdtTollGate.getText().toString(),
				xEdtDetails.getText().toString());
		Toast.makeText(getApplicationContext(), " Traffic Noted", 1000).show();
	}
	public void viewrequests(View v) {
		Intent intent = new Intent(TrafficMainPage.this, ListUserRequests.class);
		startActivity(intent);
	}

}
