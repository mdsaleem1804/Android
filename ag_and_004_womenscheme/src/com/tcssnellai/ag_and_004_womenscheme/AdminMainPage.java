package com.tcssnellai.ag_and_004_womenscheme;

import com.tcssnellai.ag_and_004_womenscheme.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class AdminMainPage extends Activity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.admimainpage);
	}
	public void addscheme(View v)
	{
		Intent intent = new Intent(AdminMainPage.this, AdminSchemeEntry.class);
		startActivity(intent);
	}
	public void viewscheme(View v)
	{
		Intent intent = new Intent(AdminMainPage.this, ListSchemes.class);
		intent.putExtra("formname", "AdminMainPage");
		startActivity(intent);
	}

}
