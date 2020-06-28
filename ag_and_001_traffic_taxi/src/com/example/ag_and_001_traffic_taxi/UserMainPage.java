package com.example.ag_and_001_traffic_taxi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class UserMainPage extends Activity {
DataBaseConnection mCon;
private DbAdapter dbHelper;
EditText xEdtUserMobileNo;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.usermainpage);
		mCon=new DataBaseConnection(this);
		dbHelper = new DbAdapter(this);
		xEdtUserMobileNo=(EditText) findViewById(R.id.fedtUserPhoneNo);
		
	}
	public void proceed(View v)
	{
		if(xEdtUserMobileNo.getText().toString().length()!=10)
		{
			xEdtUserMobileNo.setError("Digit Should be 10");
			return;
		}
		mCon.insertUserDetails(xEdtUserMobileNo.getText().toString());
		Intent intent = new Intent(UserMainPage.this,
				ListView_User.class);
		intent.putExtra("usermobileno", xEdtUserMobileNo.getText().toString());
		startActivity(intent);
	}
}