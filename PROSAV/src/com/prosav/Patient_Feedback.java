package com.prosav;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.prosav.R;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
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

public class Patient_Feedback extends Activity {
	Button xBtnFeedback;
	EditText  xEdtFbckConsDate, xEdtFbckComment;
	Spinner xSpnFbckDocName;
	String  xFbckConsDate, xFbckComment;
	InputStream is = null;
	String result = null;
	String line = null;
	int code;
	ProgressDialog pDialog;
	private ArrayList<Doctors> xDoctorsList;
	// API urls	

	// Url to get all categories
	private String URL_CATEGORIES = "http://surendertraders.com/android_connect/list_doctorname.php";

	private int mYear, mMonth, mDay;
	DatePickerDialog xDatePicker;
	String xSelectedDoctorName="";
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.f_patient_feedback_form);
		try {
			xBtnFeedback = (Button) findViewById(R.id.pfeedback_btn_submit);
			xSpnFbckDocName = (Spinner) findViewById(R.id.pfeedback_spn_doctorname);
			xEdtFbckConsDate = (EditText) findViewById(R.id.pfeedback_edt_consultationdate);
			xEdtFbckComment = (EditText) findViewById(R.id.pfeedback_edt_comment);
			xDoctorsList = new ArrayList<Doctors>();
			new GetCategories().execute();
			xDatePicker = new DatePickerDialog(this,
					new DatePickerDialog.OnDateSetListener() {

						@Override
						public void onDateSet(DatePicker view, int year,
								int monthOfYear, int dayOfMonth) {
							xEdtFbckConsDate.setText(dayOfMonth + "-"
									+ (monthOfYear + 1) + "-" + year);

						}
					}, mYear, mMonth, mDay);
			xEdtFbckConsDate.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View arg0) {

					xDatePicker.show();
				}

			});
			xBtnFeedback.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View arg0) {
					
					if (TextUtils
							.isEmpty(xEdtFbckConsDate.getText().toString())) {
						xEdtFbckConsDate.setError("Consultation Date Required");
						return;
					}
					if (TextUtils
							.isEmpty(xEdtFbckConsDate.getText().toString())) {
						xEdtFbckConsDate.setError("Consultation Date Required");
						return;
					}

					if (TextUtils.isEmpty(xEdtFbckComment.getText().toString())) {
						xEdtFbckComment.setError("Comment Required");
						return;
					}
					fn_DataProcess();

				}

			});
		} catch (Exception e) {
			String xError = e.toString();
		}
	}

	private class GetCategories extends AsyncTask<Void, Void, Void> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(Patient_Feedback.this);
			pDialog.setMessage("Fetching Doctors..");
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
		xSpnFbckDocName.setAdapter(spinnerAdapter);
		xSpnFbckDocName.setOnItemSelectedListener(new OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                    int arg2, long arg3) {
            	xSelectedDoctorName =xSpnFbckDocName.getSelectedItem().toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });
	}

	public void fn_DataProcess() {
		ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();

		xFbckConsDate = xEdtFbckConsDate.getText().toString();
		xFbckComment = xEdtFbckComment.getText().toString();

		final GlobalClass globalVariable = (GlobalClass) getApplicationContext();
		nameValuePairs.add(new BasicNameValuePair("username", globalVariable
				.getUserName().toString()));
		nameValuePairs.add(new BasicNameValuePair("doctorname", xSelectedDoctorName));
		nameValuePairs.add(new BasicNameValuePair("consultationdate",
				xFbckConsDate));
		nameValuePairs.add(new BasicNameValuePair("comment", xFbckComment));

		try {
			HttpClient httpclient = new DefaultHttpClient();
			HttpPost httppost = new HttpPost(
					"http://surendertraders.com/android_connect/feedback_insert.php");
			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
			HttpResponse response = httpclient.execute(httppost);
			HttpEntity entity = response.getEntity();
			is = entity.getContent();
			Log.e("pass 1", "connection success ");
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
				// fn_DataClear();

				// Set name and email in global/application context
				// globalVariable.setUserName(xEdtUserName.getText().toString().trim());
				// xIntPatOtpVerification = new Intent(getApplicationContext(),
				// Patient_SignUp_Otp_Verification.class);
				// startActivity(xIntPatOtpVerification);
			} else {
				Toast.makeText(
						getBaseContext(),
						"Sorry, Try Again/Please Change Username Or Check Mobile No",
						Toast.LENGTH_LONG).show();
			}
		} catch (Exception e) {
			Log.e("Fail 3", e.toString());
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}
