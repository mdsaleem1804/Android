package com.example.ag_and_012_school;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class StudentRegistration extends Activity {
	Spinner xSpnDepartment, xSpnYear;
	DataBaseConnection mCon;
	EditText xEdtStudentName, xEdtUsername, xEdtPassword, xEdtMobileNo,
			xEdtGender, xEdtRollNo, xEdtRegNo;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.studentregistration);
		xEdtStudentName = (EditText) findViewById(R.id.fedtStudentNameForRegistration);
		xEdtUsername = (EditText) findViewById(R.id.fedtUsernameForRegistration);
		xEdtPassword = (EditText) findViewById(R.id.fedtPasswordForRegistration);
		xSpnDepartment = (Spinner) findViewById(R.id.fspnDepartment);
		xSpnYear = (Spinner) findViewById(R.id.fspnYear);

		xEdtMobileNo = (EditText) findViewById(R.id.fEdtMobileNo);

		xEdtGender = (EditText) findViewById(R.id.fEdtGender);

		xEdtRollNo = (EditText) findViewById(R.id.fEdtRollNo);

		xEdtRegNo = (EditText) findViewById(R.id.fEdtRegNo);
		mCon = new DataBaseConnection(this);
		String xYear[] = { "First", "Second", "Third", "Four" };

		// Spinner Drop down elements
		List<String> xDepartmentList = new ArrayList<String>();
		xDepartmentList.add("IT");
		xDepartmentList.add("CSE");
		xDepartmentList.add("ECE");
		xDepartmentList.add("EEE");
		xDepartmentList.add("MBA");
		xDepartmentList.add("MCA");

		// Creating adapter for spinner
		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, xDepartmentList);

		// Drop down layout style - list view with radio button
		dataAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		// attaching data adapter to spinner
		xSpnDepartment.setAdapter(dataAdapter);

		// Creating adapter for spinner
		ArrayAdapter<String> dataAdapter1 = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, xYear);

		// Drop down layout style - list view with radio button
		dataAdapter1
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		// attaching data adapter to spinner
		xSpnYear.setAdapter(dataAdapter1);

	}

	public void savestudent(View v) {
		if (mCon.fn_InsertStudentRegistration(xEdtStudentName.getText()
				.toString(), xSpnDepartment.getSelectedItem().toString(),
				xSpnYear.getSelectedItem().toString(), xEdtMobileNo.getText()
						.toString(), xEdtGender.getText().toString(),
				xEdtRollNo.getText().toString(),
				xEdtRegNo.getText().toString(), xEdtUsername.getText()
						.toString(), xEdtPassword.getText().toString())) {
			Toast.makeText(getApplicationContext(), "Student Details Stored",
					1000).show();
			Intent intent = new Intent(StudentRegistration.this, HomePage.class);
			startActivity(intent);
		}
	}

	public void fn_DataClear() {
		xEdtStudentName.setText("");
		xEdtUsername.setText("");
		xEdtPassword.setText("");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}