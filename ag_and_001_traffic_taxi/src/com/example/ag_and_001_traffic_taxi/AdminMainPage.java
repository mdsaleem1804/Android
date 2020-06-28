package com.example.ag_and_001_traffic_taxi;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class AdminMainPage extends Activity {

	Spinner xSpnSources, xSpnDestination, xSpnDriver;

	String xSelectedSource = "", xSelectedDestination = "", xSelectedDriver;
	String[] xSource = { "Tirunelveli", "Tuticorin", "Madurai", "Kovilpatti",
			"Chennai", "Nagercoil" };
	String[] xDestination = { "Kanyakumari" };

	DataBaseConnection mCon;
	private SQLiteDatabase db = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.adminmainpage);
		xSpnSources = (Spinner) findViewById(R.id.fspnSource);
		xSpnDestination = (Spinner) findViewById(R.id.fspnDestination);
		xSpnDriver = (Spinner) findViewById(R.id.fspnDriver);
		mCon = new DataBaseConnection(this);
		db = mCon.getWritableDatabase();
		LoadSource();
		LoadDestination();
		LoadDrivers();
	}

	public void listdrivers(View v) {
		Intent intent = new Intent(AdminMainPage.this, ListDrivers.class);
		startActivity(intent);

	}

	public void setdriver(View v) {
		try {

			ContentValues contentValues = new ContentValues();
			contentValues.put("source", xSpnSources.getSelectedItem().toString());
			contentValues.put("destination", xSpnDestination.getSelectedItem().toString());
			// contentValues.put("latitude", "77.8");
			// contentValues.put("longitude", "8.7");
			db.update("driverregistration", contentValues, "username='"
					+ xSpnDriver.getSelectedItem().toString() + "'", null);
			Toast.makeText(getApplicationContext(), "Updated", 1000).show();
		} catch (Exception e) {
			e.toString();
		}

	}

	protected void openDatabase() {
		db = openOrCreateDatabase("ag_and_001_trafficandtaxi",
				Context.MODE_PRIVATE, null);
	}

	
	

	public void LoadSource() {
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, xSource);
		xSpnSources.setAdapter(adapter);
		xSpnSources
				.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
					@Override
					public void onItemSelected(AdapterView<?> arg0, View arg1,
							int arg2, long arg3) {
						int position = xSpnSources.getSelectedItemPosition();
						Toast.makeText(getApplicationContext(),
								"You have selected " + xSource[+position],
								Toast.LENGTH_LONG).show();
						xSelectedDestination = xSpnSources.getSelectedItem()
								.toString();
						// TODO Auto-generated method stub
					}

					@Override
					public void onNothingSelected(AdapterView<?> arg0) {
						// TODO Auto-generated method stub
					}
				});
	}

	public void LoadDestination() {
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, xDestination);
		xSpnDestination.setAdapter(adapter);
		xSpnDestination
				.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
					@Override
					public void onItemSelected(AdapterView<?> arg0, View arg1,
							int arg2, long arg3) {
						int position = xSpnDestination
								.getSelectedItemPosition();
						Toast.makeText(getApplicationContext(),
								"You have selected " + xDestination[+position],
								Toast.LENGTH_LONG).show();
						xSelectedDestination = xSpnDestination
								.getSelectedItem().toString();
						// TODO Auto-generated method stub
					}

					@Override
					public void onNothingSelected(AdapterView<?> arg0) {
						// TODO Auto-generated method stub
					}
				});
	}

	public void LoadDrivers() {
		ArrayList<String> my_array = new ArrayList<String>();
		my_array = getDriver();

		@SuppressWarnings("rawtypes")
		ArrayAdapter my_Adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, my_array);
		xSpnDriver.setAdapter(my_Adapter);
		xSpnDriver.setOnItemSelectedListener(new OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				xSelectedDriver = xSpnDriver.getSelectedItem().toString();

			}

			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub

			}

		});

	}

	@SuppressLint("ShowToast")
	public ArrayList<String> getDriver() {

		ArrayList<String> my_array = new ArrayList<String>();
		try {

			Cursor allrows = db.rawQuery("SELECT * FROM driverregistration",
					null);
			System.out.println("COUNT : " + allrows.getCount());

			if (allrows.moveToFirst()) {
				do {

					String NAME = allrows.getString(5);
					my_array.add(NAME);

				} while (allrows.moveToNext());
			}
			allrows.close();
			
		} catch (Exception e) {
			Toast.makeText(getApplicationContext(), "Error encountered.",
					Toast.LENGTH_LONG);
		}
		return my_array;
	}

}