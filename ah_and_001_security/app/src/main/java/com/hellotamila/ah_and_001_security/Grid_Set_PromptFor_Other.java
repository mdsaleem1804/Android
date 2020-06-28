package com.hellotamila.ah_and_001_security;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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

public class Grid_Set_PromptFor_Other extends Activity {
    Intent xIntent;
    SimpleGetterAndSetter obj;
    Button a,b,c,d,e,f,g,h,i;
    int code;
    String xChatText;
    String id;
    InputStream is = null;
    String result = null;
    String line = null;
    String xMobileNo,xUserName;
    EditText xEdtMobileNo,xEdtUserName;
    String xImei;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grid_set_prompt);
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        a = (Button) findViewById(R.id.a);
        b = (Button) findViewById(R.id.b);
        c = (Button) findViewById(R.id.c);
        d = (Button) findViewById(R.id.d);
        e = (Button) findViewById(R.id.e);
        f = (Button) findViewById(R.id.f);
        g = (Button) findViewById(R.id.g);
        h= (Button) findViewById(R.id.h);
        i = (Button) findViewById(R.id.i);

        obj = new SimpleGetterAndSetter();
        Toast.makeText(getApplicationContext(), obj.getUsername(),Toast.LENGTH_LONG).show();
    }

    public void go_conirm_prompt(View v) {
       // xIntent = new Intent(getApplicationContext(), Grid_Confirm_Prompt.class);
        Button b = (Button)v;
        String buttonText = b.getText().toString();
        obj.setImage1(buttonText);
        checkUser();
        //startActivity(xIntent);
    }


    public void checkUser() {
        ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
       // xMobileNo=obj.getUsername();
        xUserName=obj.getUsername();
        xImei=obj.getImei();
       // obj.setDetails(xUserName,xMobileNo,xImei);
        nameValuePairs.add(new BasicNameValuePair("username",xUserName));
        //nameValuePairs.add(new BasicNameValuePair("mobileno",xMobileNo));
        nameValuePairs.add(new BasicNameValuePair("imei", xImei));
        try {
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost(
                    "http://hellotamila.com/spiro/onetime/checkuserforanotherimei.php");
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
                String xActualImage = (json_data.getString("image"));
                String xComparingimage=obj.getImage1();
                if(xComparingimage.equalsIgnoreCase(xActualImage))
                {
                  xIntent = new Intent(getApplicationContext(), User_BankPage.class);
                  //  xIntent = new Intent(getApplicationContext(), Information.class);
                    startActivity(xIntent);
                    Toast.makeText(Grid_Set_PromptFor_Other.this,"Welcome To Transaction Page",Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(Grid_Set_PromptFor_Other.this,"Verification Image Not Matched    ",Toast.LENGTH_LONG).show();
                }




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