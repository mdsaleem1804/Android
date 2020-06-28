package com.example.and_af_qrcode;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends Activity {
	private TextView xedtUserName, xedtPassword;
	Button xBtnLogin;
	Intent xIntentLoginSuccess;
	DBhelper mydb;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		mydb = new DBhelper(this);
		xedtUserName = (TextView) findViewById(R.id.edtUserName);
		xedtPassword = (TextView) findViewById(R.id.edtPassword);
		xBtnLogin = (Button) findViewById(R.id.btnAdminLogin);

		xBtnLogin.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				if (mydb.CheckUsers(xedtUserName.getText().toString(),
						xedtPassword.getText().toString())) {
					Toast.makeText(getApplicationContext(),
							"Succesfully Loggged In ", 1500).show();
					xIntentLoginSuccess = new Intent(Login.this, LoginSucess.class);
					startActivity(xIntentLoginSuccess);

				} else {
					Toast.makeText(getApplicationContext(),
							"UnAuthorized User", 1500).show();
					Intent intent = new Intent(Login.this, Login.class);
					startActivity(intent);
					finish();
				}

			}

		});

	}
}