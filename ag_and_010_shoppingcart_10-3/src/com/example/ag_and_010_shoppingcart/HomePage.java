package com.example.ag_and_010_shoppingcart;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class HomePage extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.homepage);
	}

	public void vendorregistration(View v) {
		Intent intent = new Intent(HomePage.this, VendorRegistration.class);
		startActivity(intent);

	}

	public void vendorlogin(View v) {
		Intent intent = new Intent(HomePage.this, VendorLogin.class);
		startActivity(intent);

	}
	public void userlogin(View v) {
		Intent intent = new Intent(HomePage.this, UserLogin.class);
		startActivity(intent);

	}
}
