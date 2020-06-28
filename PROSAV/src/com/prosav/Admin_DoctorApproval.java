package com.prosav;

import com.prosav.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;

public class Admin_DoctorApproval extends Activity {
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.f_admin_login_success);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}
