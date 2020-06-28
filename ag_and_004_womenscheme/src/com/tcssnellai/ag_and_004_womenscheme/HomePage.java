package com.tcssnellai.ag_and_004_womenscheme;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.TextView;

public class HomePage extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.homepage);
		
		
/*		TextView myText = (TextView) findViewById(R.id.textView1 );

		Animation anim = new AlphaAnimation(0.0f, 1.0f);
		anim.setDuration(500); //You can manage the blinking time with this parameter
		anim.setStartOffset(20);
		anim.setRepeatMode(Animation.REVERSE);
		anim.setRepeatCount(Animation.INFINITE);
		myText.startAnimation(anim);*/
		
	}
	public void registration (View v)
	{
		Intent intent = new Intent(HomePage.this,
				Registration.class);
		startActivity(intent);
	}
	public void login (View v)
	{
		Intent intent = new Intent(HomePage.this,
				Login.class);
		startActivity(intent);
	}
	public void adminlogin (View v)
	{
		Intent intent = new Intent(HomePage.this,
				AdminLogin.class);
		startActivity(intent);
	}
	public void generalschemes (View v)
	{
		Intent intent = new Intent(HomePage.this,
				ListSchemes.class);
		intent.putExtra("formname", "HomePage");
		startActivity(intent);
	}
	public void governmentsites (View v)
	{
		Intent intent = new Intent(HomePage.this,
				GovernmentSites.class);
		
		startActivity(intent);
	}
	
	public void newschemes(View v)
	{
		Intent intent = new Intent(HomePage.this,
				ListNewSchemes.class);
		//intent.putExtra("formname", "HomePage");
		startActivity(intent);
	}
	
}
