package com.prosav;

import com.prosav.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class AdminLoginSuccess extends Activity {
	Button xBtnDoctorList, xBtnPatientList, xBtnPatientComments;
	Intent xIntDoctorList, xIntPatientList, xIntPatientComments;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.f_admin_login_success);

		xBtnDoctorList = (Button) findViewById(R.id.btn_admin_viewdoctors);
		xBtnPatientList = (Button) findViewById(R.id.btn_admin_viewpatients);
		xBtnPatientComments = (Button) findViewById(R.id.btn_admin_patcomments);

	xBtnDoctorList.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

				xIntDoctorList = new Intent(getApplicationContext(),
						Doctor_List.class);
				startActivity(xIntDoctorList);

			}

		});

		xBtnPatientList.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

				xIntPatientList = new Intent(getApplicationContext(),
						Patient_List.class);
				startActivity(xIntPatientList);

			}

		});
		xBtnPatientComments.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

				xIntPatientComments = new Intent(getApplicationContext(),
						Patent_FeedbackList.class);
				startActivity(xIntPatientComments);

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