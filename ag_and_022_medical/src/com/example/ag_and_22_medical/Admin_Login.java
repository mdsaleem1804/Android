package com.example.ag_and_22_medical;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.text.TextUtils;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

@TargetApi(Build.VERSION_CODES.GINGERBREAD)
public class Admin_Login extends Activity {

	EditText xEdtALUserName, xEdtALPassword;
	Intent xIntAdminLoginSuccess;

	Button xLogin;

	@SuppressLint("NewApi")
	@TargetApi(Build.VERSION_CODES.GINGERBREAD)
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.f_admin_login);
		xLogin = (Button) findViewById(R.id.alog_btn_login);
		xEdtALUserName = (EditText) findViewById(R.id.alog_edt_username);
		xEdtALPassword = (EditText) findViewById(R.id.alog_edt_password);
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
				.permitAll().build();

		StrictMode.setThreadPolicy(policy);

		xLogin.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				if (TextUtils.isEmpty(xEdtALUserName.getText().toString())) {
					xEdtALUserName.setError("User Name Required");
					return;
				}
				if (TextUtils.isEmpty(xEdtALPassword.getText().toString())) {
					xEdtALPassword.setError("Password Required");
					return;
				}
				if ((xEdtALUserName.getText().toString()
						.equalsIgnoreCase("1"))
						&& (xEdtALPassword.getText().toString()
								.equalsIgnoreCase("1"))) {
					xIntAdminLoginSuccess = new Intent(getApplicationContext(),
							HomePage.class);
					startActivity(xIntAdminLoginSuccess);

				}
				else
				{
					Toast.makeText(getApplicationContext(), "Invalid UserName Or Password",
							Toast.LENGTH_LONG).show();
				}

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
