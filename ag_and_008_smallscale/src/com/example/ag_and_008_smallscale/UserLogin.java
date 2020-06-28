package com.example.ag_and_008_smallscale;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class UserLogin extends Activity {
	DataBaseConnection mCon;
	EditText xEdtUserName, xEdtPassword;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.userlogin);
		mCon = new DataBaseConnection(this);
		xEdtUserName = (EditText) findViewById(R.id.fEdtUserLogUser);
		xEdtPassword = (EditText) findViewById(R.id.fEdtUserLogPassword);
	}

	public void userlogin(View v) {
		if (mCon.fn_GetUsers(xEdtUserName.getText().toString(), xEdtPassword
				.getText().toString())) {
			Intent intent = new Intent(UserLogin.this, ListVendors.class);
			/*
			 * Intent intent = new Intent(UserLogin.this, UserMainPage.class);
			 */
			intent.putExtra("username", xEdtUserName.getText().toString());
			startActivity(intent);
			Toast.makeText(getApplicationContext(),
					"User Succesfully Logged In", 1000).show();
		} else {
			Toast.makeText(getApplicationContext(), "Invalid User", 1000)
					.show();
		}
	}

	public void userregistration(View v) {
		Intent intent = new Intent(UserLogin.this, UserRegistration.class);
		startActivity(intent);

	}

}
