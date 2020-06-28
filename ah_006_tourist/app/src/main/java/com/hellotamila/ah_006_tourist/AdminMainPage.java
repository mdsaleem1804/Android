package com.hellotamila.ah_006_tourist;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class AdminMainPage extends Activity {

	Button xBtnAddHotel,xBtnAddPlaces,xBtnBack,xBtnSetRoute,xGetBudget,xBtnAddBusServices;

	@SuppressLint("CutPasteId")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		try {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.adminmainpage);

/*
		xGetBudget.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(AdminMainPage.this,
						GetBudget.class);
				startActivity(intent);

			}
		});
		xBtnSetRoute.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(AdminMainPage.this,
						AddRoute.class);
				startActivity(intent);

			}
		});

	*/

		}
		catch(Exception e)
		{
			String xError=e.toString();
			Toast.makeText(getApplicationContext(),xError,Toast.LENGTH_LONG).show();
		}
	}
	public void online_bus(View v) {
		Intent xIntent = new Intent(getApplicationContext(),Webform.class);
		xIntent.putExtra("url", "http://hellotamila.com/barani/bus/");
		startActivity(xIntent);

	}
	public void bus(View v) {
		Intent intent = new Intent(AdminMainPage.this,
				AddBus.class);
		startActivity(intent);
	}
	public void hotel(View v) {
		Intent intent = new Intent(AdminMainPage.this,
				AddHotel.class);
		startActivity(intent);

	}
	public void places(View v) {
		Intent intent = new Intent(AdminMainPage.this,
				AddPlaces.class);
		startActivity(intent);

	}

	public void listbus(View v) {
		Intent intent = new Intent(AdminMainPage.this,
				ListBus.class);
		intent.putExtra("formname", "adminpage");
		startActivity(intent);

	}
	public void goback(View v) {
		Intent intent = new Intent(AdminMainPage.this,
				MainActivity.class);
		startActivity(intent);
		startActivity(intent);

	}

}