package com.example.busapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class Adminaddfr extends Activity {

	ImageButton addbf1, addbf2;
	Intent addif1, addif2;
	
	EditText bustime, busdelaytime, busnumber;
	String btime, bdt, bn;

	DataBaseConnection db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_adminaddfr);

		db = new DataBaseConnection(this);
		addbf1 = (ImageButton) findViewById(R.id.adminadda);
		addbf2 = (ImageButton) findViewById(R.id.adminaddh);
		bustime = (EditText) findViewById(R.id.bustime);
		busdelaytime = (EditText) findViewById(R.id.busdelaytime);
		busnumber = (EditText) findViewById(R.id.busnumber);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.adminaddfr, menu);
		return true;
	}

	public void adminaddb(View v) {
		db.InsertBusFrom(bustime.getText().toString(), busdelaytime.getText()
				.toString());
		Toast.makeText(getApplicationContext(),
				"Bus Details are successfully added...", Toast.LENGTH_LONG)
				.show();
		addif1 = new Intent(getApplication(), MainActivity.class);
		startActivityForResult(addif1, 0);
	}

	public void addhome(View v) {
		Toast.makeText(getApplicationContext(), "Thank you...",
				Toast.LENGTH_SHORT).show();
		addif2 = new Intent(getApplication(), MainActivity.class);
		startActivityForResult(addif2, 0);
	}

	public void add(View v) {
		/*
		 * if(TextUtils.isEmpty(bustime,busdelaytime,busnumber.getText().toString
		 * ())) { bustime.setError("bustime Required");
		 * busdelaytime.setError("bustime Required");
		 * busnumber.setError("bustime Required"); return; }
		 * 
		 * db.insertfrom(bustime.getText().toString());
		 * db.insertfrom(busdelaytime.getText().toString());
		 * db.insertfrom(busnumber.getText().toString());
		 */
		
		Toast.makeText(getApplicationContext(), "Inserted Successfully", 1000)
				.show();
	}

}
