package com.hellotamila.ah_and_001_security;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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

public class Grid_Confirm_Prompt extends Activity {
    String xMobileNo,xUserName,xImei,xImage1,xImage2;
    String buttonText;
    Intent xIntent;
    SimpleGetterAndSetter obj;
    String id;
    InputStream is = null;
    String result = null;
    String line = null;
    int code;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grid_conirm_prompt);
            obj = new SimpleGetterAndSetter();
        }
    public void go_conirm_prompt(View v) {
        // xIntent = new Intent(getApplicationContext(), SignUp_Final.class);
        try {
            Button b = (Button)v;
             buttonText = b.getText().toString();
            obj.setImage2(buttonText);
            if (obj.getImage1().equalsIgnoreCase(obj.getImage2())) {
                Toast.makeText(getApplicationContext(), "Matched", Toast.LENGTH_LONG).show();
                updateImage();
            } else {
                Toast.makeText(getApplicationContext(), "Not Matched", Toast.LENGTH_LONG).show();
            }
            //  startActivity(xIntent);
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
        }
    }

    public void updateImage() {
        ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        xMobileNo=obj.getMobileno();
        xUserName=obj.getUsername();
        xImei=obj.getImei();
        nameValuePairs.add(new BasicNameValuePair("username",xUserName));
        nameValuePairs.add(new BasicNameValuePair("mobileno",xMobileNo));
        nameValuePairs.add(new BasicNameValuePair("imei", xImei));
        nameValuePairs.add(new BasicNameValuePair("image", obj.getImage2()));
        try {
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost(
                    "http://hellotamila.com/spiro/onetime/updatereg.php");
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
                Toast.makeText(getBaseContext(), "Image Registered Successfully",
                        Toast.LENGTH_SHORT).show();
                xIntent = new Intent(getApplicationContext(), HomePage.class);;
                startActivity(xIntent);

            } else {
                Toast.makeText(getBaseContext(), "Sorry, Try Again",
                        Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            Log.e("Fail 3", e.toString());
        }
    }
}