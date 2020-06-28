package com.hellotamila.ah_006_tourist;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddHotel extends Activity {
	EditText xHotelName, xSBed, xDBed, XFacilities, xOthers;
	Button xAddHotelData,xClearData,xGoBack;
	DataBaseConnection mCon;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (android.os.Build.VERSION.SDK_INT > 9) {
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
					.permitAll().build();
			StrictMode.setThreadPolicy(policy);
		}
		mCon = new DataBaseConnection(this);
		setContentView(R.layout.addhotel);
		xHotelName = (EditText) findViewById(R.id.edtHotelName);
		xSBed = (EditText) findViewById(R.id.edtSingleBed);
		xDBed = (EditText) findViewById(R.id.edtDoubleBed);
		XFacilities = (EditText) findViewById(R.id.edtFacilities);
		xOthers = (EditText) findViewById(R.id.edtOthers);
		xAddHotelData = (Button) findViewById(R.id.btnAddHotelData);
	    xClearData=  (Button) findViewById(R.id.btnClear);
		xGoBack = (Button) findViewById(R.id.btnback);

		xAddHotelData.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				try {
					if(TextUtils.isEmpty(xHotelName.getText().toString())) {
						xHotelName.setError("Enter Hotel Name");
						return;
					}
					mCon.insertHotel(xHotelName.getText().toString(), Integer
							.parseInt(xSBed.getText().toString()), Integer
							.parseInt(xDBed.getText().toString()), XFacilities
							.getText().toString(), xOthers.getText().toString());
					Toast.makeText(getApplicationContext(),
							"Hotel Details Added", Toast.LENGTH_SHORT).show();
					DataClear();
				} catch (Exception e) {

				}

			}
		});
		xClearData.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				try {

					DataClear();
				} catch (Exception e) {

				}

			}
		});
		xGoBack.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				try {
					Intent intent = new Intent(AddHotel.this,
							AdminMainPage.class);
					startActivity(intent);
				} catch (Exception e) {

				}

			}
		});
	}

	public void DataClear() {
		xHotelName.setText("");
		xSBed.setText("");
		xDBed.setText("");
		XFacilities.setText("");
		xOthers.setText("");

	}

}