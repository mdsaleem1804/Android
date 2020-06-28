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

public class Patient_Login_Succcess extends Activity {
	Button xBtnPatientProfile, xBtnDoctorsList, xBtnFeedBack;
	Intent xIntPatientProfile,xIntDoctorsList,xIntFeedBack;
	JSONParser jsonParser = new JSONParser();
	JSONObject json = null;
	String xUserName;
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

	// single patient_data url
	//
	private static final String url_product_detials = "http://surendertraders.com/android_connect/patient_get_singledata.php";
	// JSON Node names
	private static final String TAG_SUCCESS = "success";
	private static final String TAG_PATIENT_DATA = "patient_data";
	private static final String TAG_FULLNAME = "fullname";
	private static final String TAG_AGE = "age";
	private static final String TAG_MOBILENO = "mobileno";
	private static final String TAG_LANDLINENO = "landlineno";
	private static final String TAG_EMAIL = "email";
	private static final String TAG_ADDRESS = "address";
	private static final String TAG_CLINICALNOTES = "clinicalnotes";
	private static final String TAG_MEDICALHISTORY = "medicalhistory";
	private static final String TAG_SIGNUPVERSTATUS = "signupverstatus";

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.f_patient_login_success);
		xBtnPatientProfile = (Button) findViewById(R.id.button1);
		xBtnDoctorsList = (Button) findViewById(R.id.button2);
		xBtnFeedBack = (Button) findViewById(R.id.button3);
		globalVariable = (GlobalClass) getApplicationContext();
		xBtnPatientProfile.setEnabled(false);
		xBtnPatientProfile.setEnabled(false);
		new GetProductDetails().execute();
		xBtnPatientProfile.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

				xIntPatientProfile = new Intent(getApplicationContext(),
						Patient_SignUp_Updation.class);
				startActivity(xIntPatientProfile);

			}

		});
		xBtnDoctorsList.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

				xIntFeedBack = new Intent(getApplicationContext(),
						Patient_List.class);
				startActivity(xIntFeedBack);

			}

		});
		
		xBtnFeedBack.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

				xIntDoctorsList = new Intent(getApplicationContext(),
						Patient_Feedback.class);
				startActivity(xIntDoctorsList);

			}

		});
	}

	/**
	 * Background Async Task to Get complete patient_data details
	 * */
	class GetProductDetails extends AsyncTask<String, String, String> {

		/**
		 * Before starting background thread Show Progress Dialog
		 * */
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(Patient_Login_Succcess.this);
			pDialog.setMessage("Loading Your Data. Please wait...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(true);
			pDialog.show();
		}

		/**
		 * Getting patient_data details in background thread
		 * */
		protected String doInBackground(String... params) {

			// updating UI from Background Thread
			runOnUiThread(new Runnable() {
				public void run() {
					// Check for success tag
					int success;
					try {
						// Building Parameters
						List<NameValuePair> params = new ArrayList<NameValuePair>();
						xUserName = globalVariable.getUserName().toString();
						params.add(new BasicNameValuePair("username", xUserName));

						// getting patient_data details by making HTTP request
						// Note that patient_data details url will use GET
						// request
						JSONObject json = jsonParser.makeHttpRequest(
								url_product_detials, "GET", params);

						// check your log for json response
						// Log.d("Single patient_data Details",
						// json.toString());

						// json success tag
						success = json.getInt(TAG_SUCCESS);
						if (success == 1) {
							final GlobalClass globalVariable = (GlobalClass) getApplicationContext();
							// successfully received patient_data details
							JSONArray productObj = json
									.getJSONArray(TAG_PATIENT_DATA); // JSON
																		// Array

							// get first patient_data object from JSON Array
							JSONObject patient_data = productObj
									.getJSONObject(0);
							globalVariable.setFullName(patient_data
									.getString(TAG_FULLNAME));
							globalVariable.setAge(Integer.parseInt(patient_data
									.getString(TAG_AGE)));
							globalVariable.setMobileno(patient_data
									.getString(TAG_MOBILENO));
							globalVariable.setLandlineno(patient_data
									.getString(TAG_LANDLINENO));
							globalVariable.setEmail(patient_data
									.getString(TAG_EMAIL));
							globalVariable.setAddress(patient_data
									.getString(TAG_ADDRESS));
							globalVariable.setClinicalnotes(patient_data
									.getString(TAG_CLINICALNOTES));
							globalVariable.setMedicalhistory(patient_data
									.getString(TAG_MEDICALHISTORY));
							globalVariable.setSignUp_Ver_Status(patient_data
									.getString(TAG_SIGNUPVERSTATUS));
						} else {
							// patient_data with pid not found
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
			String xSignUpVerStatus = globalVariable.getSignUp_Ver_Status().toString();
			if(xSignUpVerStatus.equalsIgnoreCase("0"))
			{
				finish();
				Intent xIntPatientOtp = new Intent(getApplicationContext(),
						Patient_SignUp_Otp_Verification.class);
				startActivity(xIntPatientOtp);
			}
			pDialog.dismiss();
			xBtnPatientProfile.setEnabled(true);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}