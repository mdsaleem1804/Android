package com.tcssnellai.ag_and_004_womenscheme;

import java.util.ArrayList;
import java.util.List;

import com.tcssnellai.ag_and_004_womenscheme.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class AdminSchemeEdit extends Activity implements OnItemSelectedListener {
	String xSelectedScheme;

	EditText xEdtSchemeName, xEdtMinAge, xEdtMaxAge, 
			xEdtAmount, xEdtDetail, xEdtWebsite;
	Button xBtnAddScheme;
	DataBaseConnection mCon;
	private SQLiteDatabase db;
	private Cursor xCursor;
	Spinner xSpnEducation, xSpnCaste, xSpnSection;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.adminschemeentry);
		Bundle b = new Bundle();
		b = getIntent().getExtras();
		openDatabase();
		xSelectedScheme = b.getString("name");
		mCon = new DataBaseConnection(this);
		xEdtSchemeName = (EditText) findViewById(R.id.fedtSchemeName);
		xEdtMinAge = (EditText) findViewById(R.id.fedtMinAge);
		xEdtMaxAge = (EditText) findViewById(R.id.fedtMaxAge);
		xEdtAmount = (EditText) findViewById(R.id.fedtAmount);
		xEdtDetail = (EditText) findViewById(R.id.fedtDetails);
		xEdtWebsite = (EditText) findViewById(R.id.fedtWebsite);
		
		xEdtSchemeName.setEnabled(false);
		xEdtMinAge.setEnabled(false);
		xEdtMaxAge.setEnabled(false);
		xEdtDetail.setEnabled(false);
		xEdtWebsite.setEnabled(false);
		
		xEdtSchemeName.setText(xSelectedScheme);

		String xQry = "Select * from schemes WHERE schemename='"
				+ xSelectedScheme + "'";
		xCursor = db.rawQuery(xQry, null);
		xCursor.moveToFirst();
		showRecords();

		xBtnAddScheme = (Button) findViewById(R.id.fbtnAddscheme);
		xBtnAddScheme.setVisibility(View.VISIBLE);

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

	protected void showRecords() {
		xEdtMinAge.setText(xCursor.getString(2));
		xEdtMaxAge.setText(xCursor.getString(3));
		xEdtAmount.setText(xCursor.getString(7));
		xEdtDetail.setText(xCursor.getString(8));
		xEdtWebsite.setText(xCursor.getString(9));

	}

	protected void openDatabase() {
		db = openOrCreateDatabase("ag_and_004_womenscheme1",
				Context.MODE_PRIVATE, null);
	}

	public void deletescheme(View v) {
		mCon.fn_DeleteSchemeDetails(xSelectedScheme);
		Intent intent = new Intent(AdminSchemeEdit.this, ListSchemes.class);
		startActivity(intent);
	}

	public void updatescheme(View v) {
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

		if (mCon.fn_DataProcess(xEdtSchemeName.getText().toString(), xEdtMinAge
				.getText().toString(), xEdtMaxAge.getText().toString(),
				xSpnEducation.getSelectedItem().toString(), xSpnCaste
						.getSelectedItem().toString(), xSpnSection
						.getSelectedItem().toString(), xEdtAmount.getText()
						.toString(), xEdtDetail.getText().toString(),
				xEdtWebsite.getText().toString(), "U")) {
			Toast.makeText(getApplicationContext(), "Updated", 1000).show();
			Intent intent = new Intent(AdminSchemeEdit.this, HomePage.class);
			startActivity(intent);
		} else {
			Toast.makeText(getApplicationContext(), "Validation Required", 1000)
					.show();
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
