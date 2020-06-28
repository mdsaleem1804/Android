package com.hellotamila.ah_and_007;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class AdminLogin extends AppCompatActivity {
    private EditText etEmail;
    private EditText etPassword;
    String password;
    String email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_admin_login);
        etEmail = (EditText) findViewById(R.id.edt_username);
        etPassword = (EditText) findViewById(R.id.edt_password);

    }
    public void admin_mainpage(View v) {
        email = etEmail.getText().toString();
        password = etPassword.getText().toString();
        if(email.equalsIgnoreCase("admin") && password.equalsIgnoreCase("admin")) {
            Intent xIntent = new Intent(getApplicationContext(), ListUserRequests.class);
           startActivity(xIntent);
            AdminLogin.this.finish();
        }
        else
        {
            Toast.makeText(getApplicationContext(),"Wrong Data Provided", Toast.LENGTH_LONG).show();
        }

    }
}
