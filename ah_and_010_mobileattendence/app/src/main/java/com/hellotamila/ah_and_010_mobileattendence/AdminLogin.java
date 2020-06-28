package com.hellotamila.ah_and_010_mobileattendence;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AdminLogin extends AppCompatActivity {

    // CONNECTION_TIMEOUT and READ_TIMEOUT are in milliseconds

    public static final int CONNECTION_TIMEOUT=10000;
    public static final int READ_TIMEOUT=15000;
    private EditText etEmail;
    private EditText etPassword;
    String password;
    String email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_login);

        // Get Reference to variables
        etEmail = (EditText) findViewById(R.id.edt_username);
        etPassword = (EditText) findViewById(R.id.edt_password);

    }

    // Triggers when LOGIN Button clicked
    public void checkLogin(View arg0) {

        // Get text from email and passord field
        email = etEmail.getText().toString();
       password = etPassword.getText().toString();
       if(email.equalsIgnoreCase("admin") && password.equalsIgnoreCase("admin")) {
           Intent intent = new Intent(AdminLogin.this, AdminHomePage.class);
           startActivity(intent);
           AdminLogin.this.finish();
       }
       else
       {
           Toast.makeText(getApplicationContext(),"Wrong Data Provided", Toast.LENGTH_LONG).show();
       }
    }

}
