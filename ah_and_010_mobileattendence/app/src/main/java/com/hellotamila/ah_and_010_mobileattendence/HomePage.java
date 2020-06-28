package com.hellotamila.ah_and_010_mobileattendence;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class HomePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepage);
    }
    public void staff_login(View v) {
        Intent xIntent = new Intent(getApplicationContext(), StaffLogin.class);
        startActivity(xIntent);
    }
    public void admin_login(View v) {
        Intent xIntent = new Intent(getApplicationContext(), AdminLogin.class);
        startActivity(xIntent);
    }
}
