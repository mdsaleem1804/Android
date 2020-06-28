package com.example.ag_and_22_medical;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.os.Bundle;
import android.os.StrictMode;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

public class Advance extends Activity implements OnItemSelectedListener {
	Spinner xSpnPatientName;
	EditText xEdtAdvanceDate,xEdtAdvanceAmount;
	DataBaseConnection mCon;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.advance);
		xSpnPatientName = (Spinner) findViewById(R.id.fSpnPatientName);
		xEdtAdvanceDate = (EditText) findViewById(R.id.fEdtAdvanceDate);
		xEdtAdvanceAmount = (EditText) findViewById(R.id.fEdtAdvanceAmount);		
		xEdtAdvanceDate.setEnabled(false);
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		xEdtAdvanceDate.setText(dateFormat.format(new Date())); 
		
		if (android.os.Build.VERSION.SDK_INT > 9) {
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
					.permitAll().build();
			StrictMode.setThreadPolicy(policy);
		}
		// Spinner click listener
		xSpnPatientName.setOnItemSelectedListener(this);
		// Spinner Drop down elements
		List<String> xSection1List = new ArrayList<String>();
		xSection1List.add("Ambika");
		xSection1List.add("Raja Kani");
		xSection1List.add("Tamilisai Kannan");

		// Creating adapter for spinner
		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, xSection1List);

		// Drop down layout style - list view with radio button
		dataAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		// attaching data adapter to spinner
		xSpnPatientName.setAdapter(dataAdapter);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	
	public void saveadvance(View v)
	{
		if (mCon.fn_Advance(xSpnPatientName.getSelectedItem().toString(),
			
				xEdtAdvanceDate.getText().toString(),xEdtAdvanceAmount.getText().toString())) {

			Toast.makeText(getApplicationContext(), "Inserted", 1000).show();
		}
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
