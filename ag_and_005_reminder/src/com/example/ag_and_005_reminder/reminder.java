package com.example.ag_and_005_reminder;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class reminder extends Activity implements OnItemSelectedListener {
	EditText xEdtName, xEdtDescription, xEdtDate;
	DataBaseConnection mCon;
	Calendar myCalendar;
	DatePickerDialog.OnDateSetListener date;
	private RadioGroup radioDateGroup;
	private RadioButton radioDateButton;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mCon = new DataBaseConnection(this);
		setContentView(R.layout.reminder_edit);
		xEdtName = (EditText) findViewById(R.id.fedt_name);
		xEdtDescription = (EditText) findViewById(R.id.fedt_description);
		xEdtDate = (EditText) findViewById(R.id.fedt_date);
		radioDateGroup = (RadioGroup) findViewById(R.id.radioGroup);
		// SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		// String date = sdf.format(new Date());
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		xEdtDate.setText(dateFormat.format(new Date())); // it will show
															// 16/07/2013

		if (android.os.Build.VERSION.SDK_INT > 9) {
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
					.permitAll().build();
			StrictMode.setThreadPolicy(policy);
		}
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
				new DatePickerDialog(reminder.this, date, myCalendar
						.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
						myCalendar.get(Calendar.DAY_OF_MONTH)).show();
			}
		});

	}

	private void updateLabel() {

		String myFormat = "dd/MM/yyyy"; // In which you need put here
		SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

		xEdtDate.setText(sdf.format(myCalendar.getTime()));
	}

	public void savereminder(View v) {
		int selectedId = radioDateGroup.getCheckedRadioButtonId();
		radioDateButton = (RadioButton) findViewById(selectedId);
		String xDateGroup = radioDateButton.getText().toString();
		if (xDateGroup.equalsIgnoreCase("Weekly")) {
			for (int i = 1; i <= 10; i++) {
				String xDate = xEdtDate.getText().toString(); // Start date
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				Calendar c = Calendar.getInstance();
				try {
					c.setTime(sdf.parse(xDate));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				c.add(Calendar.WEEK_OF_MONTH, i); // number of days to add
				xDate = sdf.format(c.getTime());

				mCon.insertReminder(xEdtName.getText().toString(),
						xEdtDescription.getText().toString(),xDate);
			}
		}
		
		if (xDateGroup.equalsIgnoreCase("Monthly")) {
			for (int i = 1; i <= 10; i++) {
				String xDate = xEdtDate.getText().toString(); // Start date
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				Calendar c = Calendar.getInstance();
				try {
					c.setTime(sdf.parse(xDate));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				c.add(Calendar.MONTH, i); // number of days to add
				xDate = sdf.format(c.getTime());

				mCon.insertReminder(xEdtName.getText().toString(),
						xEdtDescription.getText().toString(), xDate);
			}
		}

		DataClear();
		Toast.makeText(getApplicationContext(), "Reminder Saves", 1000).show();
	}

	public void DataClear() {
		xEdtDate.setText("");
		xEdtDescription.setText("");
		xEdtName.setText("");
	}

	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub

	}
}
