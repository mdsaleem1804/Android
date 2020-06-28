package com.hellotamila.ah_006_tourist;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.StrictMode;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

public class AddPlaces extends Activity {
	EditText xedtPlaceName,  xedtDetails, xedtFavourites;
	Button xAddPlacesData,xClearData,xGoBack;
	DataBaseConnection mCon;
	Spinner xspnNearestHotel, xspnDistrict;
	private SQLiteDatabase db = null;
	String xSelectedHotel = "",xSelectedDistrict="";
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
		setContentView(R.layout.addplaces);
		xedtPlaceName = (EditText) findViewById(R.id.edtPlaceName);
		xedtDetails = (EditText) findViewById(R.id.edtDetails);
		xedtFavourites = (EditText) findViewById(R.id.edtFavourite);
		xspnNearestHotel = (Spinner) findViewById(R.id.spnHotel);
		xspnDistrict = (Spinner) findViewById(R.id.spnDistrict);
		xAddPlacesData = (Button) findViewById(R.id.btnAddPlaces);
	    xClearData=  (Button) findViewById(R.id.btnPlacesClear);
		xGoBack = (Button) findViewById(R.id.btnPlacesBack);

		

		LoadDistricts();
		LoadHotels();

		xAddPlacesData.setOnClickListener(new View.OnClickListener() {

			@SuppressLint("ShowToast")
			@Override
			public void onClick(View v) {
				try {
					if(TextUtils.isEmpty(xedtPlaceName.getText().toString())) {
						xedtPlaceName.setError("Enter Place  Name");
						return;
					}
					mCon.insertPlaces(xedtPlaceName.getText().toString(),
							xSelectedDistrict, xedtDetails
									.getText().toString(), xedtFavourites
									.getText().toString(), xSelectedHotel);

					Toast.makeText(getApplicationContext(), "Places Added",
							Toast.LENGTH_SHORT).show();
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
					Intent intent = new Intent(AddPlaces.this,
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
		xspnDistrict.setAdapter(adapter);
		xspnDistrict
				.setOnItemSelectedListener(new OnItemSelectedListener() {
					@Override
					public void onItemSelected(AdapterView<?> arg0, View arg1,
							int arg2, long arg3) {
						int position = xspnDistrict.getSelectedItemPosition();
						Toast.makeText(getApplicationContext(),
								"You have selected " + Districts[+position],
								Toast.LENGTH_LONG).show();
						xSelectedDistrict= xspnDistrict.getSelectedItem().toString();
						// TODO Auto-generated method stub
					}

					@Override
					public void onNothingSelected(AdapterView<?> arg0) {
						// TODO Auto-generated method stub
					}
				});
	}

	public void LoadHotels() {
		ArrayList<String> my_array = new ArrayList<String>();
		my_array = getHotelNames();

		xspnNearestHotel = (Spinner) findViewById(R.id.spnHotel);
		@SuppressWarnings("rawtypes")
		ArrayAdapter my_Adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, my_array);
		xspnNearestHotel.setAdapter(my_Adapter);

		xspnNearestHotel
				.setOnItemSelectedListener(new OnItemSelectedListener() {
					public void onItemSelected(AdapterView<?> parent,
							View view, int position, long id) {
						xSelectedHotel = xspnNearestHotel.getSelectedItem()
								.toString();

					}

					public void onNothingSelected(AdapterView<?> arg0) {
						// TODO Auto-generated method stub

					}

				});

	}

	@SuppressLint("ShowToast")
	public ArrayList<String> getHotelNames() {

		ArrayList<String> my_array = new ArrayList<String>();
		try {

			Cursor allrows = db.rawQuery("SELECT * FROM hotel", null);
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
		xedtPlaceName.setText("");
		//xedtDistrictName.setText("");
		xedtDetails.setText("");
		xedtFavourites.setText("");

		// xedtNearestHotel.setText("");

	}

}