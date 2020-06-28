package com.example.ag_and_011_smsencryption;

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
	
	public void sender(View v)
	{
		Intent intent = new Intent(HomePage.this,
				SenderLogin.class);
		startActivity(intent);
	}
}
