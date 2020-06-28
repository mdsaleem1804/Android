package com.nellaibill.petcollege;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AddHospitalData extends AppCompatActivity {
    DataBaseConnection mCon;
    EditText xFullName, xAadhar,xMobileNo,xRemarks,xLatitude,xLongitude;
    GPSTracker gps;
    TextView textFile;
    private static final int PICKFILE_RESULT_CODE = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_stafflocation);
        mCon = new DataBaseConnection(this);
        xLatitude = (EditText) findViewById(R.id.editText1);
        xLongitude = (EditText) findViewById(R.id.editText2);
        gps = new GPSTracker(AddHospitalData.this);
        // check if GPS enabled
        if(gps.canGetLocation()){

            double latitude = gps.getLatitude();
            double longitude = gps.getLongitude();
            xLatitude.setText(String.valueOf(latitude));
            xLongitude.setText(String.valueOf(longitude));
            // \n is for new line
            Toast.makeText(getApplicationContext(), "Your Location is - \nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();
        }else{
            // can't get location
            // GPS or Network is not enabled
            // Ask user to enable GPS/network in settings
            gps.showSettingsAlert();
        }


    }


}

