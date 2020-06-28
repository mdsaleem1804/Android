package com.example.ag_and_012_school;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class StudentLogin_Attend   extends Activity {

	EditText xEdtUserName, xEdtPassword;
	DataBaseConnection mCon;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.studenlogin);
		mCon=new DataBaseConnection(this);
		xEdtUserName = (EditText) findViewById(R.id.fEdtStudentName);
		xEdtPassword = (EditText) findViewById(R.id.fEdtPassword);
	}
	
	public void studentlogin(View v) {
	/*	if ((xEdtUserName.getText().toString().equalsIgnoreCase("Saleem"))
				&& (xEdtPassword.getText().toString().equalsIgnoreCase("123"))) {
			Intent intent = new Intent(StudentLogin.this,
					MarkView.class);
			intent.putExtra("studentname", xEdtUserName.getText().toString()); 
			startActivity(intent);
		}*/
		if(mCon.fn_GetUsers(xEdtUserName.getText().toString(), xEdtPassword.getText().toString()))
		{	
			Intent intent = new Intent(StudentLogin_Attend.this,
					AttendenceView.class);
			intent.putExtra("studentusername", xEdtUserName.getText().toString()); 
			startActivity(intent);
		}
		else
		{
			Toast.makeText(getApplicationContext(), "Invalid Student", 1000).show();
		}
	}


}
