package com.example.ag_and_015_handwaeving;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainPage extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mainpage);

	}

	public void proceed(View v) {
		finish();
		Intent intent = new Intent(MainPage.this, DataManagement.class);
		startActivity(intent);
	}
}