package com.example.and_af_donor;

import java.util.ArrayList;
import java.util.Calendar;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import android.widget.Toast;

public class AddContacts extends Activity {
	// This references camefrom "activity_add_contacts.xml" layout
	EditText etName;
	EditText etPhone;
	EditText etEmail;
	EditText xEdtAddressLine1,xEdtAddressLine2;
	
	TextView LastDonationDate;
	Spinner spinner;
	String s;
	String s1;
	String s2;
	String s3,xAddressLine1,xAddressLine2;
	int acheivedId;
	String lastDate;
	ArrayList<String> bloodGroups = new ArrayList<String>();
	Calendar ca;
	TextView lastDonationTextView;
	Button savebtn;
	String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
	String nameS, phoneS, emailS, bloodGroupStr, lastDateS;

	// "activity_add_contacts.xml" layout refrences finished

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_contacts);
		etName = (EditText) findViewById(R.id.NameEditText);
		etPhone = (EditText) findViewById(R.id.PhoneEditText);
		etEmail = (EditText) findViewById(R.id.EmailEditText);
		xEdtAddressLine1 = (EditText) findViewById(R.id.edtaddressline1);
		xEdtAddressLine2 = (EditText) findViewById(R.id.edtaddressline2);
		LastDonationDate = (TextView) findViewById(R.id.lastDonationTextView);
		savebtn = (Button) findViewById(R.id.saveButton);
		lastDonationTextView = (TextView) findViewById(R.id.lastDonationTextView);

		String getTask = getIntent().getStringExtra("task");
		ca = Calendar.getInstance();

		bloodGroups.add("A+");
		bloodGroups.add("A-");
		bloodGroups.add("B+");
		bloodGroups.add("B-");
		bloodGroups.add("AB+");
		bloodGroups.add("AB-");
		bloodGroups.add("O+");
		bloodGroups.add("O-");

		lastDonationTextView.setText(ca.get(Calendar.DAY_OF_MONTH) + "/"
				+ (ca.get(Calendar.MONTH) + 1) + "/" + ca.get(Calendar.YEAR));

		spinner = (Spinner) findViewById(R.id.spinner2);

		spinner.setAdapter(new ArrayAdapter<String>(AddContacts.this,
				android.R.layout.simple_spinner_dropdown_item, bloodGroups));

		spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> adapterView, View view,
					int i, long l) {
				String selectedItem = spinner.getSelectedItem().toString()
						.trim();

				s3 = spinner.getSelectedItem().toString().trim();

			}

			@Override
			public void onNothingSelected(AdapterView<?> adapterView) {

			}
		});

		if (getTask.equals("edit")) {

			acheivedId = getIntent().getIntExtra("id", 0);
			nameS = getIntent().getStringExtra("name");
			phoneS = getIntent().getStringExtra("phone");

			emailS = getIntent().getStringExtra("email");
			bloodGroupStr = getIntent().getStringExtra("bloodGroup");
			lastDateS = getIntent().getStringExtra("lastDate");

			etName.setText(nameS);
			etPhone.setText(phoneS);
			etEmail.setText(emailS);
			LastDonationDate.setText(lastDateS);

			for (int i = 1; i < bloodGroups.size(); i++) {

				if (bloodGroups.get(i).equals(bloodGroupStr)) {
					spinner.setSelection(i);
					break;
				}
			}

			savebtn.setText("UPDATE");
		}

		// Toast.makeText(this, savebtn.getText().toString() ,
		// Toast.LENGTH_LONG).show();

	}

	public boolean nameValidation(String s) {

		Toast.makeText(getApplicationContext(), "Please enter name correctly",
				Toast.LENGTH_SHORT).show();
		return false;

	}

	// Save Information Button Onclick
	public void saveInfo(View view) {

		s = etName.getText().toString().trim();
		s2 = etEmail.getText().toString();
		lastDate = LastDonationDate.getText().toString().trim();
		xAddressLine1=xEdtAddressLine1.getText().toString();
		xAddressLine2=xEdtAddressLine2.getText().toString();
		if (s.length() == 0) {
			nameValidation(s);
		} else {
			s1 = etPhone.getText().toString();
			if (s1.trim().length() == 0) {
				Toast.makeText(getApplicationContext(), "Phone needed",
						Toast.LENGTH_SHORT).show();

			} else {
				if (s2.matches(emailPattern)) {
					if (savebtn.getText().toString().equals("save")) {

						DonorInformation donorInfo = new DonorInformation(s,
								s1, s2, s3, lastDate,xAddressLine1,xAddressLine2);
						DbHelper dbHelper = new DbHelper(AddContacts.this);
						long result = dbHelper.insert(AddContacts.this,
								donorInfo);
						if (result != -1) {
							setResult(RESULT_OK);
						}

						finish();
					}

					else if (savebtn.getText().toString().equals("UPDATE")) {

						DonorInformation donorInfo = new DonorInformation(
								acheivedId, s, s1, s2, s3, lastDate);
						DbHelper dbHelper = new DbHelper(AddContacts.this);
						// Toast.makeText(this,"ID : "+
						// acheivedId,Toast.LENGTH_LONG).show();
						int re = dbHelper.update(this, donorInfo);
						// Toast.makeText(this,"Data : "+
						// re,Toast.LENGTH_LONG).show();
						startActivity(new Intent(AddContacts.this,
								MainActivity.class));
						finish();
					}

				} else {
					Toast.makeText(AddContacts.this,
							"Please Enter Correct email", Toast.LENGTH_LONG)
							.show();
				}
			}

		}

	}

	public void Getcalendar(View view) {

		DatePickerDialog datePickerDialog = new DatePickerDialog(
				AddContacts.this, new DatePickerDialog.OnDateSetListener() {
					@Override
					public void onDateSet(DatePicker datePicker, int i, int i1,
							int i2) {

						lastDonationTextView.setText(i2 + "/" + (i1 + 1) + "/"
								+ i);
					}
				}, ca.get(Calendar.YEAR), ca.get(Calendar.MONTH),
				ca.get(Calendar.DAY_OF_MONTH));
		datePickerDialog.show();
	}
}
