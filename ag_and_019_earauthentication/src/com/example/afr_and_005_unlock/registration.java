package com.example.afr_and_005_unlock;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class registration extends Activity {

	DataBaseConnection mCon;
	Button xbtnOk, xbtnCancel;
	Spinner xspnGender, xspnDept;

	EditText xedtname, xedtage, xedtusername, xedtpassword;
	private String[] xGender = { "Male", "Female" };
	private String[] xDmy = { "Days", "Months", "Years" };
	String xSelectedGender, xSelectedDmy;
	product prod;
	DataBaseConnection db;
	Long xAge;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.registration);
		db = new DataBaseConnection(this);
		prod = new product();
		initViews();
	    SpinnerControls();

		loadData();
		/**
		 * CRUD Operations
		 * */

	}

	public void onItemSelected(AdapterView<?> parent, View view, int position,
			long id) {
		xSelectedGender = xspnGender.getSelectedItem().toString();
		xSelectedDmy = xspnDept.getSelectedItem().toString();

	}

	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub

	}

	public void SpinnerControls()
	{
		ArrayAdapter<String> adapter_year = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, xGender);
		adapter_year
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		xspnGender.setAdapter(adapter_year);

		ArrayAdapter<String> adapter_department = new ArrayAdapter<String>(
				this, android.R.layout.simple_spinner_item, xDmy);
		adapter_department
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		xspnDept.setAdapter(adapter_department);
	}
	public void initViews() {
		xspnGender = (Spinner) findViewById(R.id.spnGender);
		xspnDept = (Spinner) findViewById(R.id.spnDmy);
		xedtname = (EditText) findViewById(R.id.edtname);
		xedtage = (EditText) findViewById(R.id.edtage);
		xedtusername = (EditText) findViewById(R.id.edtusername);
		xedtpassword = (EditText) findViewById(R.id.edtpassword);
		xbtnOk = (Button) findViewById(R.id.btnok);
		xbtnCancel = (Button) findViewById(R.id.btncancel);
	}

	private boolean isValidMobile(String phone2) 
	{
	    boolean check;
	    if(phone2.length() > 3)
	    {
	        check = false;
	        xedtage.setError("Not Valid Number");
	    }
	    else
	    {
	        check = true;
	    }
	    return check;
	}
	private void loadData() {
		xbtnOk.setOnClickListener(new OnClickListener() {
			@SuppressLint("ShowToast")
			@Override
			public void onClick(View v) {
				try {
					if (xedtname.getText().toString().matches("")) {
					    Toast.makeText(getApplicationContext(), "You did not enter a name", Toast.LENGTH_SHORT).show();
					    xedtname.requestFocus();
					    return;
					}
					if (xedtage.getText().toString().matches("")) {
					    Toast.makeText(getApplicationContext(), "You did not enter a mobile number", Toast.LENGTH_SHORT).show();
					    xedtage.requestFocus();
					    return;
					}
					if (xedtusername.getText().toString().matches("")) {
					    Toast.makeText(getApplicationContext(), "You did not enter a username", Toast.LENGTH_SHORT).show();
					    xedtusername.requestFocus();
					    return;
					}
					if (xedtpassword.getText().toString().matches("")) {
					    Toast.makeText(getApplicationContext(), "You did not enter a password", Toast.LENGTH_SHORT).show();
					    xedtpassword.requestFocus();
					    return;
					}
					if(!isValidMobile(xedtage.getText().toString()))
					{
						 Toast.makeText(getApplicationContext(), "Age Must be Less than 3 Digit", Toast.LENGTH_SHORT).show();
						 xedtage.requestFocus();
						    return;
					}
//					if(db.CheckMobileNo(xedtage.getText().toString()))
//					{
//						 Toast.makeText(getApplicationContext(), "Mobile Number Already Registered With us", Toast.LENGTH_SHORT).show();
//						 xedtage.requestFocus();
//						 return;
//					}
					prod.setName(xedtname.getText().toString());
					prod.setGender(xSelectedGender);
					prod.setDmy(xSelectedDmy);
					xAge = Long.valueOf(xedtage.getText()
							.toString());
					prod.setAge(xAge);
					prod.setUsername(xedtusername.getText().toString());
					prod.setPassword(xedtpassword.getText().toString());
					if (db.InsertUsers(prod)) {
						Toast.makeText(getApplicationContext(),
								"Registered Success", 1000).show();
						finish();
						Intent intent = new Intent(registration.this,
								MainActivity.class);
						startActivity(intent);

					} else {
						Toast.makeText(getApplicationContext(),
								"Registration Failed", 1000).show();
						xedtname.setText("");
						xedtage.setText("");
						xedtusername.setText("");
						xedtpassword.setText("");
					}
				} catch (Exception e) {
					Log.e(STORAGE_SERVICE, e.toString());

				}
			}

		});
		
		xbtnCancel.setOnClickListener(new OnClickListener() {

	        @SuppressLint("ShowToast")
			public void onClick(View v) {
				xedtname.setText("");
				xedtage.setText("");
				xedtusername.setText("");
				xedtpassword.setText("");
	        	
	        }
	    });
	}

	
	
}
