package com.example.ag_and_008_smallscale;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.telephony.gsm.SmsManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class GpsSettings extends Activity {
	EditText xEdtLatitude, xEdtLongtitude;
	DrawerLayout mDrawerLayout;
	ListView mDrawerList;
	ActionBarDrawerToggle mDrawerToggle;
	String mTitle = "";

	// Button xGetLocation;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.gpssettings);
		xEdtLatitude = (EditText) findViewById(R.id.fedtLatitude);
		xEdtLongtitude = (EditText) findViewById(R.id.fedtLongtitude);
		// xGetLocation=(Button) findViewById(R.id.fbtnGetLocation);
		fn_CollectGpsValues();

		// LoadDrawer();
	}

	public void sendSMS(String phoneNo, String msg) {
		try {
			SmsManager smsManager = SmsManager.getDefault();
			smsManager.sendTextMessage(phoneNo, null, msg, null, null);
			Toast.makeText(getApplicationContext(), "Message Sent",
					Toast.LENGTH_LONG).show();
		} catch (Exception ex) {
			Toast.makeText(getApplicationContext(), ex.getMessage().toString(),
					Toast.LENGTH_LONG).show();
			ex.printStackTrace();
		}
	}

	public void getlocation(View v) {
		fn_CollectGpsValues();
	}

	public void go(View v) {
		if ((xEdtLatitude.getText().toString().equalsIgnoreCase("9.1479"))
				&& (xEdtLongtitude.getText().toString()
						.equalsIgnoreCase("77.839"))) {
			Intent intent = new Intent(GpsSettings.this, HomePage.class);
			startActivity(intent);
		}
		if ((xEdtLatitude.getText().toString().equalsIgnoreCase("9.1819"))
				&& (xEdtLongtitude.getText().toString()
						.equalsIgnoreCase("77.8531"))) {
			Intent intent = new Intent(GpsSettings.this, HomePage.class);
			startActivity(intent);
		}

		if ((xEdtLatitude.getText().toString().equalsIgnoreCase("8.8103"))
				&& (xEdtLongtitude.getText().toString()
						.equalsIgnoreCase("78.1376"))) {
			Intent intent = new Intent(GpsSettings.this, HomePage.class);
			startActivity(intent);
		} else {
			Toast.makeText(getApplicationContext(), "Invalid Value", 1000)
					.show();
		}

	}

	public void LoadDrawer() {

		mTitle = (String) getTitle();

		// Getting reference to the DrawerLayout
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

		mDrawerList = (ListView) findViewById(R.id.drawer_list);

		// Getting reference to the ActionBarDrawerToggle
		mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
				R.drawable.ic_drawer, R.string.drawer_open,
				R.string.drawer_close) {

			/** Called when drawer is closed */
			public void onDrawerClosed(View view) {
				getActionBar().setTitle(mTitle);
				invalidateOptionsMenu();

			}

			/** Called when a drawer is opened */
			public void onDrawerOpened(View drawerView) {
				getActionBar().setTitle("Select a Menu");
				invalidateOptionsMenu();
			}

		};

		// Setting DrawerToggle on DrawerLayout
		mDrawerLayout.setDrawerListener(mDrawerToggle);

		// Creating an ArrayAdapter to add items to the listview mDrawerList
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(
				getBaseContext(), R.layout.drawer_list_item, getResources()
						.getStringArray(R.array.leftmenu));

		// Setting the adapter on mDrawerList
		mDrawerList.setAdapter(adapter);

		// Enabling Home button
		getActionBar().setHomeButtonEnabled(true);

		// Enabling Up navigation
		getActionBar().setDisplayHomeAsUpEnabled(true);

		// Setting item click listener for the listview mDrawerList
		mDrawerList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				/*
				 * if(position==0) { mDrawerLayout.closeDrawer(mDrawerList);
				 * Intent intent = new Intent(MainActivity.this,
				 * GpsSettings.class); startActivity(intent);
				 * 
				 * 
				 * }
				 */
				// Getting an array of rivers
				String[] rivers = getResources().getStringArray(
						R.array.leftmenu);

				// Currently selected river
				mTitle = rivers[position];

				/*
				 * // Creating a fragment object RiverFragment rFragment = new
				 * RiverFragment();
				 * 
				 * // Creating a Bundle object Bundle data = new Bundle();
				 * 
				 * // Setting the index of the currently selected item of
				 * mDrawerList data.putInt("position", position);
				 * 
				 * // Setting the position to the fragment
				 * rFragment.setArguments(data);
				 * 
				 * // Getting reference to the FragmentManager FragmentManager
				 * fragmentManager = getFragmentManager();
				 * 
				 * // Creating a fragment transaction FragmentTransaction ft =
				 * fragmentManager.beginTransaction();
				 * 
				 * // Adding a fragment to the fragment transaction
				 * ft.replace(R.id.content_frame, rFragment);
				 * 
				 * // Committing the transaction ft.commit();
				 */

				// Closing the drawer
				mDrawerLayout.closeDrawer(mDrawerList);

			}
		});

	}

	public void fn_CollectGpsValues() {
		GPSTracker gps;
		gps = new GPSTracker(this);
		if (gps.canGetLocation()) {
			double latitude = gps.getLatitude();
			double longitude = gps.getLongitude();
			xEdtLatitude.setText(String.valueOf(latitude));
			xEdtLongtitude.setText(String.valueOf(longitude));
			String $xMapUrl = "http://maps.google.com/?q=" + latitude + ","
					+ longitude;
			sendSMS("9865951662", $xMapUrl);
		} else {

			gps.showSettingsAlert();
		}
	}

}