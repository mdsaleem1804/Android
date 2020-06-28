package com.example.ag_and_22_medical;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class PatientRegistration extends Activity {
	DataBaseConnection mCon;
	EditText xEdtPName, xEdtPAge, xEdtAddress, xEdtMobileNo, xEdtDiagnosis,
			xEdtECoNo, xEdtECAdd;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.patientregistration);
		mCon = new DataBaseConnection(this);
		xEdtPName = (EditText) findViewById(R.id.fEdtPatientName);
		xEdtPAge = (EditText) findViewById(R.id.fEdtAge);
		xEdtAddress = (EditText) findViewById(R.id.fEdtMobileNo);
		xEdtMobileNo = (EditText) findViewById(R.id.fEdtAddress);
		xEdtDiagnosis = (EditText) findViewById(R.id.fEdtDiagnosis);
		xEdtECoNo = (EditText) findViewById(R.id.fEdtEmergencyContactNo);
		xEdtECAdd = (EditText) findViewById(R.id.fEdtEmergencyContactAddress);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void patientregistration(View v) {
		if (mCon.fn_PatientRegistration(xEdtPName.getText().toString(),
				xEdtPAge.getText().toString(),
				xEdtAddress.getText().toString(), xEdtMobileNo.getText()
						.toString(), xEdtDiagnosis.getText().toString(),
				xEdtECoNo.getText().toString(), xEdtECAdd.getText().toString())) {

			Toast.makeText(getApplicationContext(), "Inserted", 1000).show();
		}
	}
}
