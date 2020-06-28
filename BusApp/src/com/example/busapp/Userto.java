package com.example.busapp;


import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class Userto extends Activity {
	ImageButton bl4,bl6;
	Intent ilo4,ilo6;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_userto);
		bl4=(ImageButton)findViewById(R.id.bj1);
		bl6=(ImageButton)findViewById(R.id.bj2);
	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.userto, menu);
		return true;
	}
	public void toser(View v)
    {
		Toast.makeText(getApplicationContext(),"Searching the bus...",Toast.LENGTH_SHORT).show();
			
    }
	public void togoho(View v)
    {
		Toast.makeText(getApplicationContext(),"Thank you",Toast.LENGTH_SHORT).show();
		ilo6=new Intent(getApplication(),MainActivity.class);
		startActivityForResult(ilo6,0);	
    }

}
