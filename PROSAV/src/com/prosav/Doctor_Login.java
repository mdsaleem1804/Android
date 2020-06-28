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

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.ConnectivityManager;
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

public class Doctor_Login extends Activity {
	TextView xTxtSignUp, xTxtForgetPwd;
	EditText xEdtDLUserName, xEdtDLPassword;
	Intent xIntDoctorSignUp, xIntDocForgetPwd, xIntDoctorLoginSuccess;
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

	Button xLogin;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.f_doctor_login);
		xTxtSignUp = (TextView) findViewById(R.id.dlog_txt_signup);
		xTxtForgetPwd = (TextView) findViewById(R.id.dlog_txt_forgetpassword);
		xLogin = (Button) findViewById(R.id.dlog_btn_login);
		xEdtDLUserName = (EditText) findViewById(R.id.dlog_edt_username);
		xEdtDLPassword = (EditText) findViewById(R.id.dlog_edt_password);
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
				.permitAll().build();

		StrictMode.setThreadPolicy(policy);

		xLogin.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				if (TextUtils.isEmpty(xEdtDLUserName.getText().toString())) {
					xEdtDLUserName.setError("User Name Required");
					return;
				}
				if (TextUtils.isEmpty(xEdtDLPassword.getText().toString())) {
					xEdtDLPassword.setError("Password Required");
					return;
				}
				/*if (!isInternetOn()) {
					Toast.makeText(Doctor_Login.this, "Check Connection",
							Toast.LENGTH_LONG).show();
				}*/
				try {
					xLogin.setBackgroundColor(Color.RED);
					xLogin.setEnabled(false);
					String username = xEdtDLUserName.getText().toString();
					String password = xEdtDLPassword.getText().toString();

					// String link =
					// "http://10.0.2.2/android_connect/doctorlogin.php";

					String link = "http://surendertraders.com/android_connect/doctorlogin.php";
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
						xIntDoctorLoginSuccess = new Intent(
								getApplicationContext(),
								Doctor_Login_Succcess.class);
						startActivity(xIntDoctorLoginSuccess);
						Toast.makeText(Doctor_Login.this,
								"Welcome " + xFullName, Toast.LENGTH_LONG)
								.show();
					} else {
						Toast.makeText(Doctor_Login.this,
								"Invalid UserName/Password", Toast.LENGTH_LONG)
								.show();
					}

					// return sb.toString();
				} catch (Exception e) {
					Toast.makeText(Doctor_Login.this, "Error" ,
							Toast.LENGTH_LONG).show();

				}
				// Calling Application class (see application tag in
				// AndroidManifest.xml)
				final GlobalClass globalVariable = (GlobalClass) getApplicationContext();

				// Set name and email in global/application context
				globalVariable
						.setDUserName(xEdtDLUserName.getText().toString());

				xLogin.setEnabled(true);
				xLogin.setBackgroundColor(Color.GREEN);
			}

		});
		xTxtSignUp.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

				xIntDoctorSignUp = new Intent(getApplicationContext(),
						Doctor_SignUp.class);
				startActivity(xIntDoctorSignUp);

			}

		});
		xTxtForgetPwd.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

				xIntDocForgetPwd = new Intent(getApplicationContext(),
						Doctor_Forget_Password.class);
				startActivity(xIntDocForgetPwd);

			}

		});
	}

	public final boolean isInternetOn() {

		// get Connectivity Manager object to check connection
		ConnectivityManager connec = (ConnectivityManager) getSystemService(getBaseContext().CONNECTIVITY_SERVICE);

		// Check for network connections
		if (connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.CONNECTED
				|| connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.CONNECTING
				|| connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.CONNECTING
				|| connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.CONNECTED) {

			// if connected with internet

			// Toast.makeText(this, " Connected ", Toast.LENGTH_LONG).show();
			return true;

		} else if (connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.DISCONNECTED
				|| connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.DISCONNECTED) {

			// Toast.makeText(this, " Not Connected ",
			// Toast.LENGTH_LONG).show();
			return false;
		}
		return false;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}
