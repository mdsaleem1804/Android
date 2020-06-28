package com.example.ag_and_012_school;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.InputType;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MarkEntry extends Activity {
	Spinner xSpnStudentNameForMarkEntry;
	String xSelectedStudent;
	DataBaseConnection mCon;
	EditText xEdtSubject1,xEdtSubject2,xEdtSubject3,xEdtSubject4,xEdtSubject5;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.markentry);
		xSpnStudentNameForMarkEntry = (Spinner) findViewById(R.id.fspnStudentNameForMarkEntry);
		xEdtSubject1 = (EditText) findViewById(R.id.fedtSubject1);
		xEdtSubject2 = (EditText) findViewById(R.id.fedtSubject2);
		xEdtSubject3 = (EditText) findViewById(R.id.fedtSubject3);
		xEdtSubject4 = (EditText) findViewById(R.id.fedtSubject4);
		xEdtSubject5 = (EditText) findViewById(R.id.fedtSubject5);
		
		xEdtSubject1.setInputType(InputType.TYPE_CLASS_NUMBER);
		InputFilter[] FilterArray = new InputFilter[1];
		FilterArray[0] = new InputFilter.LengthFilter(2);
		xEdtSubject1.setFilters(FilterArray);
		
		xEdtSubject2.setInputType(InputType.TYPE_CLASS_NUMBER);
		xEdtSubject2.setFilters(FilterArray);
		
		
		xEdtSubject3.setInputType(InputType.TYPE_CLASS_NUMBER);
		xEdtSubject3.setFilters(FilterArray);
		
		
		xEdtSubject4.setInputType(InputType.TYPE_CLASS_NUMBER);
		xEdtSubject4.setFilters(FilterArray);
		
		
		xEdtSubject5.setInputType(InputType.TYPE_CLASS_NUMBER);
		xEdtSubject5.setFilters(FilterArray);
		
		mCon = new DataBaseConnection(this);
		LoadStudents();
		
	}
	public void LoadStudents() {
		ArrayList<String> my_array = new ArrayList<String>();
		my_array = mCon.fn_GetStudents();

		
		@SuppressWarnings("rawtypes")
		ArrayAdapter my_Adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, my_array);
		xSpnStudentNameForMarkEntry.setAdapter(my_Adapter);
		xSpnStudentNameForMarkEntry
				.setOnItemSelectedListener(new OnItemSelectedListener() {
					public void onItemSelected(AdapterView<?> parent,
							View view, int position, long id) {
						xSelectedStudent = xSpnStudentNameForMarkEntry.getSelectedItem()
								.toString();

					}

					public void onNothingSelected(AdapterView<?> arg0) {
						// TODO Auto-generated method stub

					}

				});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
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
}