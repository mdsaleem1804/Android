package com.hellotamila.ah_and_007;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class AdminVerification extends AppCompatActivity {
        DataBaseConnection mCon;
        private SQLiteDatabase db;
        private Cursor xCursor;
        String xSelectedUser;

    EditText matchingdetails, longitude, latitude;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_admin_verification);
         Bundle b = new Bundle();
         b = getIntent().getExtras();
         openDatabase();
         xSelectedUser = b.getString("name");
         mCon = new DataBaseConnection(this);
        latitude = (EditText) findViewById(R.id.latitude);
        longitude = (EditText) findViewById(R.id.longitude);
        matchingdetails = (EditText) findViewById(R.id.matchingdetails);

        try {
            String xQry = "select mobileno from user_requests where aadhar ='" + xSelectedUser + "'";
            xCursor = db.rawQuery(xQry, null);
            if(xCursor.getCount()>=1) {
                xCursor.moveToFirst();
                matchingdetails.setText(xCursor.getString(0));
                      }

        }catch(Exception e)
        {
            Toast.makeText(getApplicationContext(),e.toString(),Toast.LENGTH_LONG).show();
        }

    }
    protected void openDatabase() {
        db = openOrCreateDatabase("ah_and_007.db", Context.MODE_PRIVATE, null);
    }
    public void match_police(View v) {
        try {
            String xQry = "select latitude,longitude from add_fir where aadhar ='" + xSelectedUser + "'";
            xCursor = db.rawQuery(xQry, null);
            if(xCursor.getCount()>=1) {
                xCursor.moveToFirst();
                latitude.setText(xCursor.getString(0));
                longitude.setText(xCursor.getString(1));
                Toast.makeText(getApplicationContext(),"Your Records Matched With Police Database",Toast.LENGTH_LONG).show();
            }
            else
            {
                Toast.makeText(getApplicationContext(),"No Records Matched",Toast.LENGTH_LONG).show();
            }
        }catch(Exception e)
        {
            Toast.makeText(getApplicationContext(),e.toString(),Toast.LENGTH_LONG).show();
        }

    }
    public void match_hospital(View v) {
        try {
            String xQry = "select latitude,longitude from add_hospital_data where aadhar ='" + xSelectedUser + "'";
            xCursor = db.rawQuery(xQry, null);
            if(xCursor.getCount()>=1) {
                xCursor.moveToFirst();
                      latitude.setText(xCursor.getString(0));
                longitude.setText(xCursor.getString(1));
                Toast.makeText(getApplicationContext(),"Your Records Matched With Hospital Database",Toast.LENGTH_LONG).show();
            }
            else
            {
                Toast.makeText(getApplicationContext(),"No Records Matched",Toast.LENGTH_LONG).show();
            }
        }catch(Exception e)
        {
            Toast.makeText(getApplicationContext(),e.toString(),Toast.LENGTH_LONG).show();
        }
    }
    public void send(View v) {
        try {
            String xNumber=matchingdetails.getText().toString();
            String xLatitide=latitude.getText().toString();
            String xLongitude=longitude.getText().toString();
            String xMapUrl = "http://maps.google.com/?q=" + xLatitide + ","+ xLongitude;
            SmsManager sms = SmsManager.getDefault();
            sms.sendTextMessage(xNumber, null, "Your Missing Person Found in this  area"+xMapUrl, null, null);

        }catch(Exception e)
        {
            Toast.makeText(getApplicationContext(),e.toString(),Toast.LENGTH_LONG).show();
        }
    }

}
