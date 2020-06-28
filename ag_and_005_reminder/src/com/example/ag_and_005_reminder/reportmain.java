package com.example.ag_and_005_reminder;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

public class reportmain extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.reportmain);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void listall(View v) {
		/*
		 * Intent intent = new Intent(reportmain.this,ListReminder.class);
		 * startActivity(intent);
		 */
		Intent intent = new Intent(reportmain.this, ListExpenses.class);
		startActivity(intent);
	}

	public void listdate(View v) {

		Intent intent = new Intent(reportmain.this, ListExpensesByDate.class);
		startActivity(intent);
	}

	public void listpayment(View v) {

		Intent intent = new Intent(reportmain.this,
				ListExpensesByPaymentMode.class);
		startActivity(intent);
	}

	public void listcategory(View v) {

		Intent intent = new Intent(reportmain.this,
				ListExpensesByCategory.class);
		startActivity(intent);
	}
}