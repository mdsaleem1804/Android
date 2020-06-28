package com.prosav;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

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

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

@TargetApi(Build.VERSION_CODES.GINGERBREAD)
public class Doctor_Set_Status extends Activity {
	EditText xEdtDate;
	Button xBtnOff;
	String xDate;
	InputStream is = null;
	String result = null;
	String line = null;
	int code;

	private DatePickerDialog DatePickerDialog;

	private SimpleDateFormat dateFormatter;

	@TargetApi(Build.VERSION_CODES.GINGERBREAD)
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.f_doctor_status);

		xEdtDate = (EditText) findViewById(R.id.dstatus_edt_date);
		xBtnOff = (Button) findViewById(R.id.dstatus_btn_off);
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
				.permitAll().build();

		StrictMode.setThreadPolicy(policy);

		dateFormatter = new SimpleDateFormat("yyyy-M-dd", Locale.US);

		// xEdtRemDoctorName.setText(globalVariable.getDUserName().toString());
		Calendar newCalendar = Calendar.getInstance();
		DatePickerDialog = new DatePickerDialog(
				this,
				new OnDateSetListener() {

					public void onDateSet(DatePicker view, int year,
							int monthOfYear, int dayOfMonth) {
						Calendar newDate = Calendar.getInstance();
						newDate.set(year, monthOfYear, dayOfMonth);
						xEdtDate.setText(dateFormatter.format(newDate
								.getTime()));
					}

				}, newCalendar.get(Calendar.YEAR), newCalendar
						.get(Calendar.MONTH),
				newCalendar.get(Calendar.DAY_OF_MONTH));

		xBtnOff.setOnClickListener(new OnClickListener() {
			ProgressDialog dialog = null;

			@Override
			public void onClick(View arg0) {
				dialog = ProgressDialog.show(Doctor_Set_Status.this, "",
						"Set Status ...", true);
				new Thread(new Runnable() {
					public void run() {
						fn_DataProcess();
					}
				}).start();

			}

		});

		xEdtDate.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

				DatePickerDialog.show();

			}

		});
	}

	public void fn_DataProcess() {
		ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		xDate = xEdtDate.getText().toString();
		nameValuePairs.add(new BasicNameValuePair("date", xDate));
		nameValuePairs.add(new BasicNameValuePair("doctorid", "1"));

		try {
			HttpClient httpclient = new DefaultHttpClient();
			HttpPost httppost = new HttpPost(
					"http://surendertraders.com/android_connect/doctor_set_status.php");
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
				Toast.makeText(getBaseContext(), "Status Updated ",
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

	public void fn_DataClear() {
		xEdtDate.setText("");

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}
