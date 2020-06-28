package com.hellotamila.ah_and_001_security;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
public class SignUp_Final  extends Activity {
    String xMobileNo,xImage1,xImage2;
    Intent xIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setup_final);
        Bundle b = new Bundle();
        b = getIntent().getExtras();
        xMobileNo = b.getString("mobileno");
        xImage1= b.getString("image1");
        xImage2= b.getString("image2");
        Toast.makeText(getApplicationContext(),xMobileNo,Toast.LENGTH_LONG).show();
        Toast.makeText(getApplicationContext(),xImage1,Toast.LENGTH_LONG).show();
        Toast.makeText(getApplicationContext(),xImage2,Toast.LENGTH_LONG).show();
    }
    public void final_signup(View v) {

    }
}