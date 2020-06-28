package com.hellotamila.ah_and_013_roadsense;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class DriverLogin extends Activity {

	EditText xEdtUserName, xEdtPassword;
	DataBaseConnection mCon;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		mCon=new DataBaseConnection(this);
		xEdtUserName = (EditText) findViewById(R.id.fedtusername);
		xEdtPassword = (EditText) findViewById(R.id.fedtpasssword);
	}
	
	public void login(View v) {

		if(mCon.fn_GetDriverLogin(xEdtUserName.getText().toString(), xEdtPassword.getText().toString()))
		{
			Intent intent = new Intent(DriverLogin.this,
					DriverMainPage.class);
			intent.putExtra("drivername", xEdtUserName.getText().toString());
			startActivity(intent);
		}
		else
		{
			Toast.makeText(getApplicationContext(), "Invalid Driver", 1000).show();
		}
	}


}
