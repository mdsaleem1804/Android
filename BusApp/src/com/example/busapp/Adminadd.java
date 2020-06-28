package com.example.busapp;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class Adminadd extends Activity {
	ImageButton addb3;
	Button addb1,addb2;
	Intent addi1,addi2,addi3;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_adminadd);
		addb1=(Button)findViewById(R.id.abf);
	    addb2=(Button)findViewById(R.id.abt);
	    addb3=(ImageButton)findViewById(R.id.abhh);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.adminadd, menu);
		return true;
	}
	public void addfrom(View v)
	{
		Toast.makeText(getApplicationContext(),"Welcome",Toast.LENGTH_SHORT).show();
		addi1=new Intent(getApplication(),Adminaddfr.class);
		startActivityForResult(addi1, 0);
	}
	public void addto(View v)
	{
		Toast.makeText(getApplicationContext(),"Welcome",Toast.LENGTH_SHORT).show();
		addi2=new Intent(getApplication(),Adminaddto.class);
		startActivityForResult(addi2, 0);
	}
	public void goho(View v)
	{
		Toast.makeText(getApplicationContext(),"Thank you",Toast.LENGTH_SHORT).show();
		addi3=new Intent(getApplication(),MainActivity.class);
		startActivityForResult(addi3, 0);
	}


}
