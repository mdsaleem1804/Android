package com.hellotamila.ah_and_010_mobileattendence;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class AdminHomePage extends AppCompatActivity {
    Intent xIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home_page);
    }

    public void add_stud(View v) {

            xIntent = new Intent(getApplicationContext(), Webform.class);
            xIntent.putExtra("url", "http://hellotamila.com/barani/attendence/studreg.php");
            startActivity(xIntent);
    }

    public void view_stud(View v) {
        xIntent = new Intent(getApplicationContext(), Webform.class);
        xIntent.putExtra("url", "http://hellotamila.com/barani/attendence/viewstudents.php");
        startActivity(xIntent);

    }
    public void add_staff(View v) {
        xIntent = new Intent(getApplicationContext(), StaffRegistration.class);
        startActivity(xIntent);

    }
    public void list_staff(View v) {
        xIntent = new Intent(getApplicationContext(), ListProblems.class);
        startActivity(xIntent);

    }
}
