package com.hellotamila.ah_and_011_book;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;


public class HomePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepage);

    }

    public void user_login(View v) {
        Intent xIntent = new Intent(getApplicationContext(), StudentLogin.class);
        startActivity(xIntent);
    }

    public void library_login(View v) {
        Intent xIntent = new Intent(getApplicationContext(), LibraryLogin.class);
        startActivity(xIntent);
    }

    public void admin_login(View v) {
        Intent xIntent = new Intent(getApplicationContext(), AdminLogin.class);
        startActivity(xIntent);
    }

}
