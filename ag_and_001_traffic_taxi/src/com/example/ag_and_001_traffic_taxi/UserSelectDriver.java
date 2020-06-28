package com.example.ag_and_001_traffic_taxi;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class UserSelectDriver extends Activity {
	String xGetUserMobileNo, xGetDriverName;
	EditText xEdtMobileNo, xEdtDriverName;
	DataBaseConnection mCon;
	private SQLiteDatabase db;
	Cursor xCursor;
	String $xMapUrl;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.userselectdriver);

		mCon = new DataBaseConnection(this);

		xEdtMobileNo = (EditText) findViewById(R.id.fEdtSendMobileNo);
		xEdtDriverName = (EditText) findViewById(R.id.fEdtSendDriverName);

		Bundle b = new Bundle();
		b = getIntent().getExtras();
		xGetUserMobileNo = b.getString("usermobileno");
		xGetDriverName = b.getString("drivername");
		xEdtMobileNo.setText(xGetUserMobileNo);
		xEdtDriverName.setText(xGetDriverName);
		openDatabase();
		String xQry = "Select * from driverregistration where username='"+xGetDriverName + "'";
		xCursor = db.rawQuery(xQry, null);
		xCursor.moveToFirst();
		String xLatitude = xCursor.getString(7);
		String xLongitude = xCursor.getString(8);
		 $xMapUrl = "http://maps.google.com/?q=" + xLatitude + ","
				+ xLongitude;

	}

	public void sendrequest(View v) {

		mCon.insertUserSendRequest(xGetUserMobileNo, xGetDriverName);
		Toast.makeText(getApplicationContext(), " Request Send", 1000).show();
	}

	protected void openDatabase() {
		db = openOrCreateDatabase("ag_and_001_trafficandtaxi",
				Context.MODE_PRIVATE, null);
	}

	public void getmap(View v) {
		Intent i = new Intent(Intent.ACTION_VIEW);
		i.setData(Uri.parse($xMapUrl));
		startActivity(i);
	}
}
