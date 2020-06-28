package com.prosav;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;

public class Doctor_SetReminder extends Activity {
	GlobalClass globalVariable;
	EditText xEdtRemDoctorName, xEdtNextVisDate;

	private DatePickerDialog DatePickerDialog;

	private SimpleDateFormat dateFormatter;
	Spinner xSpnRemPatName;
	String xSelectedPatientName="";
	
	InputStream is = null;
	String result = null;
	String line = null;
	int code;
	ProgressDialog pDialog;
	private ArrayList<Doctors> xDoctorsList;
	// API urls	

	// Url to get all categories
	private String URL_CATEGORIES = "http://surendertraders.com/android_connect/list_doctorname.php";

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.f_set_reminder);
		xSpnRemPatName = (Spinner) findViewById(R.id.spn_patientname);
		xEdtRemDoctorName = (EditText) findViewById(R.id.dreminder_edt_doctorname);
		xEdtNextVisDate = (EditText) findViewById(R.id.dreminder_edt_patientnextvisdate);
		globalVariable = (GlobalClass) getApplicationContext();
		dateFormatter = new SimpleDateFormat("yyyy-M-dd", Locale.US);

		// xEdtRemDoctorName.setText(globalVariable.getDUserName().toString());
		Calendar newCalendar = Calendar.getInstance();
		DatePickerDialog = new DatePickerDialog(this,
				new OnDateSetListener() {

					public void onDateSet(DatePicker view, int year,
							int monthOfYear, int dayOfMonth) {
						Calendar newDate = Calendar.getInstance();
						newDate.set(year, monthOfYear, dayOfMonth);
						xEdtNextVisDate.setText(dateFormatter.format(newDate
								.getTime()));
					}

				}, newCalendar.get(Calendar.YEAR),
				newCalendar.get(Calendar.MONTH),
				newCalendar.get(Calendar.DAY_OF_MONTH));

		xEdtNextVisDate.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

				DatePickerDialog.show();

			}

		});
		new GetCategories().execute();
	}
	private class GetCategories extends AsyncTask<Void, Void, Void> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(Doctor_SetReminder.this);
			pDialog.setMessage("Fetching Patients..");
			pDialog.setCancelable(false);
			pDialog.show();

		}

		@Override
		protected Void doInBackground(Void... arg0) {
			ServiceHandler jsonParser = new ServiceHandler();
			String json = jsonParser.makeServiceCall(URL_CATEGORIES,
					ServiceHandler.GET);

			Log.e("Response: ", "> " + json);

			if (json != null) {
				try {
					JSONObject jsonObj = new JSONObject(json);
					if (jsonObj != null) {
						JSONArray categories = jsonObj.getJSONArray("doctors");

						for (int i = 0; i < categories.length(); i++) {
							JSONObject catObj = (JSONObject) categories.get(i);
							Doctors xDoctorList = new Doctors(
									catObj.getString("name"));
							xDoctorsList.add(xDoctorList);
						}
					}

				} catch (JSONException e) {
					e.printStackTrace();
				}

			} else {
				Log.e("JSON Data", "Didn't receive any data from server!");
			}

			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			if (pDialog.isShowing())
				pDialog.dismiss();
			populateSpinner();
		}

	}

	/**
	 * Adding spinner data
	 * */
	private void populateSpinner() {
		List<String> lables = new ArrayList<String>();

		// txtCategory.setText("");

		for (int i = 0; i < xDoctorsList.size(); i++) {
			lables.add(xDoctorsList.get(i).getName());
		}

		// Creating adapter for spinner
		ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, lables);

		// Drop down layout style - list view with radio button
		spinnerAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		// attaching data adapter to spinner
		xSpnRemPatName.setAdapter(spinnerAdapter);
		xSpnRemPatName.setOnItemSelectedListener(new OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                    int arg2, long arg3) {
            	xSelectedPatientName =xSpnRemPatName.getSelectedItem().toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}
