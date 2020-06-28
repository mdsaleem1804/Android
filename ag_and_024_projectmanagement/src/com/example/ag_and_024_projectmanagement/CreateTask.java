package com.example.ag_and_024_projectmanagement;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class CreateTask extends Activity {
	DataBaseConnection mCon;
	private SQLiteDatabase db = null;
	Spinner xSpnProject;
	String xSelectedProject;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.createtask);
		mCon = new DataBaseConnection(this);
		db = mCon.getWritableDatabase();
		xSpnProject = (Spinner) findViewById(R.id.fSpnProjName);
		LoadProjects();
	}
	
	public void LoadProjects() {
		ArrayList<String> my_array = new ArrayList<String>();
		my_array = getProject();

		@SuppressWarnings("rawtypes")
		ArrayAdapter my_Adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, my_array);
		xSpnProject.setAdapter(my_Adapter);
		xSpnProject.setOnItemSelectedListener(new OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				xSelectedProject = xSpnProject.getSelectedItem().toString();

			}

			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub

			}

		});

	}

	@SuppressLint("ShowToast")
	public ArrayList<String> getProject() {

		ArrayList<String> my_array = new ArrayList<String>();
		try {

			Cursor allrows = db.rawQuery("SELECT * FROM project",
					null);
			System.out.println("COUNT : " + allrows.getCount());

			if (allrows.moveToFirst()) {
				do {

					String NAME = allrows.getString(5);
					my_array.add(NAME);

				} while (allrows.moveToNext());
			}
			allrows.close();
			
		} catch (Exception e) {
			Toast.makeText(getApplicationContext(), "Error encountered.",
					Toast.LENGTH_LONG);
		}
		return my_array;
	}

}
