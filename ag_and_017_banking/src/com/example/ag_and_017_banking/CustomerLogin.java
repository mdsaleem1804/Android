package com.example.ag_and_017_banking;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.telephony.gsm.SmsManager;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class CustomerLogin extends Activity {

	EditText xEdtUserName, xEdtPassword;
	DataBaseConnection mCon;
	int xLoginCount = 0;
	String xId,xMobileNo;
	private SQLiteDatabase db;
	private Cursor xCursor;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		mCon = new DataBaseConnection(this);
		xEdtUserName = (EditText) findViewById(R.id.fedtusername);
		xEdtPassword = (EditText) findViewById(R.id.fedtpasssword);
		openDatabase();
	}

	protected void showRecords() {
		xId = xCursor.getString(0);
		xMobileNo = xCursor.getString(11);
	}

	protected void openDatabase() {
		db = openOrCreateDatabase("accounts.db", Context.MODE_PRIVATE, null);
	}

	public void login(View v) {
		if(xEdtUserName.getText().toString().length()>=10)
		{
			String xQry = "Select * from accounts  where acno='"
					+ xEdtUserName.getText().toString() + "'";
			xCursor = db.rawQuery(xQry, null);
			xCursor.moveToFirst();
			showRecords();
			
		if (mCon.fn_GetLogin(xEdtUserName.getText().toString(), xEdtPassword
				.getText().toString())) {
			Intent intent = new Intent(CustomerLogin.this,
					UserUpdateAccount.class);
			intent.putExtra("accountid", xId);
			startActivity(intent);

		} else {
			if (xLoginCount == 2) {
				Toast.makeText(getApplicationContext(),
						"Password Wrong -One Chance Remaining", 1000).show();
			}
			if (xLoginCount == 1) {
				Toast.makeText(getApplicationContext(),
						"Password Wrong -Two Chance Remaining", 1000).show();
			}
			if (xLoginCount > 2) {
				
				try {
					SmsManager smsManager = SmsManager.getDefault();
					smsManager.sendTextMessage(xMobileNo, null, "LoginMultipleTimes", null, null);
					Toast.makeText(getApplicationContext(), "Message Sent",
							Toast.LENGTH_LONG).show();
				} catch (Exception ex) {
					Toast.makeText(getApplicationContext(), ex.getMessage().toString(),
							Toast.LENGTH_LONG).show();
					ex.printStackTrace();
				}
			}
			xLoginCount += 1;
		}
	}
		else
		{
			Toast.makeText(getApplicationContext(), "Please Enter 10 Digit AcNo",
					Toast.LENGTH_LONG).show();
	
		}
	
	}

}
