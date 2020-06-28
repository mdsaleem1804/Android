package com.tcssnellai.ag_and_004_womenscheme;

import com.tcssnellai.ag_and_004_womenscheme.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends Activity {

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
		if(mCon.fn_GetWomenRegistration(xEdtUserName.getText().toString(), xEdtPassword.getText().toString()))
		{	
			Intent intent = new Intent(Login.this,
					ListSchemesByUser.class);
			intent.putExtra("formname", "HomePage");
			intent.putExtra("username", xEdtUserName.getText().toString());
			startActivity(intent);
		}
		else
		{
			Toast.makeText(getApplicationContext(), "Invalid Login", 1000).show();
			xEdtUserName.setText("");
			xEdtPassword.setText("");
		}
		
	}


}
