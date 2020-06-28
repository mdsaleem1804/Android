package com.hellotamila.ah_006_tourist;

import java.util.ArrayList;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

public class AddRoute extends Activity {
	EditText xEdtSetPAth,xEdtSetPAth2;
	Button xAddRoutePath,xGoBack;
	DataBaseConnection mCon;
	Spinner xspnChPlace, xspnChDistrict;
	private SQLiteDatabase db = null;
	String xSelectedPlace = "",xSelectedDistrict="";
	String[] Districts = { "Tirunelveli", "Tuticorin", "Madurai", "Kovilpatti",
			"Chennai", "Nagercoil", };

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (android.os.Build.VERSION.SDK_INT > 9) {
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
					.permitAll().build();
			StrictMode.setThreadPolicy(policy);
		}
		mCon = new DataBaseConnection(this);
		db = mCon.getWritableDatabase();
		setContentView(R.layout.addroute);
		xEdtSetPAth = (EditText) findViewById(R.id.edtSetPAth);
		xEdtSetPAth2 = (EditText) findViewById(R.id.editSetPAth2);
		xspnChPlace = (Spinner) findViewById(R.id.spnChoosePlace);
		xspnChDistrict = (Spinner) findViewById(R.id.spnChooseDistrict);
		xAddRoutePath = (Button) findViewById(R.id.btnRouteOk);
		xGoBack = (Button) findViewById(R.id.btnRouteBack);
		

		

		LoadDistricts();
		LoadPlaces();

		xAddRoutePath.setOnClickListener(new View.OnClickListener() {

			@SuppressLint("ShowToast")
			@Override
			public void onClick(View v) {
				try {

					mCon.insertPath(xSelectedDistrict, xSelectedPlace, xEdtSetPAth
							.getText().toString(), xEdtSetPAth2
							.getText().toString());

					Toast.makeText(getApplicationContext(), "Route Assigned From " + xSelectedDistrict + "to" +xSelectedPlace ,
							Toast.LENGTH_SHORT).show();
					DataClear();
				} catch (Exception e) {

				}

			}
		});
		
		xGoBack.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				try {
					Intent intent = new Intent(AddRoute.this,
							AdminMainPage.class);
					startActivity(intent);
				} catch (Exception e) {

				}

			}
		});

	}

	public void LoadDistricts() {
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, Districts);
		xspnChDistrict.setAdapter(adapter);
		xspnChDistrict
				.setOnItemSelectedListener(new OnItemSelectedListener() {
					@Override
					public void onItemSelected(AdapterView<?> arg0, View arg1,
							int arg2, long arg3) {
						int position = xspnChDistrict.getSelectedItemPosition();
						Toast.makeText(getApplicationContext(),
								"You have selected " + Districts[+position],
								Toast.LENGTH_LONG).show();
						xSelectedDistrict= xspnChDistrict.getSelectedItem().toString();
						// TODO Auto-generated method stub
					}

					@Override
					public void onNothingSelected(AdapterView<?> arg0) {
						// TODO Auto-generated method stub
					}
				});
	}

	public void LoadPlaces() {
		ArrayList<String> my_array = new ArrayList<String>();
		my_array = getPlaces();

		xspnChPlace = (Spinner) findViewById(R.id.spnChoosePlace);
		@SuppressWarnings("rawtypes")
		ArrayAdapter my_Adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, my_array);
		xspnChPlace.setAdapter(my_Adapter);

		xspnChPlace
				.setOnItemSelectedListener(new OnItemSelectedListener() {
					public void onItemSelected(AdapterView<?> parent,
							View view, int position, long id) {
						xSelectedPlace = xspnChPlace.getSelectedItem()
								.toString();

					}

					public void onNothingSelected(AdapterView<?> arg0) {
						// TODO Auto-generated method stub

					}

				});

	}

	@SuppressLint("ShowToast")
	public ArrayList<String> getPlaces() {

		ArrayList<String> my_array = new ArrayList<String>();
		try {

			Cursor allrows = db.rawQuery("SELECT * FROM places", null);
			System.out.println("COUNT : " + allrows.getCount());

			if (allrows.moveToFirst()) {
				do {

					String NAME = allrows.getString(0);
					my_array.add(NAME);

				} while (allrows.moveToNext());
			}
			allrows.close();
			db.close();
		} catch (Exception e) {
			Toast.makeText(getApplicationContext(), "Error encountered.",
					Toast.LENGTH_LONG);
		}
		return my_array;
	}

	public void DataClear() {
		xEdtSetPAth.setText("");
		xEdtSetPAth2.setText("");

	}

}