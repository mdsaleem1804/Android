package com.hellotamila.ah_and_007;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

public class HomePage extends AppCompatActivity {
    Intent xIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try{
            setContentView(R.layout.homepage);
        }
        catch(Exception e)
        {
            String xError=e.toString();
            Toast.makeText(getApplicationContext(),xError,Toast.LENGTH_LONG).show();
        }

    }
    public void user_requests(View v) {
        Intent xIntent = new Intent(getApplicationContext(), UserRequests.class);
        startActivity(xIntent);
    }
    public void admin_login(View v) {
        Intent xIntent = new Intent(getApplicationContext(), AdminLogin.class);
        startActivity(xIntent);
    }
    public void police_login(View v) {
        Intent xIntent = new Intent(getApplicationContext(), AddFir.class);
        startActivity(xIntent);
    }
    public void hospital_login(View v) {
        Intent xIntent = new Intent(getApplicationContext(), AddHospitalData.class);
        startActivity(xIntent);
    }
}
