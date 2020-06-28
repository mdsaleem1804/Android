package com.example.ag_and_22_medical;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

public class HomePage extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.homepage);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void patientregistration(View v) {
		Intent xIntent = new Intent(HomePage.this, PatientRegistration.class);
		startActivity(xIntent);

	}

	public void ipadmission(View v) {
		Intent xIntent = new Intent(HomePage.this, IpAdmission.class);
		startActivity(xIntent);
	}

	public void advance(View v) {
		Intent xIntent = new Intent(HomePage.this, Advance.class);
		startActivity(xIntent);
	}

	public void listpatients(View v) {
		Intent xIntent = new Intent(HomePage.this, ListPatients.class);
		startActivity(xIntent);
	}
	public void listipadmission(View v) {
		Intent xIntent = new Intent(HomePage.this, ListIpAdmission.class);
		startActivity(xIntent);
	}
	public void listadvance(View v) {
		Intent xIntent = new Intent(HomePage.this, ListAdvance.class);
		startActivity(xIntent);
	}
}
