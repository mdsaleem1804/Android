package com.example.womensafetyapp;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {
	String source_ph_number,target_ph_number;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		

		// str_address = bundle.getString("address");
	/*	TelephonyManager tmgr = (TelephonyManager) BgService.this
				.getSystemService(Context.TELEPHONY_SERVICE);
		String ph_number = tmgr.getLine1Number();*/
		Intent i_startservice=new Intent(MainActivity.this,BgService.class);
		startService(i_startservice);	
	}

	
	public void register(View v) {
		Intent i_register=new Intent(MainActivity.this,Register.class);
		startActivity(i_register);
		
	}
	
public void display_no(View v) {
	Intent i_view=new Intent(MainActivity.this,Display.class);
	startActivity(i_view);
		
	}

public void instruct(View v) {	
	Intent i_help=new Intent(MainActivity.this,Instructions.class);
    startActivity(i_help);
}

public void verify(View v) {	
	Intent i_verify=new Intent(MainActivity.this,Verify.class);
    startActivity(i_verify);
}
}
