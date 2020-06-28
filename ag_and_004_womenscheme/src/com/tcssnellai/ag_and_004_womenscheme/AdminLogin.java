package com.tcssnellai.ag_and_004_womenscheme;

import com.tcssnellai.ag_and_004_womenscheme.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AdminLogin extends Activity {

	EditText xEdtAdmUserName, xEdtAdmPassword;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.adminlogin);
		xEdtAdmUserName = (EditText) findViewById(R.id.fedtadminusername);
		xEdtAdmPassword = (EditText) findViewById(R.id.fedtadminpassword);
	}

	public void adminlogin(View v) {
		if (xEdtAdmUserName.getText().toString().equalsIgnoreCase("admin")
				&& xEdtAdmPassword.getText().toString()
						.equalsIgnoreCase("admin")) {
			Intent intent = new Intent(AdminLogin.this, AdminMainPage.class);
			startActivity(intent);
		} else {
			Toast.makeText(getApplicationContext(), "Password Mismatch", 1000)
					.show();
		}
	}

}
