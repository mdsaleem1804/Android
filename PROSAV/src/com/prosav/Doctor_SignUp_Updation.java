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
import android.widget.TableRow;
import android.widget.Toast;

public class Doctor_SignUp_Updation extends Activity {
	EditText xEdtDFullName, xEdtDAge, xEdtDSpecialist, xEdtDMobileNo,
			xEdtDLandlineno, xEdtDEmail, xEdtDAddress;
	Spinner xSpnDGender;
	String xDFullName, xDAge, xDMobileNo, xDLandlineNo, xDEmail, xDAddress,
			xSpecialist, xMedicalHistory;
	Button xBtnDSignUpUpdate;
	GlobalClass globalVariable;
	InputStream is = null;
	String result = null;
	String line = null;
	int code;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.f_doctor_signup_updation);
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
				.permitAll().build();

		StrictMode.setThreadPolicy(policy);
		try {
			xEdtDFullName = (EditText) findViewById(R.id.dsignup_updation_edt_name);
			xEdtDAge = (EditText) findViewById(R.id.dsignup_updation_edt_age);
			xEdtDSpecialist = (EditText) findViewById(R.id.dsignup_updation_edt_specialist);
			xEdtDMobileNo = (EditText) findViewById(R.id.dsignup_updation_edt_mobileno);
			xEdtDLandlineno = (EditText) findViewById(R.id.dsignup_updation_edt_landlineno);
			xEdtDEmail = (EditText) findViewById(R.id.dsignup_updation_edt_email);
			xEdtDAddress = (EditText) findViewById(R.id.dsignup_updation_edt_address);
			xBtnDSignUpUpdate = (Button) findViewById(R.id.dsignup_updation_btn_update);

			globalVariable = (GlobalClass) getApplicationContext();
			try {
				xEdtDFullName.setText(globalVariable.getDFullName().toString());
				xEdtDAge.setText(globalVariable.getDAge().toString());
				xEdtDSpecialist.setText(globalVariable.getDSpecialist()
						.toString());
				xEdtDMobileNo.setText(globalVariable.getDMobileno().toString());
				xEdtDLandlineno.setText(globalVariable.getDLandlineno()
						.toString());
				xEdtDEmail.setText(globalVariable.getDEmail().toString());
				xEdtDAddress.setText(globalVariable.getDAddress().toString());

			} catch (Exception e) {
				String xError = e.toString();
				Toast.makeText(getApplicationContext(), "Error" + xError,
						Toast.LENGTH_SHORT).show();
			}
			xBtnDSignUpUpdate.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View arg0) {
					if (TextUtils.isEmpty(xEdtDFullName.getText().toString())) {
						xEdtDFullName.setError("Full Name Required");
						return;
					}
					if (TextUtils.isEmpty(xEdtDAge.getText().toString())) {
						xEdtDAge.setError("Age Required");
						return;
					}

					if (!isValidMobile(xEdtDMobileNo.getText().toString())) {
						Toast.makeText(getApplicationContext(),
								"Mobile Number Must Be 10 Digit",
								Toast.LENGTH_SHORT).show();
						xEdtDMobileNo.requestFocus();
						return;
					}
					fn_DataProcess();

				}

			});
			xSpnDGender = (Spinner) findViewById(R.id.spn_d_gender);
			List<String> xGender = new ArrayList<String>();
			TableRow xActiveRow = (TableRow) findViewById(R.id.tblActiveRow);
			xActiveRow.setVisibility(View.INVISIBLE);
			xGender.add("Male");
			xGender.add("Female");

			ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
					android.R.layout.simple_spinner_item, xGender);

			dataAdapter
					.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

			xSpnDGender.setAdapter(dataAdapter);
		} catch (Exception e) {
			String xError = e.toString();
			Toast.makeText(getApplicationContext(), "Error" + xError,
					Toast.LENGTH_SHORT).show();
		}
	}

	public void fn_DataProcess() {
		ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		xDFullName = xEdtDFullName.getText().toString().trim();
		xDAge = xEdtDAge.getText().toString().trim();
		xDMobileNo = xEdtDMobileNo.getText().toString().trim();
		xDLandlineNo = xEdtDLandlineno.getText().toString().trim();
		xDEmail = xEdtDEmail.getText().toString().trim();
		xDAddress = xEdtDAddress.getText().toString().trim();
		xSpecialist = xEdtDSpecialist.getText().toString().trim();
		
		 nameValuePairs.add(new BasicNameValuePair("dusername", globalVariable
		 .getDUserName().toString().trim()));
		nameValuePairs.add(new BasicNameValuePair("dfullname", xDFullName));
		nameValuePairs.add(new BasicNameValuePair("dage", xDAge));
		nameValuePairs.add(new BasicNameValuePair("dmobileno", xDMobileNo));
		nameValuePairs.add(new BasicNameValuePair("dlandlineno", xDLandlineNo));
		nameValuePairs.add(new BasicNameValuePair("demail", xDEmail));
		nameValuePairs.add(new BasicNameValuePair("daddress", xDAddress));
		nameValuePairs.add(new BasicNameValuePair("dspecialist", xSpecialist));

		try {
			HttpClient httpclient = new DefaultHttpClient();
			HttpPost httppost = new HttpPost(
					"http://surendertraders.com/android_connect/doctor_signup_update.php");
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
				Intent xIntDoctorLoginSuccess = new Intent(
						getApplicationContext(), Doctor_Login_Succcess.class);
				startActivity(xIntDoctorLoginSuccess);
			} else {
				Toast.makeText(getBaseContext(), "Sorry, Try Again",
						Toast.LENGTH_LONG).show();
			}
		} catch (Exception e) {
			Log.e("Fail 3", e.toString());
		}
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

	public void fn_DataClear() {
		xEdtDFullName.setText("");
		xEdtDAge.setText("");
		xEdtDMobileNo.setText("");
		xEdtDLandlineno.setText("");
		xEdtDEmail.setText("");
		xEdtDAddress.setText("");
		xEdtDSpecialist.setText("");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}
