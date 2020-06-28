package com.example.ag_and_002_tourist;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.and_af_tourist.R;

public class AdminBusEdit extends Activity {
	EditText xBusName, xDeparture, xArrival, xFare, xWebsite;
	Button xAddBusData, xClearData, xGoBack, xBtnUpdateBus, xBtnDeleteBus;
	DataBaseConnection mCon;
	private SQLiteDatabase db;
	private Cursor xCursor;
	String xSelectedBus;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (android.os.Build.VERSION.SDK_INT > 9) {
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
					.permitAll().build();
			StrictMode.setThreadPolicy(policy);
		}
		mCon = new DataBaseConnection(this);
		setContentView(R.layout.addbus);

		Bundle b = new Bundle();
		b = getIntent().getExtras();
		openDatabase();
		xSelectedBus = b.getString("name");

		xBusName = (EditText) findViewById(R.id.fedt_busname);
		xDeparture = (EditText) findViewById(R.id.fedt_departure);
		xArrival = (EditText) findViewById(R.id.fedt_arrival);
		xFare = (EditText) findViewById(R.id.fedt_fare);
		xWebsite = (EditText) findViewById(R.id.fedt_website);
		xAddBusData = (Button) findViewById(R.id.fbtn_addbus);
		xClearData = (Button) findViewById(R.id.fbtn_clear);
		xGoBack = (Button) findViewById(R.id.fbtn_back);
		xBtnUpdateBus = (Button) findViewById(R.id.fBtnUpdateBus);
		xBtnDeleteBus = (Button) findViewById(R.id.fBtnDeleteBus);
		xAddBusData.setVisibility(1);

		xBusName.setText(xSelectedBus);
		String xQry = "Select * from bus";
		xCursor = db.rawQuery(xQry, null);
		xCursor.moveToFirst();
		showRecords();

		xBtnUpdateBus.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				try {

					mCon.insertBus(xBusName.getText().toString(), xDeparture
							.getText().toString(), xArrival.getText()
							.toString(), xFare.getText().toString(), xWebsite
							.getText().toString(), "U");
					Toast.makeText(getApplicationContext(),
							"Bus Details Added", Toast.LENGTH_SHORT).show();
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
					Intent intent = new Intent(AdminBusEdit.this,
							AdminMainPage.class);
					startActivity(intent);
				} catch (Exception e) {

				}

			}
		});
	}

	public void DataClear() {
		xBusName.setText("");
		xDeparture.setText("");
		xArrival.setText("");
		xFare.setText("");
		xWebsite.setText("");

	}

	protected void openDatabase() {
		db = openOrCreateDatabase("ag_and_002_tourist.db",
				Context.MODE_PRIVATE, null);
	}
	public void weblink(View v) {
		String xUrl=xWebsite.getText().toString();
		if (!xUrl.startsWith("http://") && !xUrl.startsWith("https://"))
			xUrl = "http://" + xUrl;
		Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(xUrl));
		startActivity(browserIntent);
	}
	public void places(View v) {

		Intent intent = new Intent(AdminBusEdit.this, ListPlaces
				.class);
		startActivity(intent);
	}

	protected void showRecords() {
		xBusName.setText(xCursor.getString(0));
		xDeparture.setText(xCursor.getString(1));
		xArrival.setText(xCursor.getString(2));
		xFare.setText(xCursor.getString(3));
		xWebsite.setText(xCursor.getString(4));
		// xEdtWebsite.setText(xCursor.getString(6));

	}

	public void deletebus(View v) {
		mCon.fn_DeleteBusDetails(xSelectedBus);
		Intent intent = new Intent(AdminBusEdit.this, ListBus.class);
		intent.putExtra("formname", "adminpage");
		startActivity(intent);
	}

}