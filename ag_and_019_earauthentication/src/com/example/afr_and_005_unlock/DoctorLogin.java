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


public class DoctorLogin extends Activity {
	Button xbtnLogin;
	EditText xedtDUsername,xedtDPassword;
	DataBaseConnection db;
	String xUsername,xPassword;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.adminlogin);
		xbtnLogin = (Button) findViewById(R.id.btnalogin );
		xedtDUsername = (EditText) findViewById(R.id.edtausername );
		xedtDPassword = (EditText) findViewById(R.id.edtapassword );
		
		db= new DataBaseConnection(this);
		
		xbtnLogin.setOnClickListener(new OnClickListener() {

	        @SuppressLint("ShowToast")
			public void onClick(View v) {
	    		xUsername=xedtDUsername.getText().toString();
	    		xPassword=xedtDPassword.getText().toString();
	        	if(xUsername.equals("admin")&& xPassword .equals("admin"))
	        	{	Toast.makeText(getApplicationContext(), "Succesfully Loggged In ", 1500).show();
	        		Intent intent = new Intent(DoctorLogin.this, DoctorLoginSuccess.class);
		            startActivity(intent);
		           
        	}
	        	else
        	{
	        		
        		Toast.makeText(getApplicationContext(), "UnAuthorized User", 1500).show();
	        		Intent intent = new Intent(DoctorLogin.this, DoctorLogin.class);
		            startActivity(intent);
		            finish();
		            
	        	}
	        	
	        }
	    });
	}}
