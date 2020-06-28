package com.example.ag_and_012_school;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

public class AttendenceView extends Activity {
	EditText xEdtStudentName,xEdtFromDate,xEdtToDate;
	DatePickerDialog.OnDateSetListener xDateFrom,xDateTo;
	String xSelectedStudentUser;
	DataBaseConnection mCon;
	private SQLiteDatabase db;
	Calendar myCalendar;
	Cursor xCursor;
	 Button button;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.attendenceview);
		mCon = new DataBaseConnection(this);
		xEdtStudentName= (EditText) findViewById(R.id.fedtStudentNameForAttend);
		xEdtFromDate = (EditText) findViewById(R.id.fedt_FromDate);
		xEdtToDate = (EditText) findViewById(R.id.fedt_ToDate);
		myCalendar = Calendar.getInstance();
		  button = (Button) findViewById(R.id.fbtn_GetPercentage);
	
		Bundle b = new Bundle();
		b = getIntent().getExtras();
		openDatabase();
		xSelectedStudentUser = b.getString("studentusername");
		
		//xEdtStudentName.setText(xSelectedStudentUser);
		String xQry = "Select * from studentregistration where username='"+xSelectedStudentUser+"'";
		xCursor = db.rawQuery(xQry, null);
		if(xCursor.getCount()>0)
		{
		xCursor.moveToFirst();
		try
		{
			xEdtStudentName.setText(xCursor.getString(0));
		//showRecords();
		}
		catch(Exception e)
		{
			String xError=e.toString();
		}
		}
		
		mCon = new DataBaseConnection(this);
		
		xDateFrom = new DatePickerDialog.OnDateSetListener() {

			@Override
			public void onDateSet(DatePicker view, int year, int monthOfYear,
					int dayOfMonth) {
				// TODO Auto-generated method stub
				myCalendar.set(Calendar.YEAR, year);
				myCalendar.set(Calendar.MONTH, monthOfYear);
				myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
				updateLabelforFromDate();
			}

		};
		
		xDateTo = new DatePickerDialog.OnDateSetListener() {

			@Override
			public void onDateSet(DatePicker view, int year, int monthOfYear,
					int dayOfMonth) {
				// TODO Auto-generated method stub
				myCalendar.set(Calendar.YEAR, year);
				myCalendar.set(Calendar.MONTH, monthOfYear);
				myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
				updateLabelforToDate();
			}

		};
		
		xEdtFromDate.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				new DatePickerDialog(AttendenceView.this, xDateFrom, myCalendar
						.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
						myCalendar.get(Calendar.DAY_OF_MONTH)).show();
			}
		});
		
		xEdtToDate.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				new DatePickerDialog(AttendenceView.this, xDateTo, myCalendar
						.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
						myCalendar.get(Calendar.DAY_OF_MONTH)).show();
			}
		});
	    button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	try
        		{
            	String xQry = "Select * from attendence where studentname='"+xEdtStudentName.getText().toString()+"' and " +
        				" date  BETWEEN '"+xEdtFromDate.getText().toString()+"' AND '"+xEdtToDate.getText().toString()+"' and status = 'Present'";
        		xCursor = db.rawQuery(xQry, null);
        		int xCount=xCursor.getCount();
        		int xAttendencePercentage=(xCount/30)*100;
        		Toast.makeText(getApplicationContext(), String.valueOf(xAttendencePercentage), 1000).show();
        	
        		}
        		catch(Exception e)
        		{
        			String xError=e.toString();
        		}
            }
          });
  }

	
	protected void openDatabase() {
		db = openOrCreateDatabase("ag_and_012_school.db",
				Context.MODE_PRIVATE, null);
	}
	
	
	private void updateLabelforFromDate() {

		String myFormat = "MM/dd/yy"; // In which you need put here
		SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

		xEdtFromDate.setText(sdf.format(myCalendar.getTime()));
	}

	
	private void updateLabelforToDate() {

		String myFormat = "MM/dd/yy"; // In which you need put here
		SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

		xEdtToDate.setText(sdf.format(myCalendar.getTime()));
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}


}