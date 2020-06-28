package com.prosav;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Patient_SignUp extends Activity {
	Button xBtnPatientSignUp;
	EditText xEdtFullName, xEdtMobileNo, xEdtUserName, xEdtPassword;
	String xFullName, xPassword, xUserName, xMobileNo;
	Intent xIntPatOtpVerification;
	InputStream is = null;
	String result = null;
	String line = null;
	int code;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.f_patient_signup);
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
				.permitAll().build();

		StrictMode.setThreadPolicy(policy);
		xBtnPatientSignUp = (Button) findViewById(R.id.psignup_btn_signup);
		xEdtFullName = (EditText) findViewById(R.id.psignup_edt_fullname);
		xEdtMobileNo = (EditText) findViewById(R.id.psignup_edt_mobileno);
		xEdtUserName = (EditText) findViewById(R.id.psignup_edt_username);
		xEdtPassword = (EditText) findViewById(R.id.psignup_edt_password);

		xBtnPatientSignUp.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				try {
					if (TextUtils.isEmpty(xEdtFullName.getText().toString())) {
						xEdtFullName.setError("Full Name Required");
						return;
					}

					if (!isValidMobile(xEdtMobileNo.getText().toString())) {
						Toast.makeText(getApplicationContext(),
								"Mobile Number Must Be 10 Digit",
								Toast.LENGTH_SHORT).show();
						xEdtMobileNo.requestFocus();
						return;
					}
					if (TextUtils.isEmpty(xEdtUserName.getText().toString())) {
						xEdtUserName.setError("User Name Required");
						return;
					}

					if (TextUtils.isEmpty(xEdtPassword.getText().toString())) {
						xEdtPassword.setError("Password Required");
						return;
					}
					try {
						fn_DataProcess();
					} catch (Exception e) {

					}

				} catch (Exception e) {

				}
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
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

	public void fn_DataClear() {
		xEdtFullName.setText("");
		xEdtMobileNo.setText("");
		xEdtUserName.setText("");
		xEdtPassword.setText("");
	}

	public void fn_DataProcess() {
		ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		xFullName = xEdtFullName.getText().toString();
		xMobileNo = xEdtMobileNo.getText().toString();
		xUserName = xEdtUserName.getText().toString();
		xPassword = xEdtPassword.getText().toString();

		nameValuePairs.add(new BasicNameValuePair("fullname", xFullName));
		nameValuePairs.add(new BasicNameValuePair("mobileno", xMobileNo));
		nameValuePairs.add(new BasicNameValuePair("username", xUserName));
		nameValuePairs.add(new BasicNameValuePair("password", xPassword));
		try {
			HttpClient httpclient = new DefaultHttpClient();
			HttpPost httppost = new HttpPost(
					"http://surendertraders.com/android_connect/patient_insert.php");
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
				fn_DataClear();
				final GlobalClass globalVariable = (GlobalClass) getApplicationContext();

				// Set name and email in global/application context
				globalVariable.setUserName(xEdtUserName.getText().toString()
						.trim());
				xIntPatOtpVerification = new Intent(getApplicationContext(),
						Patient_SignUp_Otp_Verification.class);
				startActivity(xIntPatOtpVerification);
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

}
