package com.example.ag_and_002_tourist;

import com.example.and_af_tourist.R;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AdminMainPage extends Activity {

	Button xBtnAddHotel,xBtnAddPlaces,xBtnBack,xBtnSetRoute,xGetBudget,xBtnAddBusServices;

	@SuppressLint("CutPasteId")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.adminmainpage);
		xBtnAddHotel=(Button)findViewById(R.id.btnAddHotel);
		xBtnAddPlaces=(Button)findViewById(R.id.btnAddPlaces);
		xBtnBack=(Button)findViewById(R.id.btnAdminPageBack);
		xBtnSetRoute=(Button)findViewById(R.id.btnAdminSetRoute);
		xGetBudget=(Button)findViewById(R.id.btnGetBudget);
		xBtnAddBusServices=(Button)findViewById(R.id.fbtn_addbusservices);
		
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
		
		xBtnAddHotel.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(AdminMainPage.this,
						AddHotel.class);
				startActivity(intent);

			}
		});
		
		xBtnAddPlaces.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(AdminMainPage.this,
						AddPlaces.class);
				startActivity(intent);

			}
		});

		xBtnAddBusServices.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(AdminMainPage.this,
						AddBus.class);
				startActivity(intent);

			}
		});

		xBtnBack.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				try {
					Intent intent = new Intent(AdminMainPage.this,
							MainActivity.class);
					startActivity(intent);
				} catch (Exception e) {

				}

			}
		});
	}
	public void listbus(View v) {
		Intent intent = new Intent(AdminMainPage.this,
				ListBus.class);
		intent.putExtra("formname", "adminpage");
		startActivity(intent);

	}
}