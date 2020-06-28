package com.example.and_af_qrcode;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomePage extends Activity{
	Button xBtnRegistration,xBtnLogin,xBtnAdminLogin;
	Intent xIntent;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.homepage);
		xBtnRegistration = (Button) findViewById(R.id.btnRegistration);
		xBtnLogin = (Button) findViewById(R.id.btnLogin);
		xBtnAdminLogin = (Button) findViewById(R.id.btnAdminLogin);


		xBtnRegistration.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					finish();
					xIntent = new Intent(HomePage.this,
							Registration.class);
					startActivity(xIntent);

				}
			});
			

		xBtnLogin.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					finish();
					xIntent = new Intent(HomePage.this,
							Login.class);
					startActivity(xIntent);

				}
			});

	}


}