package com.hellotamila.ah_and_001_security;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class ValidateOtp extends AppCompatActivity {
    SimpleGetterAndSetter obj;
    EditText xEdtOtp;
    String xUserName;
    String xImei;
    int code;
    Intent xIntent;
    String xChatText;
    String id;
    InputStream is = null;
    String result = null;
    String line = null;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.validateotp);
        obj= new SimpleGetterAndSetter();
        xEdtOtp = (EditText) findViewById(R.id.edtOtp);
    }
    public void validate_otp(View v) {
        if(obj.getOtp().equalsIgnoreCase(xEdtOtp.getText().toString()))
        {
            updateImei();
          //  startActivity(getIntent());
        }
        else
        {
            Toast.makeText(getBaseContext(), "OTP is Wrong",
                    Toast.LENGTH_LONG).show();
            xEdtOtp.setText("");
        }
    }

    public void updateImei() {
        ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        xUserName=obj.getUsername();
        xImei =obj.getImei();
        nameValuePairs.add(new BasicNameValuePair("username",xUserName));
        nameValuePairs.add(new BasicNameValuePair("imei", xImei));
        try {
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost(
                    "http://hellotamila.com/spiro/onetime/updateimei.php");
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
                Toast.makeText(getBaseContext(), "Validation Succesfully Completed",
                        Toast.LENGTH_LONG).show();
                Toast.makeText(getBaseContext(), "Imei/Device Id Changed",
                        Toast.LENGTH_LONG).show();
                Intent xIntent = new Intent(getApplicationContext(), HomePage.class);
                startActivity(xIntent);
                finish();
            } else {
                Toast.makeText(getBaseContext(), "Sorry, Try Again",
                        Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            Log.e("Fail 3", e.toString());
        }
    }

}
