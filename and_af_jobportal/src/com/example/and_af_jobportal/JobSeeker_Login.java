package com.example.and_af_jobportal;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class JobSeeker_Login extends Activity {
	EditText  xEdtUserName,xEdtPassword;
	private DBHelper mydb;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.f_jobseeker_login);
		mydb = new DBHelper(this);
		xEdtUserName = (EditText) findViewById(R.id.edtFullName);
		xEdtPassword = (EditText) findViewById(R.id.edtPassword);
	}

	public void btn_js_login(View v) {
		//mydb.CheckLogin(xEdtUserName.getText().toString(), xEdtPassword.getText().toString());
		mydb.CheckLogin("b", "c");
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}

