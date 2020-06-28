package com.example.afr_and_005_unlock;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class Login extends Activity {
	Button xbtnLogin;
	EditText xedtUsername,xedtPassword;
	DataBaseConnection db;
	String xUsername,xPassword,xSelectedUser;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		xbtnLogin = (Button) findViewById(R.id.btnLogin);
		xedtUsername = (EditText) findViewById(R.id.edtLUserName);
		xedtPassword = (EditText) findViewById(R.id.edtLPassword);
		xSelectedUser="";
		
		db= new DataBaseConnection(this);
		
		xbtnLogin.setOnClickListener(new OnClickListener() {

	        @SuppressLint("ShowToast")
			public void onClick(View v) {
	        	xUsername=xedtUsername.getText().toString();
	    		xPassword=xedtPassword.getText().toString();
	        	if( db.CheckUsers(xUsername, xPassword))
	        	{
	        		Toast.makeText(getApplicationContext(), "Succesfully Loggged In ", 1500).show();
	        		xSelectedUser = xUsername.toString();
	        		Intent intent = new Intent(Login.this, DoctorLoginSuccess.class);
	        		intent.putExtra("passuser",xSelectedUser);
					startActivity(intent);
		            startActivity(intent);
		           
	        	}
	        	else
	        	{
	        		
	        		Toast.makeText(getApplicationContext(), "UnAuthorized User", 1500).show();
	        		Intent intent = new Intent(Login.this, Login.class);
		            startActivity(intent);
		            finish();
		            
	        	}
	        	
	        }
	    });
	}}
