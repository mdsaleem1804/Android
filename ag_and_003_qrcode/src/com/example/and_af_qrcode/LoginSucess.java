package com.example.and_af_qrcode;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class LoginSucess extends Activity {

	// Button xBtnStorageLevel;
	Intent xIntentStorageLevelForm;
	DBhelper mydb;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.loginsuccess);
		mydb = new DBhelper(this);
		// xBtnStorageLevel = (Button) findViewById(R.id.fbtnstoragelevel);
	}

	public void twostoragelevel(View v) {

		xIntentStorageLevelForm = new Intent(LoginSucess.this,
				StorageLevel.class);
		startActivity(xIntentStorageLevelForm);
	}
}
