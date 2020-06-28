package com.example.and_af_donor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.StrictMode;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

public class NeedLocation extends Activity {
	private LocationManager locationMangaer = null;
	private LocationListener locationListener = null;

	private Button btnGetLocation = null;
	private EditText editLocation = null;
	private ProgressBar pb = null;
	private static final String TAG = "Debug";
	private Boolean flag = false;
	Button xbtnListDevices;
	Intent xintent;

	String xLatitude, xLongitude, xCity, xImei;
	InputStream is = null;
	String result = null;
	String line = null;
	int code;
	TelephonyManager xTelphoneDetails;
	String xError;
	EditText xUserName;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.f_needlocation);
		editLocation = (EditText) findViewById(R.id.editTextLocation);
		pb = (ProgressBar) findViewById(R.id.progressBar1);
		xUserName = (EditText) findViewById(R.id.edtusername);
		pb.setVisibility(View.INVISIBLE);
		if (android.os.Build.VERSION.SDK_INT > 9) {
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
					.permitAll().build();
			StrictMode.setThreadPolicy(policy);
		}
		locationMangaer = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	// ----Method to Check GPS is enable or disable -----
	private Boolean displayGpsStatus() {
		ContentResolver contentResolver = getBaseContext().getContentResolver();
		@SuppressWarnings("deprecation")
		boolean gpsStatus = Settings.Secure.isLocationProviderEnabled(
				contentResolver, LocationManager.GPS_PROVIDER);
		if (gpsStatus) {
			return true;

		} else {
			return false;
		}
	}

	// ----------Method to create an AlertBox -------------
	protected void alertbox(String title, String mymessage) {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage("Your Device's GPS is Disable")
				.setCancelable(false)
				.setTitle("** Gps Status **")
				.setPositiveButton("Gps On",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								// finish the current activity
								// AlertBoxAdvance.this.finish();
								Intent myIntent = new Intent(
										Settings.ACTION_SECURITY_SETTINGS);
								startActivity(myIntent);
								dialog.cancel();
							}
						})
				.setNegativeButton("Cancel",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								// cancel the dialog box
								dialog.cancel();
							}
						});
		AlertDialog alert = builder.create();
		alert.show();
	}

	// ----------Listener class to get coordinates -------------
	private class MyLocationListener implements LocationListener {
		@Override
		public void onLocationChanged(Location loc) {

			editLocation.setText("");
			pb.setVisibility(View.INVISIBLE);
			Toast.makeText(
					getBaseContext(),
					"Location changed : Lat: " + loc.getLatitude() + " Lng: "
							+ loc.getLongitude(), Toast.LENGTH_SHORT).show();
			xLongitude = "" + loc.getLongitude();
			Log.v(TAG, xLongitude);
			xLatitude = "" + loc.getLatitude();
			Log.v(TAG, xLatitude);

			// ----------to get City-Name from coordinates -------------

			Geocoder gcd = new Geocoder(getBaseContext(), Locale.getDefault());
			List<Address> addresses;
			try {
				addresses = gcd.getFromLocation(loc.getLatitude(),
						loc.getLongitude(), 1);
				if (addresses.size() > 0)
					System.out.println(addresses.get(0).getLocality());
				xCity = addresses.get(0).getSubLocality();
			} catch (IOException e) {
				e.printStackTrace();
			}

			String s = xLongitude + "\n" + xLatitude
					+ "\n\n Currrent Location  is: " + xCity;
			editLocation.setText(s);

		}

		@Override
		public void onProviderDisabled(String arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onProviderEnabled(String arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
			// TODO Auto-generated method stub

		}
	}

	public void share(View v) {
		try {
			insert();
		} catch (Exception e) {
			xError = e.toString();
		}
	}

	public void getLocation(View v) {
		try {

			flag = displayGpsStatus();
			if (flag) {

				Log.v(TAG, "onClick");

				editLocation.setText("Please!! move your device to"
						+ " see the changes in coordinates." + "\nWait..");

				pb.setVisibility(View.VISIBLE);
				locationListener = new MyLocationListener();

				locationMangaer.requestLocationUpdates(
						LocationManager.GPS_PROVIDER, 5000, 10,
						locationListener);

			} else {
				alertbox("Gps Status!!", "Your GPS is: OFF");
			}
		}

		catch (Exception e) {
			xError = e.toString();
		}
	}

	public void insert() {
		ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
		String device_id = tm.getDeviceId();
		String mydate = java.text.DateFormat.getDateTimeInstance().format(
				Calendar.getInstance().getTime());

		/*
		 * nameValuePairs.add(new BasicNameValuePair("latitude", "8.719910"));
		 * nameValuePairs.add(new BasicNameValuePair("longtitude",
		 * "77.740626"));
		 */

		nameValuePairs.add(new BasicNameValuePair("latitude", xLatitude));
		nameValuePairs.add(new BasicNameValuePair("longtitude", xLongitude));

		nameValuePairs.add(new BasicNameValuePair("username", xUserName
				.getText().toString()));
		nameValuePairs.add(new BasicNameValuePair("datetime", mydate));

		/*
		 * nameValuePairs.add(new BasicNameValuePair("latitude", "1"));
		 * nameValuePairs.add(new BasicNameValuePair("longtitude", "2"));
		 * nameValuePairs.add(new BasicNameValuePair("imei", "3"));
		 * nameValuePairs.add(new BasicNameValuePair("datetime", "4"));
		 */
		try {
			HttpClient httpclient = new DefaultHttpClient();
			HttpPost httppost = new HttpPost(
					"http://mohamedsaleem.co.uk/tcss/and-af-donor/mobiletracking_insert.php");
			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
			HttpResponse response = httpclient.execute(httppost);
			HttpEntity entity = response.getEntity();
			is = entity.getContent();
			Log.e("pass 1", "connection success ");
			Toast.makeText(getBaseContext(), "Inserted Successfully",
					Toast.LENGTH_SHORT).show();
		} catch (Exception e) {
			Log.e("Fail 1", e.toString());
			Toast.makeText(getApplicationContext(), "Invalid IP Address",
					Toast.LENGTH_LONG).show();
		}

		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					is, "iso-8859-1"), 8);
			StringBuilder sb = new StringBuilder();
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
			is.close();
			result = sb.toString();
			Log.e("pass 2", "connection success ");
		} catch (Exception e) {
			Log.e("Fail 2", e.toString());
		}

		try {
			JSONObject json_data = new JSONObject(result);
			code = (json_data.getInt("code"));

			if (code == 1) {
				Toast.makeText(getBaseContext(), "Inserted Successfully",
						Toast.LENGTH_SHORT).show();
			} else {
				Toast.makeText(getBaseContext(), "Sorry, Try Again",
						Toast.LENGTH_LONG).show();
			}
		} catch (Exception e) {
			Log.e("Fail 3", e.toString());
		}
	}
}
