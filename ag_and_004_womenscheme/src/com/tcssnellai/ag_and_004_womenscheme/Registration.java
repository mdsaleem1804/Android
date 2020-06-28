package com.tcssnellai.ag_and_004_womenscheme;

import java.util.ArrayList;
import java.util.List;

import com.tcssnellai.ag_and_004_womenscheme.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class Registration extends Activity implements OnItemSelectedListener {
	DataBaseConnection mCon;
	EditText xEdtName, xEdtAge, xEdtAddress, xEdtUserName, xEdtPassword;
	Spinner xSpnEducation,xSpnCaste,xSpnSection;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.registration);
		mCon = new DataBaseConnection(this);
		xEdtName = (EditText) findViewById(R.id.fedtName);
		xEdtAge = (EditText) findViewById(R.id.fedtAge);
		xEdtAddress = (EditText) findViewById(R.id.fedtAddress);
		xEdtUserName = (EditText) findViewById(R.id.fedtUserName);
		xEdtPassword = (EditText) findViewById(R.id.fedtPassword);
		// Spinner element

		xSpnEducation = (Spinner) findViewById(R.id.fspn_education);
		xSpnCaste = (Spinner) findViewById(R.id.fspn_caste);
		xSpnSection = (Spinner) findViewById(R.id.fspn_section);
		// Spinner click listener
		xSpnEducation.setOnItemSelectedListener(this);
		xSpnCaste.setOnItemSelectedListener(this);
		xSpnSection.setOnItemSelectedListener(this);

		// Spinner Drop down elements
		List<String> xEducationList = new ArrayList<String>();
		xEducationList.add("Nil");
		xEducationList.add("HighScool");
		xEducationList.add("Bachelor");
		xEducationList.add("Diploma");
		xEducationList.add("Doctoral");
		xEducationList.add("Master");

		// Creating adapter for spinner
		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, xEducationList);

		// Drop down layout style - list view with radio button
		dataAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		// attaching data adapter to spinner
		xSpnEducation.setAdapter(dataAdapter);

		// Spinner Drop down elements
		List<String> xCasteList = new ArrayList<String>();
		xCasteList.add("All");
		xCasteList.add("Scheduled Castes");
		xCasteList.add("Scheduled Tribes");
		xCasteList.add("Most Backward Classes");
		xCasteList.add("Backward Classes");
		xCasteList.add("Backward Classes Muslims");

		// Creating adapter for spinner
		ArrayAdapter<String> dataAdapter1 = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, xCasteList);

		// Drop down layout style - list view with radio button
		dataAdapter1
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		// attaching data adapter to spinner
		xSpnCaste.setAdapter(dataAdapter1);

		// Spinner Drop down elements
		List<String> xSectionList = new ArrayList<String>();
		xSectionList.add("All");
		xSectionList.add("Married");
		xSectionList.add("UnMarried");
		xSectionList.add("Widow");
		// Creating adapter for spinner
		ArrayAdapter<String> xDAdpSection = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, xSectionList);
		// Drop down layout style - list view with radio button
		xDAdpSection
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// attaching data adapter to spinner
		xSpnSection.setAdapter(xDAdpSection);

	}

	public void registerwomensdetails(View v) {

		if (mCon.fn_WomenRegistration(xEdtName.getText().toString(), xEdtAge
				.getText().toString(), xSpnEducation.getSelectedItem().toString(),xSpnCaste.getSelectedItem().toString(),
				xSpnSection.getSelectedItem().toString(), xEdtAddress.getText()
				.toString(), xEdtUserName.getText().toString(), xEdtPassword
				.getText().toString())) {
			xEdtName.setText("");
			xEdtAge.setText("");
			xEdtAddress.setText("");
			xEdtUserName.setText("");
			xEdtPassword.setText("");
			Toast.makeText(getApplicationContext(), "Inserted", 1000).show();
		}
	}

	public void onItemSelected(AdapterView<?> parent, View view, int position,
			long id) {
		// On selecting a spinner item
		String item = parent.getItemAtPosition(position).toString();

		// Showing selected spinner item
		// Toast.makeText(parent.getContext(), "Selected: " + item,
		// Toast.LENGTH_LONG).show();
	}

	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
