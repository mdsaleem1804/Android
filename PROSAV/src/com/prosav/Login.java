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
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends Activity {
	TextView xTxtSignUp, xTxtForgetPwd;
	Intent xIntPatientSignUp, xIntPatientForgetPwd, xIntPatientLoginSuccess;
	EditText xEdtUserName, xEdtPassword;
	Button xLogin;
	byte[] data;
	HttpPost httppost;
	StringBuffer buffer;
	HttpResponse response;
	HttpClient httpclient;
	InputStream inputStream;
	SharedPreferences app_preferences;
	List<NameValuePair> nameValuePairs;
	CheckBox check;

	String xUserName;
	ProgressDialog dialog = null;

	InputStream is = null;
	String result = null;
	String line = null;
	int code;

	private ProgressDialog pDialog;
	JSONParser jsonParser = new JSONParser();
	private static final String LOGIN_URL = "http://tcssnellai.com/android_connect/check.php";

	private static final String TAG_SUCCESS = "success";
	private static final String TAG_MESSAGE = "message";

	@TargetApi(Build.VERSION_CODES.GINGERBREAD)
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.f_patient_login);
		xTxtSignUp = (TextView) findViewById(R.id.plog_txt_signup);
		xTxtForgetPwd = (TextView) findViewById(R.id.plog_txt_forgetpassword);
		xLogin = (Button) findViewById(R.id.plog_btn_login);
		xEdtUserName = (EditText) findViewById(R.id.plog_edt_username);
		xEdtPassword = (EditText) findViewById(R.id.plog_edt_password);
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
				.permitAll().build();

		StrictMode.setThreadPolicy(policy);

		xLogin.setOnClickListener(new OnClickListener() {

			@TargetApi(Build.VERSION_CODES.GINGERBREAD)
			@SuppressLint("NewApi")
			@Override
			public void onClick(View arg0) {
				if (TextUtils.isEmpty(xEdtUserName.getText().toString())) {
					xEdtUserName.setError("User Name Required");
					return;
				}
				if (TextUtils.isEmpty(xEdtPassword.getText().toString())) {
					xEdtPassword.setError("Password Required");
					return;
				}
				//dialog = ProgressDialog.show(Login.this, "",
					//	"Validating user...", true);

				// fn_DataProcess();
				new AttemptLogin().execute();

			}

		});
		xTxtSignUp.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

				xIntPatientSignUp = new Intent(getApplicationContext(),
						Patient_SignUp.class);
				startActivity(xIntPatientSignUp);

			}

		});

		xTxtForgetPwd.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

				xIntPatientForgetPwd = new Intent(getApplicationContext(),
						Patient_Forget_Password.class);
				startActivity(xIntPatientForgetPwd);

			}

		});

	}

	class AttemptLogin extends AsyncTask<String, String, String> {
		boolean failure = false;

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(Login.this);
			pDialog.setMessage("Attempting for login...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(true);
			pDialog.show();
		}

		@Override
		protected String doInBackground(String... args) {
			int success;
			String username = xEdtUserName.getText().toString();
			String password = xEdtPassword.getText().toString();
			try {
				List<NameValuePair> params = new ArrayList<NameValuePair>();
				params.add(new BasicNameValuePair("username", username));
				params.add(new BasicNameValuePair("password", password));
				JSONObject json = jsonParser.makeHttpRequest(LOGIN_URL, "POST",
						params);

				success = json.getInt(TAG_SUCCESS);
				if (success == 1) {
					Intent ii = new Intent(Login.this,
							Patient_Login_Succcess.class);
					finish();
					startActivity(ii);
					return json.getString(TAG_MESSAGE);
				} else {
					return json.getString(TAG_MESSAGE);
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
			return null;
		}

		protected void onPostExecute(String message) {
			pDialog.dismiss();
			if (message != null) {
				Toast.makeText(Login.this, message, Toast.LENGTH_LONG).show();
			}

		}
	}

	void login() {
		try {

			httpclient = new DefaultHttpClient();
			httppost = new HttpPost(
					"http://tcssnellai.com/android_connect/check.php"); // make
																		// sure
																		// the
																		// url
																		// is
																		// correct.
			// add your data
			nameValuePairs = new ArrayList<NameValuePair>(2);
			// Always use the same variable name for posting i.e the android
			// side variable name and php side variable name should be similar,
			nameValuePairs.add(new BasicNameValuePair("username", xEdtUserName
					.getText().toString().trim())); // $Edittext_value =
													// $_POST['Edittext_value'];
			nameValuePairs.add(new BasicNameValuePair("password", xEdtPassword
					.getText().toString().trim()));
			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
			// Execute HTTP Post Request
			response = httpclient.execute(httppost);
			response.getEntity().consumeContent();
			ResponseHandler<String> responseHandler = new BasicResponseHandler();
			final String response = httpclient.execute(httppost,
					responseHandler);
			System.out.println("Response : " + response);
			runOnUiThread(new Runnable() {
				public void run() {
					// tv.setText("Response from PHP : " + response);
					// Calling Application class (see application tag in
					// AndroidManifest.xml)
					final GlobalClass globalVariable = (GlobalClass) getApplicationContext();

					// Set name and email in global/application context
					globalVariable.setUserName(xEdtUserName.getText()
							.toString().trim());

				}
			});

			if (response.equalsIgnoreCase("User Found")) {
				runOnUiThread(new Runnable() {
					public void run() {
						Toast.makeText(Login.this,
								"Login Success", Toast.LENGTH_SHORT).show();
					}
				});

				startActivity(new Intent(Login.this,
						Patient_Login_Succcess.class));
				dialog.dismiss();
			} else {
				showAlert();
			}

		} catch (Exception e) {
			dialog.dismiss();
			System.out.println("Exception : " + e.getMessage());
		}
	}

	public void showAlert() {
		Login.this.runOnUiThread(new Runnable() {
			public void run() {
				AlertDialog.Builder builder = new AlertDialog.Builder(
						Login.this);
				builder.setTitle("Login Error.");
				builder.setMessage("User not Found.")
						.setCancelable(false)
						.setPositiveButton("OK",
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,
											int id) {
									}
								});
				AlertDialog alert = builder.create();
				alert.show();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void fn_DataProcess() {
		nameValuePairs = new ArrayList<NameValuePair>(2);
		// Always use the same variable name for posting i.e the android side
		// variable name and php side variable name should be similar,
		nameValuePairs.add(new BasicNameValuePair("username", xEdtUserName
				.getText().toString().trim())); // $Edittext_value =
												// $_POST['Edittext_value'];
		nameValuePairs.add(new BasicNameValuePair("password", xEdtPassword
				.getText().toString().trim()));
		try {
			HttpClient httpclient = new DefaultHttpClient();
			HttpPost httppost = new HttpPost(
					"http://tcssnellai.com/android_connect/check.php");
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
				Toast.makeText(getBaseContext(), "Succesfully",
						Toast.LENGTH_SHORT).show();
				// Calling Application class (see application tag in
				// AndroidManifest.xml)
				final GlobalClass globalVariable = (GlobalClass) getApplicationContext();

				// Set name and email in global/application context
				globalVariable.setUserName(xEdtUserName.getText().toString()
						.trim());
				// fn_DataClear();
				// final GlobalClass globalVariable = (GlobalClass)
				// getApplicationContext();

				// Set name and email in global/application context
				// globalVariable.setUserName(xEdtUserName.getText().toString().trim());
				// xIntPatOtpVerification = new Intent(getApplicationContext(),
				// /Patient_SignUp_Otp_Verification.class);
				// startActivity(xIntPatOtpVerification);

				startActivity(new Intent(Login.this,
						Patient_Login_Succcess.class));
				// dialog.dismiss();
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
