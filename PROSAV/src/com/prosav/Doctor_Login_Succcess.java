package com.prosav;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.prosav.R;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;

public class Doctor_Login_Succcess extends Activity {
	Button xBtnDoctorProfile, xBtnPatientList, xBtnReminder;
	Intent xIntDoctorProfile, xIntPatientList, xIntReminder;
	JSONParser jsonParser = new JSONParser();
	JSONObject json = null;
	String xDUserName;
	Context context;
	ProgressBar progressbar;
	GlobalClass globalVariable;
	InputStream is = null;
	String result = null;
	String line = null;
	int code;
	// Progress Dialog
	private ProgressDialog pDialog;

	// JSON parser class

	// single doctor_data url
	//																
	private static final String url_doctor_detials = "http://surendertraders.com/android_connect/doctor_get_singledata.php";
	// JSON Node names
	private static final String TAG_SUCCESS = "success";
	private static final String TAG_DOCTOR_DATA = "doctor_data";
	private static final String TAG_FULLNAME = "fullname";
	private static final String TAG_MOBILENO = "mobileno";

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.f_doctor_login_success);
		try {
			xBtnDoctorProfile = (Button) findViewById(R.id.button1);
			xBtnPatientList = (Button) findViewById(R.id.button2);
			xBtnReminder = (Button) findViewById(R.id.button3);
			globalVariable = (GlobalClass) getApplicationContext();
			xBtnDoctorProfile.setEnabled(false);
			xBtnDoctorProfile.setEnabled(false);
			new GetProductDetails().execute();
			xBtnDoctorProfile.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View arg0) {

					xIntDoctorProfile = new Intent(getApplicationContext(),
							Doctor_SignUp_Updation.class);
					startActivity(xIntDoctorProfile);

				}

			});
			xBtnPatientList.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View arg0) {

					xIntPatientList = new Intent(getApplicationContext(),
							Patient_List.class);
					startActivity(xIntPatientList);

				}

			});

			xBtnReminder.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View arg0) {

					xIntReminder = new Intent(getApplicationContext(),
							Doctor_SetReminder.class);
					startActivity(xIntReminder);

				}

			});
		} catch (Exception e) {
			String xError = e.toString();
		}
	}

	/**
	 * Background Async Task to Get complete doctor_data details
	 * */
	class GetProductDetails extends AsyncTask<String, String, String> {

		/**
		 * Before starting background thread Show Progress Dialog
		 * */
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(Doctor_Login_Succcess.this);
			pDialog.setMessage("Loading Your Data. Please wait...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(true);
			pDialog.show();
		}

		/**
		 * Getting doctor_data details in background thread
		 * */
		protected String doInBackground(String... params) {

			// updating UI from Background Thread
			runOnUiThread(new Runnable() {
				public void run() {

					int success;
					try {

						List<NameValuePair> params = new ArrayList<NameValuePair>();
						xDUserName = globalVariable.getDUserName().toString();
						params.add(new BasicNameValuePair("username",
								xDUserName));
						
						JSONObject json = jsonParser.makeHttpRequest(
								url_doctor_detials, "GET", params);

						success = json.getInt(TAG_SUCCESS);
						if (success == 1) {
							final GlobalClass globalVariable = (GlobalClass) getApplicationContext();

							JSONArray productObj = json
									.getJSONArray(TAG_DOCTOR_DATA);
							JSONObject doctor_data = productObj
									.getJSONObject(0);
							globalVariable.setDFullName(doctor_data
									.getString(TAG_FULLNAME));
							globalVariable.setDMobileno(doctor_data
									.getString(TAG_MOBILENO));

						} else {
							// doctor_data with pid not found
						}
					} catch (JSONException e) {
						e.printStackTrace();
					}
				}
			});

			return null;
		}

		/**
		 * After completing background task Dismiss the progress dialog
		 * **/
		protected void onPostExecute(String file_url) {
			// dismiss the dialog once got all details
			String xSignUpVerStatus = globalVariable.getDSignUp_Ver_Status()
					.toString();
			if (xSignUpVerStatus.equalsIgnoreCase("2")) {
				finish();
				Intent xIntDoctorOtp = new Intent(getApplicationContext(),
						Doctor_SignUp_Otp_Verification.class);
				startActivity(xIntDoctorOtp);
			}
			pDialog.dismiss();
			xBtnDoctorProfile.setEnabled(true);

		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	@Override
	public void onBackPressed(){

		Intent xIntDoctorLogin = new Intent(getApplicationContext(),
				Doctor_Login.class);
		startActivity(xIntDoctorLogin);
	}
}