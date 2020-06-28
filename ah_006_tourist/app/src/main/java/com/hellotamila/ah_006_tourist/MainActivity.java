package com.hellotamila.ah_006_tourist;


import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		try {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_main);
		}
		catch(Exception e)
		{
			Toast.makeText(getApplicationContext(),e.toString(),Toast.LENGTH_LONG).show();
		}
		
	}
	public void user(View v) {
		Intent intent = new Intent(MainActivity.this,
				UserMainPage.class);
		//intent.putExtra("formname", "userpage");
		startActivity(intent);

	}
	public void admin(View v) {
		Intent intent = new Intent(MainActivity.this,
				AdminLoginPage.class);
		startActivity(intent);

	}

	/*@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}*/

}
