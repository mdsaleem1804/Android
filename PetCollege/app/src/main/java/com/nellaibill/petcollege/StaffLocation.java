package com.nellaibill.petcollege;

import android.Manifest;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.test.mock.MockPackageManager;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.InputStream;

public class StaffLocation extends AppCompatActivity {
    int code;
    android.content.Intent xIntent;

    EditText  xEdtLat, xEdtLong;
    String xStaffUserName;
    SimpleGetterAndSetter obj;
       private SQLiteDatabase db;
    GPSTracker gps;
    private static final int REQUEST_CODE_PERMISSION = 2;
    String mPermission = Manifest.permission.ACCESS_FINE_LOCATION;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.content_stafflocation);

            obj = new SimpleGetterAndSetter();
            xEdtLat = (EditText) findViewById(R.id.editText1);
            xEdtLong = (EditText) findViewById(R.id.editText2);
            gps = new GPSTracker(StaffLocation.this);
            try {
                if (ActivityCompat.checkSelfPermission(this, mPermission)
                        != MockPackageManager.PERMISSION_GRANTED) {

                    ActivityCompat.requestPermissions(this, new String[]{mPermission},
                            REQUEST_CODE_PERMISSION);

                    // If any permission above not allowed by user, this condition will
                    //execute every time, else your else part will work
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (gps.canGetLocation()) {

                double latitude = gps.getLatitude();
                double longitude = gps.getLongitude();
                xEdtLat.setText(String.valueOf(latitude));
                xEdtLong.setText(String.valueOf(longitude));
            } else {

                gps.showSettingsAlert();
            }
            xStaffUserName=obj.getStaffName();
            openDatabase();

        } catch (Exception e) {
            String xError = e.toString();
            Toast.makeText(getApplicationContext(),xError,Toast.LENGTH_LONG).show();
        }
    }

public  void get(View v)
{
    if (gps.canGetLocation()) {

        double latitude = gps.getLatitude();
        double longitude = gps.getLongitude();
        xEdtLat.setText(String.valueOf(latitude));
        xEdtLong.setText(String.valueOf(longitude));
    } else {

        gps.showSettingsAlert();
    }
}

    public void update(View v) {
        //    xCursor = db.rawQuery(xQry, null);
        try {
            db.execSQL("UPDATE staff_details SET stafflat='" + xEdtLat.getText().toString()+"',stafflong='" + xEdtLong.getText().toString()+"'  WHERE username='" + xStaffUserName+"'");
            Toast.makeText(getApplicationContext(), "Location Updated Succesfully", Toast.LENGTH_LONG).show();
        }
        catch(Exception e)
        {
            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
        }
    }
    protected void openDatabase() {
        db = openOrCreateDatabase("petcollege.db", Context.MODE_PRIVATE, null);
    }
}
