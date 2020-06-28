package com.example.afr_and_005_unlock;

import java.io.ByteArrayInputStream;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

public class DoctorLoginSuccess extends Activity implements
		OnItemSelectedListener {
	Button 
			xBtnColorSegmentation, xBtnSetUser,xBtnCaptureImage,xBtnReport,xBtnLock;
	Spinner xSpnUser;
	DataBaseConnection db;
	EditText xEdtPatientRemarks;
	int xCheck;
    ImageView xSetImage;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.adminloginsuccess);
		xBtnColorSegmentation = (Button) findViewById(R.id.btnColorSegmentation);
		xBtnCaptureImage = (Button) findViewById(R.id.btnCapture);
		xBtnReport = (Button) findViewById(R.id.btnReport);
		xBtnLock = (Button) findViewById(R.id.btnLock);
		//xBtnSetUser = (Button) findViewById(R.id.btnSetUser);
		//xSpnUser = (Spinner) findViewById(R.id.spnUser);
		//xEdtPatientRemarks = (EditText) findViewById(R.id.edtRecievePatientRemarks);
		xSetImage= (ImageView) findViewById(R.id.imageView1);
		db = new DataBaseConnection(this);

		//loadSpinnerData();

         
		 
         Cursor cursor = db.fetchProfileImageFromDatabase();
         if(cursor != null)
         {
             if(cursor.moveToFirst())
             {
                do
                {
                    //byte[] data = cursor.getBlob(cursor.getColumnIndex("PROFILE_IMAGE"));
                    //pic1.setImageBitmap(BitmapFactory.decodeByteArray(data, 0, data.length));
                        //String temp=cursor.getBlob(cursor.getColumnIndex("PROFILE_IMAGE")).toString();
                        //byte[] data=temp.getBytes();
                        //pic1.setImageBitmap(BitmapFactory.decodeByteArray(data, 0, data.length));
                    byte[] data = cursor.getBlob(cursor.getColumnIndex("image"));
                    ByteArrayInputStream imageStream = new ByteArrayInputStream(data);
                    Bitmap theImage = BitmapFactory.decodeStream(imageStream);
                    xSetImage.setImageBitmap(theImage);
                     
                }
                while(cursor.moveToNext());
             }
             cursor.close();
        }
         db.close();
		try {

			

			xBtnColorSegmentation
					.setOnClickListener(new View.OnClickListener() {

						public void onClick(View v) {

							Intent intent = new Intent(DoctorLoginSuccess.this,
									ColorSegmentation.class);
							startActivity(intent);

						}

					});
			xBtnCaptureImage
			.setOnClickListener(new View.OnClickListener() {

				public void onClick(View v) {

					Intent intent = new Intent(DoctorLoginSuccess.this,
							CaptureImage.class);
					startActivity(intent);

				}

			});
			xBtnReport
			.setOnClickListener(new View.OnClickListener() {

				public void onClick(View v) {

					Intent intent = new Intent(DoctorLoginSuccess.this,
							ReportGeneration.class);
					startActivity(intent);

				}

			});
			xBtnLock
			.setOnClickListener(new View.OnClickListener() {

				public void onClick(View v) {

					Intent intent = new Intent(DoctorLoginSuccess.this,
							MyLockScreenActivity.class);
					startActivity(intent);

				}

			});
			/*
				}
*/
		} catch (Exception e) {

		}
	}
	private void loadSpinnerData() {
		// database handler
		// DatabaseHandler db = new DatabaseHandler(getApplicationContext());

		// Spinner Drop down elements
		List<String> lables = db.getAllLabels();

		// Creating adapter for spinner
		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, lables);

		// Drop down layout style - list view with radio button
		dataAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		// attaching data adapter to spinner
		xSpnUser.setAdapter(dataAdapter);
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
