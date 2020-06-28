package com.example.ag_and_015_handwaeving;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class HomePage extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.homepage);
	}

	public void passwordcreation(View v) {
		finish();
		Intent intent = new Intent(HomePage.this, NameCreation.class);
		startActivity(intent);
	}

	public void configuration(View v) {
		finish();
		Intent intent = new Intent(HomePage.this, ListPasswordNames.class);
		startActivity(intent);
	}

	public void login(View v) {
		finish();
		Intent intent = new Intent(HomePage.this, Login.class);
		startActivity(intent);
	}

	public void handwaving(View v) {
		finish();
		Intent intent = new Intent(HomePage.this, Shaker.class);
		startActivity(intent);
	}

}
