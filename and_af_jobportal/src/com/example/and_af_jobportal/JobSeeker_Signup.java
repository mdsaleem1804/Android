package com.example.and_af_jobportal;

import java.util.ArrayList;
import java.util.List;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

public class JobSeeker_Signup extends Activity {

	String xSelectedCity;
	private DBHelper mydb;
	EditText xEdtFullName, xEdtUserName, xEdtPassword, xEdtMobileNo;
	Spinner xSpnCity;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mydb = new DBHelper(this);
		//int x = mydb.numberOfRows();
		setContentView(R.layout.f_jobseeker_signup);
		xEdtFullName = (EditText) findViewById(R.id.edtFullName);
		xEdtMobileNo = (EditText) findViewById(R.id.edtMobileNo);
		xEdtPassword = (EditText) findViewById(R.id.edtPassword);
		xEdtUserName = (EditText) findViewById(R.id.edtUserName);

		xSpnCity = (Spinner) findViewById(R.id.spnCity);
		populateSpinner();

	}

	public void btn_js_signup(View v) {
		mydb.insertJoseekerSignup(xEdtFullName.getText().toString(),
				xEdtUserName.getText().toString(), xEdtPassword.getText()
						.toString(), xEdtMobileNo.getText().toString(),
				xSelectedCity);
	}

	private void populateSpinner() {
		List<String> lables = new ArrayList<String>();

		// txtCategory.setText("");

		lables.add("Tirunelveli");
		lables.add("Kovilpatti");
		lables.add("Tuticorin");

		// Creating adapter for spinner
		ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, lables);

		// Drop down layout style - list view with radio button
		spinnerAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		// attaching data adapter to spinner
		xSpnCity.setAdapter(spinnerAdapter);
		xSpnCity.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				xSelectedCity = xSpnCity.getSelectedItem().toString();

			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub

			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
