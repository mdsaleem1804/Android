package com.hellotamila.ah_and_011_book;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class LibraryLogin extends AppCompatActivity {
    private EditText etEmail;
    private EditText etPassword;
    String password;
    String email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_library_login);
        etEmail = (EditText) findViewById(R.id.edt_lib_username);
        etPassword = (EditText) findViewById(R.id.edt_lib_password);

    }
    public void library_mainpage(View v) {
        email = etEmail.getText().toString();
        password = etPassword.getText().toString();
        if(email.equalsIgnoreCase("library") && password.equalsIgnoreCase("library")) {
            Intent xIntent = new Intent(getApplicationContext(), LibraryHomePage.class);
            startActivity(xIntent);
            LibraryLogin.this.finish();
        }
        else
        {
            Toast.makeText(getApplicationContext(),"Wrong Data Provided", Toast.LENGTH_LONG).show();
        }

    }
}
