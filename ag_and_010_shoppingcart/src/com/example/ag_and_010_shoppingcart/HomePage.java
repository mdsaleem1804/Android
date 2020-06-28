package com.example.ag_and_010_shoppingcart;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class HomePage extends Activity {
	int xOutDated = 0;
	String valid_until = "26/03/2017";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.homepage);

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date strDate = null;
		try {
			strDate = sdf.parse(valid_until);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		if (new Date().after(strDate)) {
			xOutDated = 1;
		}

	}

	public void vendorregistration(View v) {
		if (xOutDated == 0) {
			Intent intent = new Intent(HomePage.this, VendorRegistration.class);
			startActivity(intent);
		} else {

		}

	}

	public void vendorlogin(View v) {
		if (xOutDated == 0) {
		Intent intent = new Intent(HomePage.this, VendorLogin.class);
		startActivity(intent);
		} else {

		}
	}

	public void userlogin(View v) {

		Intent intent = new Intent(HomePage.this, UserLogin.class);
		startActivity(intent);
	}
}
