package com.example.ag_and_008_smallscale;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class VendorLogin extends Activity {
	DataBaseConnection mCon;
	EditText xEdtUserName, xEdtPassword;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.vendorlogin);
		mCon=new DataBaseConnection(this);
		xEdtUserName = (EditText) findViewById(R.id.fedtusername);
		xEdtPassword = (EditText) findViewById(R.id.fedtpasssword);
	}
	
	public void login(View v) {
		if(mCon.fn_GetDrivers(xEdtUserName.getText().toString(), xEdtPassword.getText().toString()))
		{	
			Intent intent = new Intent(VendorLogin.this,
					VendorMainPage.class);
			startActivity(intent);
		}
	}


}
