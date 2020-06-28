package com.example.ag_and_005_reminder;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class EditExpenses extends Activity implements OnItemSelectedListener {
	EditText xEdtDate, xEdtItemName, xEdtDetails, xEdtPrice, xEdtPaymentMode;
	DataBaseConnection mCon;
	Spinner xSpnCategory, xSpnPaymentMode;
	Calendar myCalendar;
	Button xBtnAdd,xBtnUpdate, xBtnDelete;
	DatePickerDialog.OnDateSetListener date;
	private SQLiteDatabase db;
	String xGetSelectedValue;
	Cursor xCursor;
	int xId;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.addexpenses);

		if (android.os.Build.VERSION.SDK_INT > 9) {
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
					.permitAll().build();
			StrictMode.setThreadPolicy(policy);
		}
		mCon = new DataBaseConnection(this);

		Bundle b = new Bundle();
		b = getIntent().getExtras();
		openDatabase();
		xGetSelectedValue = b.getString("id");
		xId=Integer.valueOf(xGetSelectedValue);

	
		
		xEdtDate = (EditText) findViewById(R.id.fedt_date);
		xEdtItemName = (EditText) findViewById(R.id.fedt_itemanme);
		xEdtDetails = (EditText) findViewById(R.id.fedt_details);
		xEdtPrice = (EditText) findViewById(R.id.fedt_price);
		xSpnPaymentMode = (Spinner) findViewById(R.id.fspn_paymentmode);
		xSpnCategory = (Spinner) findViewById(R.id.fspn_category);

		xBtnUpdate = (Button) findViewById(R.id.fbtnUpdate);
		xBtnDelete = (Button) findViewById(R.id.fbtnDelete);
		xBtnAdd = (Button) findViewById(R.id.fbtnAdd);
		
		xBtnAdd.setVisibility(View.GONE);
		xBtnUpdate.setVisibility(View.VISIBLE);
		xBtnDelete.setVisibility(View.VISIBLE);
	
		
		String xQry = "Select * from expenses where id="+xGetSelectedValue;
		xCursor = db.rawQuery(xQry, null);
		xCursor.moveToFirst();

		xEdtDate.setText(xCursor.getString(2));
		xEdtItemName.setText(xCursor.getString(3));
		xEdtDetails.setText(xCursor.getString(4));
		xEdtPrice.setText(xCursor.getString(5));
		
		
		xSpnCategory.setOnItemSelectedListener(this);
		// Spinner Drop down elements
		List<String> xEducationList = new ArrayList<String>();
		xEducationList.add("Medical");
		xEducationList.add("Entertainment");
		xEducationList.add("Food");
		xEducationList.add("Travel");
		xEducationList.add("Groceries");
		xEducationList.add("Dress");

		// Creating adapter for spinner
		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, xEducationList);

		// Drop down layout style - list view with radio button
		dataAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		// attaching data adapter to spinner
		xSpnCategory.setAdapter(dataAdapter);

		xSpnPaymentMode.setOnItemSelectedListener(this);
		// Spinner Drop down elements
		List<String> xPaymentList = new ArrayList<String>();
		xPaymentList.add("Cash");
		xPaymentList.add("Cheque");
		xPaymentList.add("Online");

		// Creating adapter for spinner
		ArrayAdapter<String> dataAdapter1 = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, xPaymentList);

		// Drop down layout style - list view with radio button
		dataAdapter1
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		// attaching data adapter to spinner
		xSpnPaymentMode.setAdapter(dataAdapter1);

		myCalendar = Calendar.getInstance();

		date = new DatePickerDialog.OnDateSetListener() {

			@Override
			public void onDateSet(DatePicker view, int year, int monthOfYear,
					int dayOfMonth) {
				// TODO Auto-generated method stub
				myCalendar.set(Calendar.YEAR, year);
				myCalendar.set(Calendar.MONTH, monthOfYear);
				myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
				updateLabel();
			}

		};

		xEdtDate.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				new DatePickerDialog(EditExpenses.this, date, myCalendar
						.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
						myCalendar.get(Calendar.DAY_OF_MONTH)).show();
			}
		});

	}
	


	protected void openDatabase() {
		db = openOrCreateDatabase("ag_and_005_reminder.db",
				Context.MODE_PRIVATE, null);
	}
	private void updateLabel() {

		String myFormat = "MM/dd/yy"; // In which you need put here
		SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

		xEdtDate.setText(sdf.format(myCalendar.getTime()));
	}

	public void onItemSelected(AdapterView<?> parent, View view, int position,
			long id) {
		// On selecting a spinner item
		String item = parent.getItemAtPosition(position).toString();

		// Showing selected spinner item
		Toast.makeText(parent.getContext(), "Selected: " + item,
				Toast.LENGTH_LONG).show();
	}

	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void updateexpenses(View v) {
		try {

			mCon.fn_UpdateExpenses(xSpnCategory.getSelectedItem().toString(),
					xEdtDate.getText().toString(), xEdtItemName.getText()
							.toString(), xEdtDetails.getText().toString(),
					xEdtPrice.getText().toString(), xSpnPaymentMode
							.getSelectedItem().toString(),xId);
			Toast.makeText(getApplicationContext(), "Expenses Added",
					Toast.LENGTH_SHORT).show();
			DataClear();

		} catch (Exception e) {

		}

	}

	public void deleteexpenses(View v) {
		mCon.fn_DeleteExpenses(xId);
		Intent intent = new Intent(EditExpenses.this, ListExpenses.class);
		startActivity(intent);
	}
	public void DataClear() {
		xEdtDate.setText("");
		xEdtItemName.setText("");
		xEdtDetails.setText("");
		xEdtPrice.setText("");
		xEdtPaymentMode.setText("");
	}
}
