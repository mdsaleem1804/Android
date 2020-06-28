package com.example.ag_and_001_traffic_taxi;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter.LengthFilter;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class DriverRegistration extends Activity {
	private DbAdapter dbHelper;
	EditText xEdtDriveName, xEdtDriverDetails, xEdrDriverLicenceNo,
			xEdtDriverRoute, xEdtUserName, xEdtPassword;
	String xError;
	DataBaseConnection mCon;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		try
		{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.driverregistration);
		 mCon=new DataBaseConnection(this);
		xEdtDriveName = (EditText) findViewById(R.id.fedtDriverName);
		xEdtDriverDetails = (EditText) findViewById(R.id.fedtAddress);
		xEdrDriverLicenceNo = (EditText) findViewById(R.id.fedtLicenceNo);
		xEdtDriverRoute = (EditText) findViewById(R.id.fedtRoute);
		xEdtUserName = (EditText) findViewById(R.id.fedtRegUserName);
		xEdtPassword = (EditText) findViewById(R.id.fedtRegPassword);
		}
		catch(Exception e)
		{
			xError=e.toString();
		}

	}
	
	public void driverregistration(View v) {
		

		//dbHelper = new DbAdapter(this);
		//dbHelper.open();
		mCon.fn_DriverRegistration(xEdtDriveName.getText().toString(),
				xEdtDriverDetails.getText().toString(),
				xEdrDriverLicenceNo.getText().toString(), 
				xEdtDriverRoute.getText()
					.toString(), xEdtUserName.getText().toString(), xEdtPassword.getText().toString());
		
		DataClear();
		Toast.makeText(getApplicationContext(),
				"Driver Succesfully Registered", 1000).show();
		Intent intent = new Intent(DriverRegistration.this, DriverLogin.class);
		startActivity(intent);
	}
	public void driverlogin(View v)
	{
		Intent intent = new Intent(DriverRegistration.this,
				DriverLogin.class);
		startActivity(intent);
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void DataClear()
	{
		xEdtDriveName.setText("");
		xEdtDriverDetails.setText("");
		xEdrDriverLicenceNo.setText("");
		xEdtDriverRoute.setText("");
		xEdtUserName.setText("");
		xEdtPassword.setText("");
	}
}
