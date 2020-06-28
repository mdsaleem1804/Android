package com.prosav;

import java.io.InputStream;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.Button;
import android.widget.EditText;

public class Doctor_SignUp_Otp_Verification extends Activity {
	EditText xEdtOtpVer;
	Button xBtnOtpConfirm;
	String xOtpVer;
	InputStream is = null;
	String result = null;
	String line = null;
	int code;
	GlobalClass globalVariable;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.f_doctor_otp_verification);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}
