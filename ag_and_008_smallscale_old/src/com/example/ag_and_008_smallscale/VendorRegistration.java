package com.example.ag_and_008_smallscale;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class VendorRegistration extends Activity {
	private DbAdapter dbHelper;
	int xMessgeDisplayTime=1000;
	EditText xEdtVendorName, xEdtVendorLocation, xEdtVendorDetails,
			xEdtVendorLandmark, xEdtProductName,xEdtProductPrice,xEdtUserName, xEdtPassword;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.vendorregistration);

	}

	public void vendorlogin(View v) {
		Intent intent = new Intent(VendorRegistration.this, VendorLogin.class);
		startActivity(intent);

	}

	public void vendorregitration(View v) {
		xEdtVendorName = (EditText) findViewById(R.id.fedtVendorName);
		xEdtVendorLocation = (EditText) findViewById(R.id.fedtVendorLocation);
		xEdtVendorDetails = (EditText) findViewById(R.id.fedtDetails);
		xEdtVendorLandmark = (EditText) findViewById(R.id.fedtLandmark);
		xEdtProductName = (EditText) findViewById(R.id.fedtProductName);
		xEdtProductPrice = (EditText) findViewById(R.id.fedtProductPrice);
		xEdtUserName = (EditText) findViewById(R.id.fedtVenRegUserName);
		xEdtPassword = (EditText) findViewById(R.id.fedtVenRegUserPassword);


		dbHelper = new DbAdapter(this);
		dbHelper.open();
		dbHelper.CreateVendorDetails(xEdtVendorName.getText().toString(),
				xEdtVendorLocation.getText().toString(),
				xEdtVendorDetails.getText().toString(), 
				 xEdtVendorLandmark.getText().toString(),
				 xEdtProductName.getText().toString(),
				 xEdtProductPrice.getText().toString(),
					xEdtUserName.getText().toString(),
					xEdtPassword.getText().toString());
		
		DataClear();
		Toast.makeText(getApplicationContext(),
				"Vendor Succesfully Registered", xMessgeDisplayTime).show();
		Intent intent = new Intent(VendorRegistration.this, VendorLogin.class);
		startActivity(intent);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void DataClear() {
		xEdtVendorLocation.setText("");
		xEdtVendorDetails.setText("");
		xEdtVendorName.setText("");
		xEdtVendorLandmark.setText("");
		xEdtUserName.setText("");
		xEdtPassword.setText("");
	}
}
