package com.hellotamila.ah_and_001_security;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.InputStream;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
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
public class HomePage extends AppCompatActivity {

    EditText xEdtOtp;
    int code;
    Intent xIntent;
    String xChatText;
    String id;
    InputStream is = null;
    String result = null;
    String line = null;
    String xMobileNo,xUserName;
    EditText xEdtMobileNo,xEdtUserName;
    String xImei;
   SimpleGetterAndSetter obj;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepage);
        try {
            obj= new SimpleGetterAndSetter();
            if (android.os.Build.VERSION.SDK_INT > 9) {
                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                        .permitAll().build();
                StrictMode.setThreadPolicy(policy);
            }
        TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED)
        {
            return;
        }
        //Reference : https://medium.com/@ssaurel/how-to-retrieve-an-unique-id-to-identify-android-devices-6f99fd5369eb
         xImei = telephonyManager.getDeviceId();
        //xImei = Settings.Secure.getString(getContentResolver(),
               // Settings.Secure.ANDROID_ID);
        obj.setImei(xImei);


           /* TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            //Calling the methods of TelephonyManager the returns the information
            String IMEINumber = tm.getDeviceId();
            String subscriberID = tm.getDeviceId();
            String SIMSerialNumber = tm.getSimSerialNumber();
            String networkCountryISO = tm.getNetworkCountryIso();
            String SIMCountryISO = tm.getSimCountryIso();
            String softwareVersion = tm.getDeviceSoftwareVersion();
            String voiceMailNumber = tm.getVoiceMailNumber();
            String info = "Phone Details:\n";
            info += "\n IMEI Number:" + IMEINumber;
            info += "\n SubscriberID:" + subscriberID;
            info += "\n Sim Serial Number:" + SIMSerialNumber;
            info += "\n Network Country ISO:" + networkCountryISO;
            info += "\n SIM Country ISO:" + SIMCountryISO;
            info += "\n Software Version:" + softwareVersion;
            info += "\n Voice Mail Number:" + voiceMailNumber;*/


            Toast.makeText(getApplicationContext(), xImei, Toast.LENGTH_LONG).show();
        }catch (Exception e)
        {
            String xError=e.toString();
            Toast.makeText(getApplicationContext(), xError, Toast.LENGTH_LONG).show();
        }
    }
    public void new_user(View v) {
        Intent xIntent = new Intent(getApplicationContext(), Signup.class);
        startActivity(xIntent);
    }
    public void existing_user(View v) {
        Intent xIntent = new Intent(getApplicationContext(), Login.class);
        startActivity(xIntent);
    }
    public void change_imei(View v) {
        Intent xIntent = new Intent(getApplicationContext(), ChangeImei.class);
        startActivity(xIntent);

    }
    public void refresh(View v) {
        String xJsonThirdParty="";
            ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();

            nameValuePairs.add(new BasicNameValuePair("imei", xImei));
            try {
                HttpClient httpclient = new DefaultHttpClient();
                HttpPost httppost = new HttpPost(
                        "http://hellotamila.com/spiro/onetime/checkthirdparty.php");
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
                JSONArray jsonMainNode = json_data.optJSONArray("chat_data");

                for (int i = 0; i < jsonMainNode.length(); i++) {
                    JSONObject jsonChildNode = jsonMainNode.getJSONObject(i);

                    xJsonThirdParty  =jsonChildNode.optString("chattext");
                    //String outPut = xFullName;
                    //doctorlist.add(createDoctors("employees", outPut));
                }
               //code = (json_data.getInt("code"));

                if (!xJsonThirdParty.equalsIgnoreCase("") ) {
                    /*Toast.makeText(getBaseContext(), "User Name Logged in With   Another IMEI",
                            Toast.LENGTH_SHORT).show();*/
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
                    alertDialogBuilder.setMessage("User Name Logged in With   Another IMEI Allow to Access?" +xJsonThirdParty);
                    alertDialogBuilder.setPositiveButton("yes",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface arg0, int arg1) {
                                    update_access();
                                }
                            });

                    alertDialogBuilder.setNegativeButton("No",new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    });

                    AlertDialog alertDialog = alertDialogBuilder.create();
                    alertDialog.show();
                }

                else {
                    Toast.makeText(getBaseContext(), "Sorry, Try Again",
                            Toast.LENGTH_LONG).show();
                }
            } catch (Exception e) {
                Log.e("Fail 3", e.toString());
            }
        }


    public void update_access()
{
    ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();

    nameValuePairs.add(new BasicNameValuePair("imei", xImei));
    try {
        HttpClient httpclient = new DefaultHttpClient();
        HttpPost httppost = new HttpPost(
                "http://hellotamila.com/spiro/onetime/updatethirdpartyaccess.php");
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
                    Toast.makeText(getBaseContext(), "Allowed",
                            Toast.LENGTH_SHORT).show();
        }

        else {
            Toast.makeText(getBaseContext(), "Sorry, Try Again",
                    Toast.LENGTH_LONG).show();
        }
    } catch (Exception e) {
        Log.e("Fail 3", e.toString());
    }
}
}
