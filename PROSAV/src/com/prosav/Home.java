package com.prosav;

import com.prosav.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class Home extends Activity {
	Button xBtnDoctor, xBtnPatient;
	Intent xIntDoctorLogin, xIntPatientLogin,xIntAdminLogin;
	TextView xTxtAdminLogin;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.home);
		xBtnDoctor = (Button) findViewById(R.id.hme_btn_doctor);
		xBtnPatient = (Button) findViewById(R.id.hme_btn_patient);

		xTxtAdminLogin= (TextView) findViewById(R.id.admin_login);
		xBtnDoctor.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

				xIntDoctorLogin = new Intent(getApplicationContext(),
						Doctor_Login.class);
				startActivity(xIntDoctorLogin);

			}

		});

		xBtnPatient.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

				xIntPatientLogin = new Intent(getApplicationContext(),
						Patient_Login.class);
				startActivity(xIntPatientLogin);

			}

		});
		xTxtAdminLogin.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

				xIntAdminLogin = new Intent(getApplicationContext(),
						Admin_Login.class);
				startActivity(xIntAdminLogin);

			}

		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
