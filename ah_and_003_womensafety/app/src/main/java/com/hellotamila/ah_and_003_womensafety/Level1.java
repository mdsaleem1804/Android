package com.hellotamila.ah_and_003_womensafety;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Level1 extends AppCompatActivity {
    int code;
    android.content.Intent xIntent;
    String xChatText;
    String id;
    InputStream is = null;
    String result = null;
    String line = null;
    String xMobileNo, xUserName;
    EditText xEdtMobileNo, xEdtLat, xEdtLong,xEdtMobileNoOther;
    String xImei;
    SimpleGetterAndSetter obj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_level1);
            if (android.os.Build.VERSION.SDK_INT > 9) {
                android.os.StrictMode.ThreadPolicy policy = new android.os.StrictMode.ThreadPolicy.Builder()
                        .permitAll().build();
                android.os.StrictMode.setThreadPolicy(policy);
            }
            obj = new SimpleGetterAndSetter();
            xEdtLat = (EditText) findViewById(R.id.editText1);
            xEdtLong = (EditText) findViewById(R.id.editText2);
            xEdtMobileNo = (EditText) findViewById(R.id.editText3);
            //xEdtMobileNoOther = (EditText) findViewById(R.id.editText4);

           /* Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);*/

            FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    try {
                        fn_CollectGpsValues();
                    } catch (Exception e) {
                        String xError = e.toString();
                    }
                    Snackbar.make(view, "Gps Value Collected", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            });


        } catch (Exception e) {
            String xError = e.toString();
        }
    }

    public void fn_CollectGpsValues() {
        GPSTracker gps;
        gps = new GPSTracker(this);
        if (gps.canGetLocation()) {
            double latitude = gps.getLatitude();
            double longitude = gps.getLongitude();
            xEdtLat.setText(String.valueOf(latitude));
            xEdtLong.setText(String.valueOf(longitude));
            // String $xMapUrl = "http://maps.google.com/?q=" + latitude + ","
            // + longitude;
            // sendSMS(xMobileNo, $xMapUrl);
        } else {

            gps.showSettingsAlert();
        }
    }

    public void update(View v) {
        fn_Update_Low();
    }

    public void fn_Update_Low() {
        java.util.ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        //obj.setDetails(xUserName,xMobileNo,xImei);
        obj.setLowlat(xEdtLat.getText().toString(), xEdtLong.getText().toString(), xEdtMobileNo.getText().toString());
        nameValuePairs.add(new BasicNameValuePair("xlat", xEdtLat.getText().toString()));
        nameValuePairs.add(new BasicNameValuePair("xlong", xEdtLong.getText().toString()));
        nameValuePairs.add(new BasicNameValuePair("xmobileno", xEdtMobileNo.getText().toString()));

        try {
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost(
                    "http://hellotamila.com/spiro/womensafety/updatelow.php");
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            HttpResponse response = httpclient.execute(httppost);
            HttpEntity entity = response.getEntity();
            is = entity.getContent();
            Log.e("pass 1", "connection success ");
        } catch (Exception e) {
            Log.e("Fail 1", e.toString());
            Toast.makeText(getApplicationContext(), "Invalid IP Address",
                    Toast.LENGTH_LONG).show();
        }

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    is, "iso-8859-1"), 8);
            StringBuilder sb = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
            is.close();
            result = sb.toString();
            Log.e("pass 2", "connection success ");
        } catch (Exception e) {
            Log.e("Fail 2", e.toString());
        }

        try {
            JSONObject json_data = new JSONObject(result);
            code = (json_data.getInt("code"));

            if (code == 1) {
                Toast.makeText(getBaseContext(), "Registered Successfully",
                        Toast.LENGTH_SHORT).show();

            } else {
                Toast.makeText(getBaseContext(), "Sorry, Try Again",
                        Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            Log.e("Fail 3", e.toString());
        }
    }
}
