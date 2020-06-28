package com.example.ag_and_015_handwaeving;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends Activity {
	DataBaseConnection mCon;
	EditText xEdtUserName, xEdtPassword;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		mCon=new DataBaseConnection(this);
		xEdtUserName = (EditText) findViewById(R.id.fedtusername);
		xEdtPassword = (EditText) findViewById(R.id.fedtpasssword);
	}
	
	public void login(View v) {
		if(mCon.fn_LoginValidation(xEdtUserName.getText().toString(), xEdtPassword.getText().toString()))
		{	finish();
			Intent intent = new Intent(Login.this,
					MainPage.class);
			startActivity(intent);
		}
		else
		{
			Toast.makeText(getApplicationContext(), "Check your Credentials", 1000).show();
		}
	}


}
