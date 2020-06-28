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
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Doctor_SignUp extends Activity {
	Button xBtnDoctorSignUp;
	EditText xEdtDFullName, xEdtDMobileNo, xEdtDUserName, xEdtDPassword;
	String xDFullName, xDPassword, xDUserName, xDMobileNo;
	Intent xIntPatOtpVerification;
	InputStream is = null;
	String result = null;
	String line = null;
	int code;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.f_doctor_signup);
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
				.permitAll().build();

		StrictMode.setThreadPolicy(policy);
		xBtnDoctorSignUp = (Button) findViewById(R.id.dsignup_btn_signup);
		xEdtDFullName = (EditText) findViewById(R.id.dsignup_edt_fullname);
		xEdtDMobileNo = (EditText) findViewById(R.id.dsignup_edt_mobileno);
		xEdtDUserName = (EditText) findViewById(R.id.dsignup_edt_username);
		xEdtDPassword = (EditText) findViewById(R.id.dsignup_edt_password);
		xBtnDoctorSignUp.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				if (TextUtils.isEmpty(xEdtDFullName.getText().toString())) {
					xEdtDFullName.setError("Full Name Required");
					return;
				}

				if (!isValidMobile(xEdtDMobileNo.getText().toString())) {
					Toast.makeText(getApplicationContext(),
							"Mobile Number Must Be 10 Digit",
							Toast.LENGTH_SHORT).show();
					xEdtDMobileNo.requestFocus();
					return;
				}
				if (TextUtils.isEmpty(xEdtDUserName.getText().toString())) {
					xEdtDUserName.setError("User Name Required");
					return;
				}

				if (TextUtils.isEmpty(xEdtDPassword.getText().toString())) {
					xEdtDPassword.setError("Password Required");
					return;
				}
				fn_DataProcess();

			}

		});

	}

	private boolean isValidMobile(String phone2) {
		boolean check;
		if (phone2.length() < 10 || phone2.length() > 12) {
			check = false;
			xEdtDMobileNo.setError("Not Valid Number");
		} else {
			check = true;
		}
		return check;
	}
	
	public void fn_DataProcess() {
		ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		xDFullName = xEdtDFullName.getText().toString();
		xDMobileNo = xEdtDMobileNo.getText().toString();
		xDUserName = xEdtDUserName.getText().toString();
		xDPassword = xEdtDPassword.getText().toString();

		nameValuePairs.add(new BasicNameValuePair("fullname", xDFullName));
		nameValuePairs.add(new BasicNameValuePair("mobileno", xDMobileNo));
		nameValuePairs.add(new BasicNameValuePair("username", xDUserName));
		nameValuePairs.add(new BasicNameValuePair("password", xDPassword));
		try {
			HttpClient httpclient = new DefaultHttpClient();
			HttpPost httppost = new HttpPost(
					"http://surendertraders.com/android_connect/doctor_insert.php");
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
				
				//Set name and email in global/application context
				globalVariable.setDUserName(xEdtDUserName.getText().toString().trim());
				xIntPatOtpVerification = new Intent(getApplicationContext(),
						Doctor_Login.class);
				startActivity(xIntPatOtpVerification);
			} else {
				Toast.makeText(getBaseContext(), "Sorry, Try Again/Please Change Username Or Check Mobile No",
						Toast.LENGTH_LONG).show();
			}
		} catch (Exception e) {
			Log.e("Fail 3", e.toString());
		}
	}
	public void fn_DataClear() {
		xEdtDFullName.setText("");
		xEdtDMobileNo.setText("");
		xEdtDUserName.setText("");
		xEdtDPassword.setText("");
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}
