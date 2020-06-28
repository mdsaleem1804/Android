package com.example.ag_and_012_school;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MarkView extends Activity {
	Spinner xSpnStudentNameForMarkEntry;
	String xSelectedStudentUser;
	DataBaseConnection mCon;
	private SQLiteDatabase db;
	private Cursor xSCursor,xCursor;
	TextView xName,xRollNo,xRegNo;
	EditText xEdtSubject1,xEdtSubject2,xEdtSubject3,xEdtSubject4,xEdtSubject5,xEdtCgpa;
	int xMarkPoint=0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.markview);
		
		Bundle b = new Bundle();
		b = getIntent().getExtras();
		openDatabase();
		xSelectedStudentUser = b.getString("studentusername");
		mCon = new DataBaseConnection(this);
		
		
		//xEdtStudentName = (EditText) findViewById(R.id.fedtStudentName);
		

		xName = (TextView) findViewById(R.id.fTxtName);
		xRollNo = (TextView) findViewById(R.id.fTxtRollNo);
		xRegNo = (TextView) findViewById(R.id.fTxtRegNo);
		
		xEdtSubject1 = (EditText) findViewById(R.id.fedtSubject1);
		xEdtSubject2 = (EditText) findViewById(R.id.fedtSubject2);
		xEdtSubject3 = (EditText) findViewById(R.id.fedtSubject3);
		xEdtSubject4 = (EditText) findViewById(R.id.fedtSubject4);
		xEdtSubject5 = (EditText) findViewById(R.id.fedtSubject5);
		xEdtCgpa = (EditText) findViewById(R.id.fedtCgpa);
		
		//xEdtStudentName.setText(xSelectedStudentUser);
		
		String xQry = "Select * from studentregistration where studentname='"+xSelectedStudentUser+"'";
		xSCursor = db.rawQuery(xQry, null);
		xName.setText(xSCursor.getString(3));
		xRollNo.setText(xSCursor.getString(4));
		xRegNo.setText(xSCursor.getString(5));
		String xQry1 = "Select * from mark where studentname='"+xSelectedStudentUser+"'";
		xCursor = db.rawQuery(xQry1, null);
		if(xCursor.getCount()>0)
		{
		xCursor.moveToFirst();
		try
		{
		showRecords();
		}
		catch(Exception e)
		{
			String xError=e.toString();
		}
		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	protected void openDatabase() {
		db = openOrCreateDatabase("ag_and_012_school.db",
				Context.MODE_PRIVATE, null);
	}
	
	protected void showRecords() {
	/*	String xGrade1=xCursor.getString(1);
		xEdtSubject1.setText(fn_GetGrade(xGrade1, 3));*/
		//xEdtSubject1.setText(fn_GetGrade(xCursor.getString(1)));
		xEdtSubject1.setText(fn_GetGrade(xCursor.getString(1),3));
		xEdtSubject2.setText(fn_GetGrade(xCursor.getString(2),3));
		xEdtSubject3.setText(fn_GetGrade(xCursor.getString(3),3));
		xEdtSubject4.setText(fn_GetGrade(xCursor.getString(4),4));
		xEdtSubject5.setText(fn_GetGrade(xCursor.getString(5),4));
		int xCgpaValue= xMarkPoint/18;
		xEdtCgpa.setText(String.valueOf(xCgpaValue));
		/*xEdtSubject2.setText(xCursor.getString(2));
		xEdtSubject3.setText(xCursor.getString(3));
		xEdtSubject4.setText(xCursor.getString(4));
		xEdtSubject5.setText(xCursor.getString(5));*/

	}
	public void savemarks(View v)
	{
		if (mCon.fn_InsertMarkEntry(xSpnStudentNameForMarkEntry.getSelectedItem().toString(),
				xEdtSubject1.getText().toString(), 
				xEdtSubject2.getText().toString(),
				xEdtSubject3.getText().toString(), 
				xEdtSubject4.getText().toString(),
				xEdtSubject5.getText().toString())){
			Toast.makeText(getApplicationContext(), "Mark Entered",
					1000).show();
			fn_ClearMarks();
	}
		
	}
	public void fn_ClearMarks()
	{
		xEdtSubject1.setText("");
		xEdtSubject2.setText("");
		xEdtSubject3.setText("");
		xEdtSubject4.setText("");
		xEdtSubject5.setText("");
	}
	
	public String fn_GetGrade(String xMarks,int xGradePoint)
	{
		String xReturnMarks="";
		int xMarkGrade=Integer.parseInt(xMarks);
		 xMarkPoint+=xMarkGrade*xGradePoint;
		if(xMarkGrade>50 && xMarkGrade<60)
		{
			xReturnMarks="E";
		}
		if(xMarkGrade>60 && xMarkGrade<70)
		{
			xReturnMarks="D";
		}
		if(xMarkGrade>70 && xMarkGrade<80)
		{
			xReturnMarks="C";
		}
		if(xMarkGrade>80 && xMarkGrade<90)
		{
			xReturnMarks="B";
		}
		if(xMarkGrade>90 && xMarkGrade<100)
		{
			xReturnMarks="A";
		}
		if(xMarkGrade>0 && xMarkGrade<50)
		{
			xReturnMarks="FAIL";
		}
		return xReturnMarks;
	}
}