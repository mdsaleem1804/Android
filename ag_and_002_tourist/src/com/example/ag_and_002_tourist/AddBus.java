package com.example.ag_and_002_tourist;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.and_af_tourist.R;

public class AddBus extends Activity {
	EditText xBusName, xDeparture, xArrival, xFare, xWebsite;
	Button xAddBusData,xClearData,xGoBack,xBtnUpdateBus,xBtnDeleteBus;
	DataBaseConnection mCon;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (android.os.Build.VERSION.SDK_INT > 9) {
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
					.permitAll().build();
			StrictMode.setThreadPolicy(policy);
		}
		mCon = new DataBaseConnection(this);
		setContentView(R.layout.addbus);
		xBusName = (EditText) findViewById(R.id.fedt_busname);
		xDeparture = (EditText) findViewById(R.id.fedt_departure);
		xArrival = (EditText) findViewById(R.id.fedt_arrival);
		xFare = (EditText) findViewById(R.id.fedt_fare);
		xWebsite = (EditText) findViewById(R.id.fedt_website);
		xAddBusData = (Button) findViewById(R.id.fbtn_addbus);
		xClearData = (Button) findViewById(R.id.fbtn_clear);
		xGoBack = (Button) findViewById(R.id.fbtn_back);
		xBtnUpdateBus = (Button) findViewById(R.id.fBtnUpdateBus);
		xBtnDeleteBus= (Button) findViewById(R.id.fBtnDeleteBus);
		xAddBusData.setEnabled(true);
		xClearData.setEnabled(false);
		xGoBack.setEnabled(false);
		xBtnUpdateBus.setEnabled(false);
		xBtnDeleteBus.setEnabled(false);
		xAddBusData.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				try {

					mCon.insertBus(
							xBusName.getText().toString(), 
							xDeparture.getText().toString(), 
							xArrival.getText().toString(), 
							xFare.getText().toString(), 
							xWebsite.getText().toString(),"S");
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
					Intent intent = new Intent(AddBus.this,
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

}