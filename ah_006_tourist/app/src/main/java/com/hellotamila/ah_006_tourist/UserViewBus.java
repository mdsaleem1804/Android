package com.hellotamila.ah_006_tourist;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class UserViewBus extends Activity {
	String xSelectedBus;
	EditText xBusName, xDeparture, xArrival, xFare, xWebsite;
	Button xAddBusData,xClearData,xGoBack,xBtnUpdateBus,xBtnDeleteBus;
	DataBaseConnection mCon;
	private SQLiteDatabase db;
	private Cursor xCursor;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		try{
			
	
		setContentView(R.layout.addbus);
		Bundle b = new Bundle();
		b = getIntent().getExtras();
		 openDatabase();
		xSelectedBus = b.getString("name");
		mCon = new DataBaseConnection(this);
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
		
		xAddBusData.setEnabled(false);
		xClearData.setEnabled(false);
		xGoBack.setEnabled(false);
		xBtnUpdateBus.setEnabled(false);
		xBtnDeleteBus.setEnabled(false);
		
		xBusName.setEnabled(false);
		xDeparture.setEnabled(false);
		xArrival.setEnabled(false);
		xFare.setEnabled(false);
		xWebsite.setEnabled(false);
		
		
		
		
		xBusName.setText(xSelectedBus);
		String xQry = "Select * from bus where busname='"+xSelectedBus+"'";
		xCursor = db.rawQuery(xQry, null);
		xCursor.moveToFirst();
		showRecords();
		}
		catch(Exception e)
		{
			String xError=e.toString();
		}

	}
	protected void openDatabase() {
        db = openOrCreateDatabase("ag_and_002_tourist.db", Context.MODE_PRIVATE, null);
    }
	protected void showRecords() {
		xBusName.setText(xCursor.getString(0));
		xDeparture.setText(xCursor.getString(1));
		xArrival.setText(xCursor.getString(2));
		xFare.setText(xCursor.getString(3));
		xWebsite.setText(xCursor.getString(4));
		//xEdtWebsite.setText(xCursor.getString(6));

	}

	
	public void weblink(View v) {
		String xUrl=xWebsite.getText().toString();
		if (!xUrl.startsWith("http://") && !xUrl.startsWith("https://"))
			xUrl = "http://" + xUrl;
		Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(xUrl));
		startActivity(browserIntent);
	}
	public void places(View v) {
		
			Intent intent = new Intent(UserViewBus.this,
					ListPlaces.class);
			startActivity(intent);
	}

}
