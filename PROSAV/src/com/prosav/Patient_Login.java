package com.prosav;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.text.TextUtils;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Patient_Login extends Activity {
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

	String xFullName;
	InputStream is = null;
	String result = null;
	String line = null;
	int code;

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

				try {
					xLogin.setEnabled(false);
					String username = xEdtUserName.getText().toString();
					String password = xEdtPassword.getText().toString();
					String link = "http://surendertraders.com/android_connect/loginpost.php";
					String data = URLEncoder.encode("username", "UTF-8") + "="
							+ URLEncoder.encode(username, "UTF-8");
					data += "&" + URLEncoder.encode("password", "UTF-8") + "="
							+ URLEncoder.encode(password, "UTF-8");
     				URL url = new URL(link);
					URLConnection conn = url.openConnection();
					conn.setDoOutput(true);
					OutputStreamWriter wr = new OutputStreamWriter(conn
							.getOutputStream());
					wr.write(data);
					wr.flush();
					BufferedReader reader = new BufferedReader(
							new InputStreamReader(conn.getInputStream()));

					StringBuilder sb = new StringBuilder();
					String line = null;

					// Read Server Response
					while ((line = reader.readLine()) != null) {
						sb.append(line);
						break;
					}
					xFullName = sb.toString();

					if (xFullName != null && !xFullName.isEmpty()) {
						xIntPatientLoginSuccess = new Intent(
								getApplicationContext(),
								Patient_Login_Succcess.class);
						startActivity(xIntPatientLoginSuccess);
						Toast.makeText(Patient_Login.this,
								"Welcome " + xFullName, Toast.LENGTH_LONG)
								.show();
					} else {
						Toast.makeText(Patient_Login.this,
								"Invalid UserName/Password", Toast.LENGTH_LONG)
								.show();
					}

					// return sb.toString();
				} catch (Exception e) {
					Toast.makeText(Patient_Login.this, "error" + e.toString(),
							Toast.LENGTH_LONG).show();

				}
				final GlobalClass globalVariable = (GlobalClass) getApplicationContext();
				globalVariable.setUserName(xEdtUserName.getText().toString());
				xLogin.setEnabled(true);
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

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
