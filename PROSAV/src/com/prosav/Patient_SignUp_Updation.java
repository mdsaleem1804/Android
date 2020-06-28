package com.prosav;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import com.prosav.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class Patient_SignUp_Updation extends Activity {
	EditText xEdtFullName, xEdtAge, xEdtMobileNo, xEdtLandlineNo, xEdtEmail,
			xEdtAddress, xEdtClinicalNotes, xEdtMedicalHistory;
	String xFullName, xAge, xMobileNo, xLandlineNo, xEmail, xAddress,
			xClinicalNotes, xMedicalHistory;
	Button xBtnPatientSignUpdate;
	Intent xIntPatOtpVerification;
	InputStream is = null;
	String result = null;
	String line = null;
	int code;
	GlobalClass globalVariable;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.f_patient_signup_updation);
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
				.permitAll().build();

		StrictMode.setThreadPolicy(policy);
		Spinner xspngender = (Spinner) findViewById(R.id.spngender);
		Spinner xspnbloodgroup = (Spinner) findViewById(R.id.spnbloodgroup);
		xEdtFullName = (EditText) findViewById(R.id.psignup_updation_edt_name);
		xEdtAge = (EditText) findViewById(R.id.psignup_updation_edt_age);
		xEdtMobileNo = (EditText) findViewById(R.id.psignup_updation_edt_mobileno);
		xEdtLandlineNo = (EditText) findViewById(R.id.psignup_updation_edt_landlineno);
		xEdtEmail = (EditText) findViewById(R.id.psignup_updation_edt_email);
		xEdtAddress = (EditText) findViewById(R.id.psignup_updation_edt_address);
		xEdtClinicalNotes = (EditText) findViewById(R.id.psignup_updation_edt_clinicalnotes);
		xEdtMedicalHistory = (EditText) findViewById(R.id.psignup_updation_edt_medicalhistory);
		xBtnPatientSignUpdate = (Button) findViewById(R.id.psignup_btn_signup_update);
		globalVariable = (GlobalClass) getApplicationContext();
		try {
			xEdtFullName.setText(globalVariable.getFullName().toString());
			xEdtAge.setText(globalVariable.getAge().toString());
			xEdtMobileNo.setText(globalVariable.getMobileno().toString());
			xEdtLandlineNo.setText(globalVariable.getLandlineno().toString());
			xEdtEmail.setText(globalVariable.getEmail().toString());
			xEdtAddress.setText(globalVariable.getAddress().toString());
			xEdtClinicalNotes.setText(globalVariable.getClinicalnotes()
					.toString());
			xEdtMedicalHistory.setText(globalVariable.getMedicalhistory()
					.toString());

		} catch (Exception e) {
			String xError = e.toString();
			Toast.makeText(getApplicationContext(), "Error" + xError,
					Toast.LENGTH_SHORT).show();
		}

		xBtnPatientSignUpdate.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				if (TextUtils.isEmpty(xEdtFullName.getText().toString())) {
					xEdtFullName.setError("Full Name Required");
					return;
				}
				if (TextUtils.isEmpty(xEdtAge.getText().toString())) {
					xEdtAge.setError("Age Required");
					return;
				}
				if (!isEmailValid(xEdtEmail.getText().toString())) {
					Toast.makeText(getApplicationContext(),
							"Not a Valid Email Id", Toast.LENGTH_SHORT).show();
					xEdtEmail.requestFocus();
					return;

				}

				if (!isValidMobile(xEdtMobileNo.getText().toString())) {
					Toast.makeText(getApplicationContext(),
							"Mobile Number Must Be 10 Digit",
							Toast.LENGTH_SHORT).show();
					xEdtMobileNo.requestFocus();
					return;
				}
				fn_DataProcess();
				// updateEmployee();

			}

		});
		List<String> xGender = new ArrayList<String>();

		xGender.add("Male");
		xGender.add("Female");

		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, xGender);

		dataAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		xspngender.setAdapter(dataAdapter);

		List<String> xBloodGroup = new ArrayList<String>();

		xBloodGroup.add("A+");
		xBloodGroup.add("A-");
		xBloodGroup.add("B+");
		xBloodGroup.add("B-");
		xBloodGroup.add("AB+");
		xBloodGroup.add("AB-");
		xBloodGroup.add("O+");
		xBloodGroup.add("O-");

		ArrayAdapter<String> dataAdapter1 = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, xBloodGroup);

		dataAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		xspnbloodgroup.setAdapter(dataAdapter1);

		// Get name and email from global/application context

	}

	private boolean isValidMobile(String phone2) {
		boolean check;
		if (phone2.length() < 10 || phone2.length() > 12) {
			check = false;
			xEdtMobileNo.setError("Not Valid Number");
		} else {
			check = true;
		}
		return check;
	}

	public boolean isEmailValid(String email) {
		String regExpn = "^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
				+ "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
				+ "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
				+ "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
				+ "[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
				+ "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$";

		CharSequence inputStr = email;

		Pattern pattern = Pattern.compile(regExpn, Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(inputStr);

		if (matcher.matches())
			return true;
		else
			return false;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void fn_DataClear() {
		xEdtFullName.setText("");
		xEdtAge.setText("");
		xEdtMobileNo.setText("");
		xEdtLandlineNo.setText("");
		xEdtEmail.setText("");
		xEdtAddress.setText("");
		xEdtClinicalNotes.setText("");
		xEdtMedicalHistory.setText("");
	}

	public void fn_DataProcess() {
		ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		xFullName = xEdtFullName.getText().toString().trim();
		xAge = xEdtAge.getText().toString().trim();
		xMobileNo = xEdtMobileNo.getText().toString().trim();
		xLandlineNo = xEdtLandlineNo.getText().toString().trim();
		xEmail = xEdtEmail.getText().toString().trim();
		xAddress = xEdtAddress.getText().toString().trim();
		xClinicalNotes = xEdtClinicalNotes.getText().toString().trim();
		xMedicalHistory = xEdtMedicalHistory.getText().toString().trim();
		nameValuePairs.add(new BasicNameValuePair("username", globalVariable
				.getUserName().toString().trim()));
		nameValuePairs.add(new BasicNameValuePair("fullname", xFullName));
		nameValuePairs.add(new BasicNameValuePair("age", xAge));
		nameValuePairs.add(new BasicNameValuePair("mobileno", xMobileNo));
		nameValuePairs.add(new BasicNameValuePair("landlineno", xLandlineNo));
		nameValuePairs.add(new BasicNameValuePair("email", xEmail));
		nameValuePairs.add(new BasicNameValuePair("address", xAddress));
		nameValuePairs.add(new BasicNameValuePair("clinicalnotes",
				xClinicalNotes));
		nameValuePairs.add(new BasicNameValuePair("medicalhistory",
				xMedicalHistory));

		try {
			HttpClient httpclient = new DefaultHttpClient();
			HttpPost httppost = new HttpPost(
					"http://surendertraders.com/android_connect/patient_signup_update.php");
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
				Toast.makeText(getBaseContext(), "Updated Successfully",
						Toast.LENGTH_SHORT).show();
				fn_DataClear();
				Intent xIntPatientLoginSuccess = new Intent(
						getApplicationContext(), Patient_Login_Succcess.class);
				startActivity(xIntPatientLoginSuccess);
			} else {
				Toast.makeText(getBaseContext(), "Sorry, Try Again",
						Toast.LENGTH_LONG).show();
			}
		} catch (Exception e) {
			Log.e("Fail 3", e.toString());
		}
	}

}
