package com.example.ag_and_010_shoppingcart;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class UserFloor extends Activity {
	String xGetUserName;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.floor);
		Bundle b = new Bundle();
	    b = getIntent().getExtras();
	     xGetUserName = b.getString("username");
	}

	public void firstfloor(View v) {

		//Intent intent = new Intent(UserFloor.this, ItemFirstFloorHome.class);
		Intent intent = new Intent(UserFloor.this, ListVendors.class);
		intent.putExtra("username", xGetUserName);
		startActivity(intent);

	}
	public void secondfloor(View v) {
		Intent intent = new Intent(UserFloor.this, ListVendors.class);
		intent.putExtra("username", xGetUserName);
		startActivity(intent);
	}
}
