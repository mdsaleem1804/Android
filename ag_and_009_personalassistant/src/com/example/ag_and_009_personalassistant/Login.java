package com.example.ag_and_009_personalassistant;

import com.example.ag_and_009_dailystudentactivities.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends Activity {
	EditText xEdtLoginPhone;
	DataBaseConnection mCon;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		mCon = new DataBaseConnection(this);
		xEdtLoginPhone = (EditText) findViewById(R.id.fedt_loginphoneno);
	}

	public void login(View v) {
		if (mCon.fn_GetMobileNo(xEdtLoginPhone.getText().toString())) {
			Toast.makeText(getApplicationContext(), "Logged In", 1000).show();
			Intent intent = new Intent(Login.this, NavigationDrawer.class);
			startActivity(intent);
		}
		else
		{
			Toast.makeText(getApplicationContext(), "Invalid No", 1000).show();
		}
	}
}
