package com.example.busapp;


import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class Userdecide extends Activity {
	ImageButton hb33;
	Button bl1,bl2;
	Intent ilo1,ilo2,hi33;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_userdecide);
		bl1=(Button)findViewById(R.id.b1);
        bl2=(Button)findViewById(R.id.b2);
        hb33=(ImageButton)findViewById(R.id.bho3);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.userdecide, menu);
		return true;
	}
	
	public void from(View v)
    {
		Toast.makeText(getApplicationContext(),"Welcome",Toast.LENGTH_SHORT).show();
		ilo1=new Intent(getApplication(),Userfrom.class);
		startActivityForResult(ilo1, 0);
    }
    public void to(View v)
    {
		Toast.makeText(getApplicationContext(),"Welcome",Toast.LENGTH_SHORT).show();
		ilo2=new Intent(getApplication(),Userto.class);
		startActivityForResult(ilo2, 0);
    }
    public void gohoo(View v)
    {
		Toast.makeText(getApplicationContext(),"Welcome",Toast.LENGTH_SHORT).show();
		hi33=new Intent(getApplication(),MainActivity.class);
		startActivityForResult(hi33, 0);
    }

}
