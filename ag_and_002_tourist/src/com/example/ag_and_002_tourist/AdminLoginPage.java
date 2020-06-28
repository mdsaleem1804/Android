package com.example.ag_and_002_tourist;

import com.example.and_af_tourist.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class AdminLoginPage extends Activity {
	private TextView xedtUserName, xedtPassword;
	Button xBtnLogin;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.admin_login);
		xedtUserName = (TextView) findViewById(R.id.edtUserName);
		xedtPassword = (TextView) findViewById(R.id.edtPassword);
		xBtnLogin = (Button) findViewById(R.id.btnAdminLogin);
		xBtnLogin.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if ((xedtUserName.getText().toString().equalsIgnoreCase("admin")) && (xedtPassword .getText().toString().equalsIgnoreCase("admin"))) {
					finish();
					Intent intent = new Intent(AdminLoginPage.this,
							AdminMainPage.class);
					startActivity(intent);
				}
				else
				{
					xedtUserName.setText("");
					xedtPassword.setText("");
					Toast.makeText(getApplicationContext(), "Invalid User",
							Toast.LENGTH_LONG).show();
				}

			}
		});

	}
}