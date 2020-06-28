package com.hellotamila.middaymealtamilnadu;
import android.Manifest;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
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
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    int code;
    String xSchoolName;
    String id;
    InputStream is = null;
    String result = null;
    String line = null;
    String xUdiseCode;
    EditText xEdtUdise;
    SimpleGetterAndSetter obj;
    DataBaseConnection mCon;
    TextView xTxtDeveloper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        xEdtUdise = (EditText) findViewById(R.id.edtUdise);
        xTxtDeveloper = (TextView) findViewById(R.id.developer);

        obj= new SimpleGetterAndSetter();
        mCon = new DataBaseConnection(this);
        LoadDeveloperDetails();
       // customBar();
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        /*if(mCon.CheckIsDataAlreadyInDBorNot()) {
            String xName=mCon.fn_GetSchoolName();
            obj.setOtp(xName);
            Intent xIntent = new Intent(getApplicationContext(), HomePage.class);
            startActivity(xIntent);
        }*/
        if(mCon.fn_GetSchoolName().equalsIgnoreCase("")) {
        }
        else{

            String xName=mCon.fn_GetSchoolName();
            obj.setOtp(xName);
            finish();
            Intent xIntent = new Intent(getApplicationContext(), HomePage.class);
            startActivity(xIntent);
        }
    }
    public void check_udise(View v) {
        checkUser();
    }
       public void checkUser() {
        ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        xUdiseCode=xEdtUdise.getText().toString();


        nameValuePairs.add(new BasicNameValuePair("code", xUdiseCode));
        try {
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost(
                    "http://hellotamila.com/hellotamila_apps/mdm/checkuser.php");
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
            //code = (json_data.getInt("code"));
            xSchoolName=(json_data.getString("school_name"));

            if (xSchoolName.equalsIgnoreCase("")) {
                Toast.makeText(getBaseContext(), "Sorry, Try Again",
                        Toast.LENGTH_LONG).show();

            }

            else {
                Toast.makeText(getBaseContext(), "Registered Succesfully",
                        Toast.LENGTH_LONG).show();
                try {

                    mCon.insertSchool(xSchoolName);
                    Toast.makeText(getApplicationContext(),
                            "School Details Added", Toast.LENGTH_SHORT).show();
                    finish();
                    Intent xIntent = new Intent(getApplicationContext(), HomePage.class);
                    startActivity(xIntent);

                } catch (Exception e) {

                }
                obj.setOtp(xSchoolName);
            }
        } catch (Exception e) {
            Log.e("Fail 3", e.toString());
        }
    }
    public void LoadDeveloperDetails() {
        ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        //nameValuePairs.add(new BasicNameValuePair("id", "1"));
        try {
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost(
                    "http://hellotamila.com/hellotamila_apps/mdm/load_live_details.php");
           // httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
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
            xTxtDeveloper.setText(json_data.getString("name"));
        } catch (Exception e) {
            Log.e("Fail 3", e.toString());
        }
    }

}
