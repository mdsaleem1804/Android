package com.example.ag_and_024_projectmanagement;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class CreateProject extends Activity {
	DataBaseConnection mCon;
	EditText xEdtProjName, xEdtProjDesc;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.createproject);
		mCon = new DataBaseConnection(this);
		xEdtProjName = (EditText) findViewById(R.id.fEdtProjectName);
		xEdtProjDesc = (EditText) findViewById(R.id.fEdtPDesc);
	}

	public void saveproject(View v) {

		mCon.insertProject(xEdtProjName.getText().toString(), xEdtProjDesc
				.getText().toString());

		Toast.makeText(getApplicationContext(),
				"Project Succesfully Added", 1000).show();

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
