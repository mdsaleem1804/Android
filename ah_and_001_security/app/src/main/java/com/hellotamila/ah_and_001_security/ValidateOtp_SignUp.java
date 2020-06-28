package com.hellotamila.ah_and_001_security;

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

public class ValidateOtp_SignUp extends AppCompatActivity {
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
            Intent xIntent = new Intent(getApplicationContext(), Grid_Set_Prompt.class);
            startActivity(xIntent);
        }
        else
        {
            Toast.makeText(getBaseContext(), "OTP is Wrong",
                    Toast.LENGTH_LONG).show();
            xEdtOtp.setText("");
        }
    }


}