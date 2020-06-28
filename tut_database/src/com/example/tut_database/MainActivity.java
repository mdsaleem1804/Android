package com.example.tut_database;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {

	Button xBtnInsert;
	SQLiteDatabase db;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		xBtnInsert = (Button) findViewById(R.id.btnInsert);
		db = openOrCreateDatabase("StudentDB", Context.MODE_PRIVATE, null);
		String xQry = "CREATE TABLE IF NOT EXISTS student(rollno Integer(2),name VARCHAR(5),marks VARCHAR);";
		try {
			db.execSQL(xQry);
		} catch (Exception e) {
			String xError = e.toString();
		}
	}

	public void Insert(View v) {
		int xRollNo = 1;
		String xName = "saleem";
		String xMarks = "25";
		try {
			String xQry = "INSERT INTO student VALUES(" + xRollNo + ",'"
					+ xName + "','" + xMarks + "')";
			db.execSQL(xQry);
		} catch (Exception e) {
			String xError = e.toString();
		}
	}

	public void Update(View v) {
		int xRollNo = 1;
		String xName = "pravin";
		String xMarks = "45";
		try {

			db.execSQL("UPDATE student SET name='" + xName + "',marks='"
					+ xMarks + "' WHERE rollno=" + xRollNo + "");

		} catch (Exception e) {
			String xError = e.toString();
		}
	}

	public void Delete(View v) {
		int xRollNo = 1;

		try {

			db.execSQL("DELETE from student  WHERE rollno=" + xRollNo + "");

		} catch (Exception e) {
			String xError = e.toString();
		}
	}

	public void Select(View v) {
		int xRollNo = 1;

		try {
			String xQry = "Select * from student  WHERE rollno=" + xRollNo;
			Cursor c = db.rawQuery(xQry, null);
			if(c.moveToFirst())
    		{
    		Toast.makeText(getApplicationContext(), c.getString(1), 1000).show();
    		}
		} catch (Exception e) {
			String xError = e.toString();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
