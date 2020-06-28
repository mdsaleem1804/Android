package com.example.ag_and_002_tourist;



import com.example.and_af_tourist.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
	}
	public void user(View v) {
		Intent intent = new Intent(MainActivity.this,
				ListBus.class);
		intent.putExtra("formname", "userpage");
		startActivity(intent);

	}
	public void admin(View v) {
		Intent intent = new Intent(MainActivity.this,
				AdminLoginPage.class);
		startActivity(intent);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
