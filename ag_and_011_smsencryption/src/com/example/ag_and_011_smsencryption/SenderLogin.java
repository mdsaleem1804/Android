package com.example.ag_and_011_smsencryption;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class SenderLogin extends Activity {

	EditText xEdtUserName, xEdtPassword;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.senderlogin);
		xEdtUserName = (EditText) findViewById(R.id.fedtusername);
		xEdtPassword = (EditText) findViewById(R.id.fedtpasssword);
	}
	
	public void login(View v) {
		if ((xEdtUserName.getText().toString().equalsIgnoreCase("sender"))
				&& (xEdtPassword.getText().toString().equalsIgnoreCase("sender"))) {
			Intent intent = new Intent(SenderLogin.this,
					Sender.class);
			startActivity(intent);
		}
	}


}
