package com.example.ag_and_22_medical;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class IpAdmission extends Activity implements OnItemSelectedListener {
	Spinner xSpnPatientName, xSpnCaseType, xSpnRoomNo, xSpnRoomType;
	EditText xEdtAdmissionDate;
	DataBaseConnection mCon;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ipadmission);
		mCon = new DataBaseConnection(this);
		// Spinner element
		xSpnPatientName = (Spinner) findViewById(R.id.fSpnPatientName);
		xSpnCaseType = (Spinner) findViewById(R.id.fSpnCaseType);
		xSpnRoomNo = (Spinner) findViewById(R.id.fSpnRoomNo);
		xSpnRoomType = (Spinner) findViewById(R.id.fSpnRoomType);
		xEdtAdmissionDate = (EditText) findViewById(R.id.fEdtAdmissionDate);
		xEdtAdmissionDate.setEnabled(false);
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		xEdtAdmissionDate.setText(dateFormat.format(new Date())); // it will
																	// show
																	// 16/07/2013

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

		// Spinner click listener
		xSpnCaseType.setOnItemSelectedListener(this);
		// Spinner Drop down elements
		List<String> xSection4List = new ArrayList<String>();
		xSection4List.add("General");
		xSection4List.add("Delivery");
		xSection4List.add("Appandix");

		// Creating adapter for spinner
		ArrayAdapter<String> dataAdapter4 = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, xSection4List);

		// Drop down layout style - list view with radio button
		dataAdapter4
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		// attaching data adapter to spinner
		xSpnCaseType.setAdapter(dataAdapter4);
		// Spinner Drop down elements
		List<String> xSection2List = new ArrayList<String>();
		xSection2List.add("101");
		xSection2List.add("102");
		xSection2List.add("103");
		xSection2List.add("104");

		// Creating adapter for spinner
		ArrayAdapter<String> dataAdapter1 = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, xSection2List);

		// Drop down layout style - list view with radio button
		dataAdapter1
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		// attaching data adapter to spinner
		xSpnRoomNo.setAdapter(dataAdapter1);

		// Spinner Drop down elements
		List<String> xSection3List = new ArrayList<String>();
		xSection3List.add("Basic");
		xSection3List.add("Delux");
		xSection3List.add("SemiDelux");
		// Creating adapter for spinner
		ArrayAdapter<String> xDAdpSection = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, xSection3List);
		// Drop down layout style - list view with radio button
		xDAdpSection
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// attaching data adapter to spinner
		xSpnRoomType.setAdapter(xDAdpSection);
	}
public void saveip(View v)
{
	if (mCon.fn_Ipegistration(xSpnPatientName.getSelectedItem().toString(),
			xSpnCaseType.getSelectedItem().toString(),
			xSpnRoomNo.getSelectedItem().toString(),
			xSpnRoomType.getSelectedItem().toString(),
			xEdtAdmissionDate.getText().toString())) {

		Toast.makeText(getApplicationContext(), "Inserted", 1000).show();
	}
}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
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
