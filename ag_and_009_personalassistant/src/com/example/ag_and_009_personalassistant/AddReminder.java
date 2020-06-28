package com.example.ag_and_009_personalassistant;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.ag_and_009_dailystudentactivities.R;

public class AddReminder extends Activity implements OnItemSelectedListener {
	EditText xEdtName, xEdtPlace, xEdtDescription, xEdtDate;
	DataBaseConnection mCon;
	Spinner xSpnExpenses;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mCon = new DataBaseConnection(this);
		setContentView(R.layout.reminder);
		xEdtName = (EditText) findViewById(R.id.fedt_Name);
		xEdtPlace = (EditText) findViewById(R.id.fedt_Place);

		xEdtDescription = (EditText) findViewById(R.id.fedt_Description);
		xEdtDate = (EditText) findViewById(R.id.fedt_Date);
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		xEdtDate.setText(dateFormat.format(new Date())); // it will show
		// Spinner element
		xSpnExpenses = (Spinner) findViewById(R.id.fSpnExpenses);

		xSpnExpenses.setOnItemSelectedListener(this);
		// Spinner Drop down elements
		List<String> xExpensesList = new ArrayList<String>();
		xExpensesList.add("Meetings");
		xExpensesList.add("Food");
		xExpensesList.add("Classes");
		xExpensesList.add("Seminar");
		xExpensesList.add("Functions");
		xExpensesList.add("Sports");
		xExpensesList.add("More");
		
		// Creating adapter for spinner
		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, xExpensesList);
		// Drop down layout style - list view with radio button
		dataAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// attaching data adapter to spinner
		xSpnExpenses.setAdapter(dataAdapter);
	}

	public void savereminder(View v) {
		mCon.insertReminder(xEdtName.getText().toString(), xEdtDescription
				.getText().toString(), xEdtDate.getText().toString(),xSpnExpenses.getSelectedItem().toString());
		dataclear();
		Toast.makeText(getApplicationContext(), "Reminder Saves", 1000).show();
	}

	public void dataclear() {
		xEdtDate.setText("");
		xEdtDescription.setText("");
		xEdtName.setText("");
		xEdtPlace.setText("");
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
