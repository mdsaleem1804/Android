package com.example.ag_and_001_traffic_taxi;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends Activity {
	DataBaseConnection mCon;
	EditText xEdtMobileNo;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register);
		mCon = new DataBaseConnection(this);
		xEdtMobileNo = (EditText) findViewById(R.id.fedtalertmobileno);

	}

	public void setnumber(View v) {
		if (xEdtMobileNo.getText().toString().matches("")) {
			Toast.makeText(this, "You did not enter a Mobile No",
					Toast.LENGTH_SHORT).show();
			return;
		}
		if(xEdtMobileNo.getText().toString().length() <10)
		{
			xEdtMobileNo.setError(" No Must be Ten Digits");
			return;
		}
		if (mCon.insertAlertRecieverDetails(xEdtMobileNo.getText().toString())) {
			Toast.makeText(getApplicationContext(), "Inserted", 1000).show();
		}
	}

}
