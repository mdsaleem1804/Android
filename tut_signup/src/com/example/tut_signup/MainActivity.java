package com.example.tut_signup;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.app.Activity;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;



public class MainActivity extends Activity {

EditText xFullName,xEmail,xUserName,xPassword,XCPassword;
	SQLiteDatabase db;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		xFullName=(EditText)findViewById(R.id.editText1);
		xEmail=(EditText)findViewById(R.id.editText2);
		xUserName=(EditText)findViewById(R.id.editText3);
		xPassword=(EditText)findViewById(R.id.editText4);
		XCPassword=(EditText)findViewById(R.id.editText5);
		db = openOrCreateDatabase("sadak", Context.MODE_PRIVATE, null);

		String xQry = "CREATE TABLE IF NOT EXISTS signup(fullname VARCHAR(50),email varchar(50),username VARCHAR(20),password VARCHAR(50));";
		try {
			db.execSQL(xQry);
		} catch (Exception e) {
			String xError = e.toString();
		}
	}
	public void signup(View v)
	{
		if (TextUtils.isEmpty(xFullName.getText().toString())) {
			xFullName.setError("Full Name Required");
			xFullName.requestFocus();
			return;
		}
		if (!isEmailValid(xEmail.getText().toString())) {
			Toast.makeText(getApplicationContext(),
					"Not a Valid Email Id", Toast.LENGTH_SHORT).show();
			xEmail.requestFocus();
			return;

		}

		if(xPassword.getText().toString().equalsIgnoreCase(XCPassword.getText().toString()))
		{
			String xQry = "INSERT INTO signup VALUES('" + xFullName.getText().toString() + "','"
					+ xEmail.getText().toString() + "','" + xUserName.getText().toString() + "','" + xPassword.getText().toString() + "')";
			db.execSQL(xQry);
		}
		else
		{
			XCPassword.setError("Not Matched");
			XCPassword.requestFocus();
			return;
		}
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

}
