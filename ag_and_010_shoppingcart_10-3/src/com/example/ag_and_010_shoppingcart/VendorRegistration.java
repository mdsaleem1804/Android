package com.example.ag_and_010_shoppingcart;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class VendorRegistration extends Activity implements OnItemSelectedListener{
	DataBaseConnection mCon;
	int xMessgeDisplayTime = 1000;
	EditText xEdtVendorName, xEdtVendorLocation, xEdtVendorDetails,
			xEdtVendorLandmark, xEdtProductName, xEdtUserName, xEdtPassword;
	Spinner xSpnFloor;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.vendorregistration);
		xSpnFloor = (Spinner) findViewById(R.id.fSpnFloor);
		// Spinner click listener
		xSpnFloor.setOnItemSelectedListener(this);
		// Spinner click listener
		// xSpnFloor.setOnItemSelectedListener(this);
		// Spinner Drop down elements
		List<String> xFloorList = new ArrayList<String>();
		xFloorList.add("FirstFloor");
		xFloorList.add("SecondFloor");
		xFloorList.add("ThirdFloor");
		xFloorList.add("FourthFloor");
		xFloorList.add("FifthFloor");

		// Creating adapter for spinner
		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, xFloorList);

		// Drop down layout style - list view with radio button
		dataAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		// attaching data adapter to spinner
		xSpnFloor.setAdapter(dataAdapter);
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

		xEdtUserName = (EditText) findViewById(R.id.fedtVenRegUserName);
		xEdtPassword = (EditText) findViewById(R.id.fedtVenRegUserPassword);
	

		mCon = new DataBaseConnection(this);
		mCon.fn_InsertVendorDetails(xEdtVendorName.getText().toString(),
				xEdtVendorLocation.getText().toString(), xEdtVendorDetails
						.getText().toString(), xEdtVendorLandmark.getText()
						.toString(), xEdtProductName.getText().toString(),
				xSpnFloor.getSelectedItem().toString(), xEdtUserName.getText()
						.toString(), xEdtPassword.getText().toString());

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

	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		
	}
}
