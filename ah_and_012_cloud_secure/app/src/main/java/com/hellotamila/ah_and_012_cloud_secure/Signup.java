package com.hellotamila.ah_and_012_cloud_secure;

import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
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
import java.util.Random;

public class Signup extends AppCompatActivity {
            int code;
            Intent xIntent;
            String id;
            InputStream is = null;
            String result = null;
            String line = null;
            String xMobileNo,xName,xEmail,xPassword;
            EditText xEdtMobileNo,xEdtName,xEdtEmail,xEdtPassword;
            String xImei;
            SimpleGetterAndSetter obj;

    Random rand;
    int n;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_signup);
        xEdtName = (EditText) findViewById(R.id.f_edt_name);
        xEdtMobileNo = (EditText) findViewById(R.id.f_edt_mobileno);
        xEdtEmail = (EditText) findViewById(R.id.f_edt_email);
        xEdtPassword = (EditText) findViewById(R.id.f_edt_password);
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
    public final static boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }

    public void sign_up(View v) {
        ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        xMobileNo=xEdtMobileNo.getText().toString();
        xName=xEdtName.getText().toString();
        xEmail=xEdtEmail.getText().toString();
      //  isValidEmail(xEmail);
        xPassword=xEdtPassword.getText().toString();
        obj.setDetails(xName,xMobileNo);
        nameValuePairs.add(new BasicNameValuePair("name",xName));
        nameValuePairs.add(new BasicNameValuePair("mobileno",xMobileNo));
        nameValuePairs.add(new BasicNameValuePair("email", xEmail));
        nameValuePairs.add(new BasicNameValuePair("password", xPassword));
        try {
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost(
                    "http://hellotamila.com/spiro/ah_and_012_msuniv/insert.php");
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
                Intent xIntent = new Intent(getApplicationContext(), MainActivity.class);
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




