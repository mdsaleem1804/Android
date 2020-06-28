package com.example.ag_and_009_personalassistant;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.EditText;

import com.example.ag_and_009_dailystudentactivities.R;

public class EditReminder extends Activity implements OnItemSelectedListener {
	EditText xEdtName, xEdtPlace, xEdtDescription, xEdtDate;
	DataBaseConnection mCon;
	private SQLiteDatabase db;
	private Cursor xCursor;
String xSelectedReminder;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mCon = new DataBaseConnection(this);
		setContentView(R.layout.reminder);
		xEdtName = (EditText) findViewById(R.id.fedt_Name);
		xEdtPlace = (EditText) findViewById(R.id.fedt_Place);

		xEdtDescription = (EditText) findViewById(R.id.fedt_Description);
		xEdtDate = (EditText) findViewById(R.id.fedt_Date);
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		xEdtDate.setText(dateFormat.format(new Date())); // it will show
		Bundle b = new Bundle();
		b = getIntent().getExtras();
		openDatabase();
		xSelectedReminder = b.getString("remindername");
		mCon = new DataBaseConnection(this);
		
		String xQry = "Select * from reminder";
		xCursor = db.rawQuery(xQry, null);
		xCursor.moveToFirst();
		showRecords();
		
	
	}

	protected void showRecords() {
		xEdtName.setText(xCursor.getString(0));
		xEdtPlace.setText(xCursor.getString(1));
		xEdtDescription.setText(xCursor.getString(2));
		xEdtDate.setText(xCursor.getString(3));
	}

	protected void openDatabase() {
		db = openOrCreateDatabase("ag_and_009_personalassistant",
				Context.MODE_PRIVATE, null);
	}

/*	public void deletescheme(View v) {
		mCon.fn_DeleteSchemeDetails(xSelectedReminder);
		Intent intent = new Intent(AdminSchemeEdit.this, ListSchemes.class);
		startActivity(intent);
	}

	public void updatereminder(View v) {
	
		if (mCon.fn_DataProcess(xEdtSchemeName.getText().toString(), xEdtMinAge
				.getText().toString(), xEdtMaxAge.getText().toString(),
				xSpnEducation.getSelectedItem().toString(), xSpnCaste
						.getSelectedItem().toString(), xSpnSection
						.getSelectedItem().toString(), xEdtAmount.getText()
						.toString(), xEdtDetail.getText().toString(),
				xEdtWebsite.getText().toString(), "U")) {
			Toast.makeText(getApplicationContext(), "Updated", 1000).show();
			Intent intent = new Intent(AdminSchemeEdit.this, HomePage.class);
			startActivity(intent);
		} else {
			Toast.makeText(getApplicationContext(), "Validation Required", 1000)
					.show();
		}
	}
*/
	public void dataclear() {
		xEdtDate.setText("");
		xEdtDescription.setText("");
		xEdtName.setText("");
		xEdtPlace.setText("");
	}

	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub

	}
}

/*
public class EditReminder extends Activity implements OnItemSelectedListener {
	EditText xEdtName, xEdtDescription, xEdtDate;
	DataBaseConnection mCon;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mCon = new DataBaseConnection(this);
		setContentView(R.layout.editreminder);
		xEdtName = (EditText) findViewById(R.id.fedt_name);
		xEdtDescription = (EditText) findViewById(R.id.fedt_description);
		xEdtDate = (EditText) findViewById(R.id.fedt_date);
		// SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		// String date = sdf.format(new Date());
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		xEdtDate.setText(dateFormat.format(new Date())); // it will show
															// 16/07/2013

		
		 * if (android.os.Build.VERSION.SDK_INT > 9) { StrictMode.ThreadPolicy
		 * policy = new StrictMode.ThreadPolicy.Builder() .permitAll().build();
		 * StrictMode.setThreadPolicy(policy); }
		 
	}

	public void savereminder(View v) {
		mCon.insertReminder(xEdtName.getText().toString(), xEdtDescription
				.getText().toString(), xEdtDate.getText().toString());
		DataClear();
		Toast.makeText(getApplicationContext(), "Reminder Saves", 1000).show();
	}

	public void DataClear() {
		xEdtDate.setText("");
		xEdtDescription.setText("");
		xEdtName.setText("");
	}

	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub

	}*/

