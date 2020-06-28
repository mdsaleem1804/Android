package com.example.ag_and_015_handwaeving;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class NameCreation extends Activity{
	DataBaseConnection mCon;
	EditText xEdtName,xEdtPassword;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.namecreation);
		mCon=new DataBaseConnection(this);
		xEdtName=(EditText)findViewById(R.id.fedtName);
		xEdtPassword=(EditText)findViewById(R.id.fedtPassword);
		
	}
public void generatepassword(View v)
{
	if(mCon.fn_PasswordCreation(xEdtName.getText().toString(),xEdtPassword.getText().toString(),"0"))
	{
		Toast.makeText(getApplicationContext(),"Data Stored", 1000).show();
		fn_ClearNmeCreatoinValues();
	}
	else
	{
		Toast.makeText(getApplicationContext(),"Error", 1000).show();
	}
	
}
public void fn_ClearNmeCreatoinValues()
{
	xEdtName.setText("");
	xEdtPassword.setText("");
}
}
