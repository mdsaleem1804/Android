package com.tcssnellai.ag_and_004_womenscheme;

import java.util.ArrayList;
import java.util.List;

import com.tcssnellai.ag_and_004_womenscheme.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class AdminSchemeEntry extends Activity implements
		OnItemSelectedListener {
	EditText xEdtSchemeName, xEdtMinAge, xEdtMaxAge, xEdtAmount, xEdtDetail,
			xEdtWebsite;
	DataBaseConnection mCon;
	Button xBtnDeleteScheme, xBtnUpdateScheme;
	Spinner xSpnEducation, xSpnCaste, xSpnSection;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.adminschemeentry);
		mCon = new DataBaseConnection(this);
		xEdtSchemeName = (EditText) findViewById(R.id.fedtSchemeName);
		xEdtMinAge = (EditText) findViewById(R.id.fedtMinAge);
		xEdtMaxAge = (EditText) findViewById(R.id.fedtMaxAge);
		xEdtAmount = (EditText) findViewById(R.id.fedtAmount);
		xEdtDetail = (EditText) findViewById(R.id.fedtDetails);
		xEdtWebsite = (EditText) findViewById(R.id.fedtWebsite);
		xBtnDeleteScheme = (Button) findViewById(R.id.fbtnDeleteScheme);
		xBtnDeleteScheme.setVisibility(View.GONE);
		xBtnUpdateScheme = (Button) findViewById(R.id.fbtnUpdateScheme);
		xBtnUpdateScheme.setVisibility(View.GONE);

		// Spinner element
		xSpnEducation = (Spinner) findViewById(R.id.fspn_admineducation);
		xSpnCaste = (Spinner) findViewById(R.id.fspn_admincaste);
		xSpnSection = (Spinner) findViewById(R.id.fspn_adminsection);

		// Spinner click listener
		xSpnEducation.setOnItemSelectedListener(this);
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

	public void savescheme(View v) {
		if (xEdtSchemeName.getText().toString().matches("")) {
			xEdtSchemeName.setError("Scheme Name Required");
			return;
		}
		if (xEdtMinAge.getText().toString().matches("")) {
			xEdtMinAge.setError("Min Age Required");
			return;
		}

		if (xEdtMaxAge.getText().toString().matches("")) {
			xEdtMaxAge.setError("Max Age Required");
			return;
		}

		if (xEdtAmount.getText().toString().matches("")) {
			xEdtAmount.setError("Amount Required");
			return;
		}

		if (xEdtDetail.getText().toString().matches("")) {
			xEdtDetail.setError("Details Required");
			return;
		}

		if (xEdtWebsite.getText().toString().matches("")) {
			xEdtWebsite.setError("Website Required");
			return;
		}

		if (mCon.fn_GetSchemeRegistration(xEdtSchemeName.getText().toString())) {

			Toast.makeText(getApplicationContext(),
					"Scheme Already Registered", 1000).show();
		}

		else {
			if (mCon.fn_DataProcess(xEdtSchemeName.getText().toString(),
					xEdtMinAge.getText().toString(), xEdtMaxAge.getText()
							.toString(), xSpnEducation.getSelectedItem()
							.toString(),
					xSpnCaste.getSelectedItem().toString(), xSpnSection
							.getSelectedItem().toString(), xEdtAmount.getText()
							.toString(), xEdtDetail.getText().toString(),
					xEdtWebsite.getText().toString(), "S")) {
				Toast.makeText(getApplicationContext(), "Inserted", 1000)
						.show();
				Intent intent = new Intent(AdminSchemeEntry.this,
						HomePage.class);
				startActivity(intent);
			} else {
				Toast.makeText(getApplicationContext(), "Validation Required",
						1000).show();
			}
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
