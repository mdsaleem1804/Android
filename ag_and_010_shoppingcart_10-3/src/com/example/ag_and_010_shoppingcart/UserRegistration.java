package com.example.ag_and_010_shoppingcart;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class UserRegistration extends Activity {
	DataBaseConnection mCon;
	int xMessgeDisplayTime = 5000;
	EditText xEdtName, xEdtAddress, xEdtCity, xEdtState, xEdtPinCode,
			xEdtMobile, xEdtUserName, xEdtPassword;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.userregistration);

	}



	public void saveuser(View v) {
		xEdtName = (EditText) findViewById(R.id.fedtName);
		xEdtAddress = (EditText) findViewById(R.id.fEdtAddress);
		xEdtCity = (EditText) findViewById(R.id.fEdtCity);
		xEdtState = (EditText) findViewById(R.id.fEdtState);
		xEdtPinCode = (EditText) findViewById(R.id.fEdtPinCode);
		xEdtMobile = (EditText) findViewById(R.id.fEdtMobileNo);
		xEdtUserName = (EditText) findViewById(R.id.fEdtUserNameSignup);
		xEdtPassword = (EditText) findViewById(R.id.fEdtPasswordSignup);

		mCon = new DataBaseConnection(this);
		mCon.fn_InsertUserDetails(xEdtName.getText().toString(), xEdtAddress
				.getText().toString(), xEdtCity.getText().toString(), xEdtState
				.getText().toString(), xEdtPinCode.getText().toString(),
				xEdtMobile.getText().toString(), xEdtUserName.getText()
						.toString(), xEdtPassword.getText().toString());

		//DataClear();
		Toast.makeText(getApplicationContext(),
				"User Succesfully Registered", xMessgeDisplayTime).show();
		Intent intent = new Intent(UserRegistration.this, UserLogin.class);
		//startActivity(intent);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void DataClear() {
		xEdtAddress.setText("");
		xEdtCity.setText("");
		xEdtName.setText("");
		xEdtState.setText("");
		xEdtUserName.setText("");
		xEdtPassword.setText("");
	}
}
