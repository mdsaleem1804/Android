package com.example.ag_and_017_banking;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AdminLogin extends Activity {

	EditText xEdtUserName, xEdtPassword;
	DataBaseConnection mCon;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		mCon = new DataBaseConnection(this);
		xEdtUserName = (EditText) findViewById(R.id.fedtusername);
		xEdtPassword = (EditText) findViewById(R.id.fedtpasssword);
	}

	public void login(View v) {
		if ((xEdtUserName.getText().toString().equalsIgnoreCase("admin"))
				&& (xEdtPassword.getText().toString().equalsIgnoreCase("admin"))) {
			Intent intent = new Intent(AdminLogin.this, AdminMainPage.class);
			startActivity(intent);

		} else {

			Toast.makeText(getApplicationContext(), "Invalid", 1000).show();

		}
	}

}
