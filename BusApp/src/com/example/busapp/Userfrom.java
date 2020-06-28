package com.example.busapp;


import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class Userfrom extends Activity {
	ImageButton bl3,bl5;
	Intent ilo3,ilo5;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_userfrom);
		bl3=(ImageButton)findViewById(R.id.fms2);
		bl5=(ImageButton)findViewById(R.id.fms1);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.userfrom, menu);
		return true;
	}
	public void ss1(View v)
    {
		Toast.makeText(getApplicationContext(),"Searching the bus...",Toast.LENGTH_SHORT).show();
			
    }
	public void ss2(View v)
    {
		Toast.makeText(getApplicationContext(),"Thank you",Toast.LENGTH_SHORT).show();
		ilo5=new Intent(getApplication(),MainActivity.class);
		startActivityForResult(ilo5,0);	
    }

}
