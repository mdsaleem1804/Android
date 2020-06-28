package com.example.ag_and_012_school;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class AttendenceEntry extends Activity {
	String[] xStatus = { "Present", "Absent" };
	Spinner xSpnStatus, xSpnStudentForAttendence;
	String xSelectedStatus;
	Calendar myCalendar;
	EditText xEdtDate;
	DatePickerDialog.OnDateSetListener date;
	String xSelectedStudent;
	DataBaseConnection mCon;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.attendenceentry);
		xSpnStatus = (Spinner) findViewById(R.id.fspn_status);
		xSpnStudentForAttendence = (Spinner) findViewById(R.id.fspn_StudentForAttendence);
		mCon = new DataBaseConnection(this);
		LoadStatus();
		LoadStudents();
		xEdtDate = (EditText) findViewById(R.id.fedtDate);
		myCalendar = Calendar.getInstance();
		date = new DatePickerDialog.OnDateSetListener() {

			@Override
			public void onDateSet(DatePicker view, int year, int monthOfYear,
					int dayOfMonth) {
				// TODO Auto-generated method stub
				myCalendar.set(Calendar.YEAR, year);
				myCalendar.set(Calendar.MONTH, monthOfYear);
				myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
				updateLabel();
			}

		};
		xEdtDate.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				new DatePickerDialog(AttendenceEntry.this, date, myCalendar
						.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
						myCalendar.get(Calendar.DAY_OF_MONTH)).show();
			}
		});

	}

	private void updateLabel() {

		String myFormat = "MM/dd/yy"; // In which you need put here
		SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

		xEdtDate.setText(sdf.format(myCalendar.getTime()));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void LoadStudents() {
		ArrayList<String> my_array = new ArrayList<String>();
		my_array = mCon.fn_GetStudents();

		@SuppressWarnings("rawtypes")
		ArrayAdapter my_Adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, my_array);
		xSpnStudentForAttendence.setAdapter(my_Adapter);
		xSpnStudentForAttendence
				.setOnItemSelectedListener(new OnItemSelectedListener() {
					public void onItemSelected(AdapterView<?> parent,
							View view, int position, long id) {
						xSelectedStudent = xSpnStudentForAttendence
								.getSelectedItem().toString();

					}

					public void onNothingSelected(AdapterView<?> arg0) {
						// TODO Auto-generated method stub

					}

				});

	}

	public void LoadStatus() {

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, xStatus);
		xSpnStatus.setAdapter(adapter);
		xSpnStatus
				.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
					@Override
					public void onItemSelected(AdapterView<?> arg0, View arg1,
							int arg2, long arg3) {
						int position = xSpnStatus.getSelectedItemPosition();
						Toast.makeText(getApplicationContext(),
								"You have selected " + xStatus[+position],
								Toast.LENGTH_LONG).show();
						xSelectedStatus = xSpnStatus.getSelectedItem()
								.toString();
						// TODO Auto-generated method stub
					}

					@Override
					public void onNothingSelected(AdapterView<?> arg0) {
						// TODO Auto-generated method stub
					}
				});
	}

	public void saveattendence(View v) {
		if (mCon.fn_GetStudentAttenWithDate(xSpnStudentForAttendence
				.getSelectedItem().toString(), xEdtDate.getText().toString())) {
			Toast.makeText(getApplicationContext(), "Attendence Already Marked for this student",
					1000).show();
		} else {

			if (mCon.fn_InsertAttendenceEntry(xSpnStudentForAttendence
					.getSelectedItem().toString(), xEdtDate.getText()
					.toString(), xSpnStatus.getSelectedItem().toString())) {
				Toast.makeText(getApplicationContext(), "Attendence Marked",
						1000).show();
				/*
				 * Intent intent = new Intent(StudentRegistration.this,
				 * HomePage.class); startActivity(intent);
				 */}
		}
	}

}