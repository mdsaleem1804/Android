package com.example.ag_and_002_tourist;

import com.example.and_af_tourist.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class GetBudget extends Activity {
	DataBaseConnection mCon;
	Spinner xspnSource, xspnDestination;
	EditText xDays;
	Button xBtnOk;
	String xSource,xDestination;
	int xAmount,xDaysCount;
	String[] Districts = { "Tirunelveli" ,"Madurai","Tuticorin",
			"Chennai" };
	AlertDialog alertDialog;
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		if (android.os.Build.VERSION.SDK_INT > 9) {
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
					.permitAll().build();
			StrictMode.setThreadPolicy(policy);
		}
		mCon = new DataBaseConnection(this);
		alertDialog = new AlertDialog.Builder(this).create();
		mCon.getWritableDatabase();
		setContentView(R.layout.getbudget);
		xDays = (EditText) findViewById(R.id.edtDays);
		xspnSource = (Spinner) findViewById(R.id.spnSource);
		xspnDestination = (Spinner) findViewById(R.id.spnDestination);
		xBtnOk = (Button) findViewById(R.id.btnGetBudget);
		LoadSource();
		LoadDestination();
		
		xBtnOk.setOnClickListener(new View.OnClickListener() {

			@SuppressWarnings("deprecation")
			@Override
			public void onClick(View v) {
				try {
					LoadAmount();
					alertDialog.setTitle("AMOUNT DETAILS");
					alertDialog.setMessage(" AMOUNT From "+xSource + "to  \n" +   xDestination + " is " 
							+xAmount );

					alertDialog.setButton("OK",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int which) {
									if (true) {
										/*
										 * xIntent = new Intent(listmembers.this,
										 * staffmainpage.class);
										 * startActivity(xIntent);
										 */
									}
									// here you can add functions
								}
							});
					alertDialog.show();

					
				} catch (Exception e) {

				}

			}
		});

		
		
	}
	
	public void LoadSource() {
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, Districts);
		xspnSource.setAdapter(adapter);
		xspnSource
				.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
					@Override
					public void onItemSelected(AdapterView<?> arg0, View arg1,
							int arg2, long arg3) {
						int position = xspnSource.getSelectedItemPosition();
						Toast.makeText(getApplicationContext(),
								"You have selected " + Districts[+position],
								Toast.LENGTH_LONG).show();
						xSource= xspnSource.getSelectedItem().toString();
						// TODO Auto-generated method stub
					}

					@Override
					public void onNothingSelected(AdapterView<?> arg0) {
						// TODO Auto-generated method stub
					}
				});
	}
	public void LoadDestination() {
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, Districts);
		xspnDestination.setAdapter(adapter);
		xspnDestination
				.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
					@Override
					public void onItemSelected(AdapterView<?> arg0, View arg1,
							int arg2, long arg3) {
						int position = xspnDestination.getSelectedItemPosition();
						Toast.makeText(getApplicationContext(),
								"You have selected " + Districts[+position],
								Toast.LENGTH_LONG).show();
						xDestination= xspnDestination.getSelectedItem().toString();
						// TODO Auto-generated method stub
					}

					@Override
					public void onNothingSelected(AdapterView<?> arg0) {
						// TODO Auto-generated method stub
					}
				});
	}
	
	public void LoadAmount()
	{
		int xDaysConvert;
		xDaysConvert=Integer.parseInt(xDays.getText().toString());
		if(xSource.equalsIgnoreCase("Tirunelveli") && xDestination.equalsIgnoreCase("Tirunelveli"))
		{
			xAmount=0;
		}
		if(xSource.equalsIgnoreCase("Madurai") && xDestination.equalsIgnoreCase("Madurai"))
		{
			xAmount=0;
		}
		
		if(xSource.equalsIgnoreCase("Chennai") && xDestination.equalsIgnoreCase("Chennai"))
		{
			xAmount=0;
		}
		
		if(xSource.equalsIgnoreCase("Tirunelveli") && xDestination.equalsIgnoreCase("Madurai"))
		{
			xAmount=2000*xDaysConvert;
		}
		if(xSource.equalsIgnoreCase("Tirunelveli") && xDestination.equalsIgnoreCase("Tuticorin"))
		{
			xAmount=500*xDaysConvert;
		}
		if(xSource.equalsIgnoreCase("Tirunelveli") && xDestination.equalsIgnoreCase("Chennai"))
		{
			xAmount=8000*xDaysConvert;
		}
		if(xSource.equalsIgnoreCase("Madurai") && xDestination.equalsIgnoreCase("Tirunelveli"))
		{
			xAmount=2000*xDaysConvert;
		}
		if(xSource.equalsIgnoreCase("Madurai") && xDestination.equalsIgnoreCase("Tuticorin"))
		{
			xAmount=2500*xDaysConvert;
		}
		
	}

}
