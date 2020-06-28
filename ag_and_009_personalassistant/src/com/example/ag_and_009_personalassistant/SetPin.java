package com.example.ag_and_009_personalassistant;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ag_and_009_dailystudentactivities.R;

public class SetPin extends Activity{
	EditText xEdtMobileNo,xEdtPassword;
	DataBaseConnection mCon;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register);
		xEdtMobileNo = (EditText) findViewById(R.id.fedtMobilNo);
		xEdtPassword = (EditText) findViewById(R.id.fedtPassword);
		mCon=new DataBaseConnection(this);
		
	}
	public void registration(View v)
	{
		if(mCon.Registration(xEdtMobileNo.getText().toString(),xEdtPassword.getText().toString()))
		{
			Toast.makeText(getApplicationContext(), "Registered Succesfully", 1000).show();
			fn_ClearRegistration();
			Intent intent = new Intent(SetPin.this, Login.class);
			startActivity(intent);
		}
	}
	public void fn_ClearRegistration()
	{
		xEdtMobileNo.setText("");
		xEdtPassword.setText("");	
	}

}
