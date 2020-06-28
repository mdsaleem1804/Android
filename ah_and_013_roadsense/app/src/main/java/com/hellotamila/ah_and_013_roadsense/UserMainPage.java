package com.hellotamila.ah_and_013_roadsense;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class UserMainPage extends Activity {
DataBaseConnection mCon;
private DbAdapter dbHelper;
EditText xEdtUserMobileNo,xEdtUserPlace;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.usermainpage);
		mCon=new DataBaseConnection(this);
		dbHelper = new DbAdapter(this);
		xEdtUserMobileNo=(EditText) findViewById(R.id.fedtUserPhoneNo);
		xEdtUserPlace=(EditText) findViewById(R.id.fEdtUserPlace);
		
	}
	public void proceed(View v)
	{
		if(xEdtUserMobileNo.getText().toString().length()!=10)
		{
			xEdtUserMobileNo.setError("Digit Should be 10");
			return;
		}
		mCon.insertUserDetails(xEdtUserMobileNo.getText().toString(),xEdtUserPlace.getText().toString());
/*		Intent intent = new Intent(UserMainPage.this,
				ListView_User.class);*/
		Intent intent = new Intent(UserMainPage.this,
				UserMainPage1.class);
		intent.putExtra("usermobileno", xEdtUserMobileNo.getText().toString());
		intent.putExtra("userplace", xEdtUserPlace.getText().toString());
		
		startActivity(intent);
	}
}