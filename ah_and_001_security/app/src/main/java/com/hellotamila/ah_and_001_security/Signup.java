package com.hellotamila.ah_and_001_security;

import android.Manifest;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.StrictMode;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
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
import java.util.Random;

public class Signup extends AppCompatActivity {
            int code;
            Intent xIntent;
            String id;
            InputStream is = null;
            String result = null;
            String line = null;
            String xMobileNo,xUserName;
            EditText xEdtMobileNo,xEdtUserName;
            String xImei;
            SimpleGetterAndSetter obj;

    Random rand;
    int n;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        xEdtUserName = (EditText) findViewById(R.id.f_edt_username);
        xEdtMobileNo = (EditText) findViewById(R.id.f_edt_mobileno);
        obj= new SimpleGetterAndSetter();
        rand = new Random();
        n = (rand.nextInt(80) + 65);
            if (android.os.Build.VERSION.SDK_INT > 9) {
                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                        .permitAll().build();
                StrictMode.setThreadPolicy(policy);
            }

        xImei =obj.getImei();
    }


    public void sign_up(View v) {
        ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        xMobileNo=xEdtMobileNo.getText().toString();
        xUserName=xEdtUserName.getText().toString();
        obj.setDetails(xUserName,xMobileNo);
        nameValuePairs.add(new BasicNameValuePair("username",xUserName));
        nameValuePairs.add(new BasicNameValuePair("mobileno",xMobileNo));
        nameValuePairs.add(new BasicNameValuePair("imei", xImei));
        try {
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost(
                    "http://hellotamila.com/spiro/onetime/insert.php");
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
                Intent xIntent = new Intent(getApplicationContext(), Grid_Set_Prompt.class);
                startActivity(xIntent);
                finish();
               obj.setOtp(String.valueOf(n));
                Intent intent = new Intent(getApplicationContext(), ValidateOtp_SignUp.class);
                PendingIntent pi = PendingIntent.getActivity(getApplicationContext(), 0, intent, 0);

                SmsManager sms = SmsManager.getDefault();
                sms.sendTextMessage(xEdtMobileNo.getText().toString(), null, String.valueOf(n), pi, null);

             /*   Intent xIntent = new Intent(getApplicationContext(), Grid_Set_Prompt.class);
                startActivity(xIntent);*/
            } else {
                Toast.makeText(getBaseContext(), "Sorry, Try Again",
                        Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            Log.e("Fail 3", e.toString());
        }
    }

}




