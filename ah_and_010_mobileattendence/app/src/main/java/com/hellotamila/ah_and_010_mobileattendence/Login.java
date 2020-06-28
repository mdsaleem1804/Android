package com.hellotamila.ah_and_010_mobileattendence;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;


public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_login);

    }
    public void login(View v) {
        Intent xIntent = new Intent(getApplicationContext(), HomePage.class);
        startActivity(xIntent);
    }

}
