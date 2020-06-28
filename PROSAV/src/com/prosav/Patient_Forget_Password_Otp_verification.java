
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
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Patient_Forget_Password_Otp_verification extends Activity {
	EditText xEdtOtpVer;
	Button xBtnOtpConfirm;
	String xOtpVer;
	InputStream is = null;
	String result = null;
	String line = null;
	int code;
	GlobalClass globalVariable;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.f_patient_otp_verification);
		globalVariable = (GlobalClass) getApplicationContext();
		xEdtOtpVer = (EditText) findViewById(R.id.potpver_edt_otp);
		xBtnOtpConfirm = (Button) findViewById(R.id.potpver_btn_confirmcode);
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
				.permitAll().build();

		StrictMode.setThreadPolicy(policy);
		xBtnOtpConfirm.setOnClickListener(new OnClickListener() {
			ProgressDialog dialog = null;

			@Override
			public void onClick(View arg0) {
				dialog = ProgressDialog.show(Patient_Forget_Password_Otp_verification.this, "",
						"Validating OTP...", true);
				new Thread(new Runnable() {
					public void run() {
						fn_DataProcess();
					}
				}).start();
				fn_DataProcess();

			}

		});
	}

	public void fn_DataProcess() {
		ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		xOtpVer = xEdtOtpVer.getText().toString();
		nameValuePairs.add(new BasicNameValuePair("otpver", xOtpVer));
		nameValuePairs.add(new BasicNameValuePair("username", globalVariable.getMobileno().toString()));

		try {
			HttpClient httpclient = new DefaultHttpClient();
			HttpPost httppost = new HttpPost(
					"http://10.0.2.2/android_connect/patient_signup_update_otp.php");
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
				Toast.makeText(getBaseContext(), "Verified", Toast.LENGTH_SHORT)
						.show();
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



	public void fn_DataClear() {
		xEdtOtpVer.setText("");

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}
