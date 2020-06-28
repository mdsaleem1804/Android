package com.example.busapp;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class Adminlogin extends Activity {
	ImageButton bnlogin,bho1;
	Intent inlogin,iho1;
	EditText ed1,ed2;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_adminlogin);
		bnlogin = (ImageButton) findViewById(R.id.alog);
		bho1=(ImageButton)findViewById(R.id.abhh);
        ed1=(EditText)findViewById(R.id.edt1);
	    ed2=(EditText)findViewById(R.id.edt2);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.adminlogin, menu);
		return true;
	}
	public void log1(View v)
    {
		if(ed1.getText().toString().equals("admin") &&
  	          
	             ed2.getText().toString().equals("admin")) {
	             Toast.makeText(getApplicationContext(), "Successfully Login...",Toast.LENGTH_SHORT).show();
	             inlogin=new Intent(getApplication(),Adminadd.class);
	             startActivityForResult(inlogin, 0);
		}
         else{
               Toast.makeText(getApplicationContext(), "Wrong Password... Re-enter ",Toast.LENGTH_SHORT).show();
         }
    }
	public void ahh(View v)
	{
		Toast.makeText(getApplicationContext(), "Thank you...", Toast.LENGTH_SHORT).show();
		iho1=new Intent(getApplication(),MainActivity.class);
		startActivityForResult(iho1,0);
	}


}
