package com.example.afr_and_005_unlock;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {
	Button xbtnRegistration, xbtnLogin, xBtnDoctorLogin;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initViews();
		loadData();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void initViews() {

		xbtnRegistration = (Button) findViewById(R.id.btnRegistration);
		xbtnLogin = (Button) findViewById(R.id.btnLogin);
		//xBtnDoctorLogin = (Button) findViewById(R.id.btnDoctorLogin);

	}

	private void loadData() {
		xbtnRegistration.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
				Intent intent = new Intent(MainActivity.this,
						registration.class);
				startActivity(intent);
				// overridePendingTransition(R.anim.right_in, R.anim.left_out);
			}
		});

		xbtnLogin.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
				Intent intent = new Intent(MainActivity.this, Login.class);
				startActivity(intent);
				// overridePendingTransition(R.anim.right_in, R.anim.left_out);
			}
		});

		/*xBtnDoctorLogin.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
				Intent intent = new Intent(MainActivity.this, DoctorLogin.class);
				startActivity(intent);
				// overridePendingTransition(R.anim.right_in, R.anim.left_out);
			}
		});*/

	}

}
