package com.nellaibill.petcollege;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class AdminHomePage extends AppCompatActivity {
    Intent xIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home_page);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }
    public void add_stud(View v) {
        xIntent = new Intent(getApplicationContext(), Webform.class);
        xIntent.putExtra("url", "http://hellotamila.com/barani/petcollege/studreg.php");
        startActivity(xIntent);
    }
    public void view_stud(View v) {
        xIntent = new Intent(getApplicationContext(), Webform.class);
        xIntent.putExtra("url", "http://hellotamila.com/barani/petcollege/viewstudents.php");
        startActivity(xIntent);

    }
    public void pay_fees(View v) {
        xIntent = new Intent(getApplicationContext(), Webform.class);
        xIntent.putExtra("url", "http://hellotamila.com/barani/petcollege/pay_fees.php");
        startActivity(xIntent);

    }
    public void fees_master(View v) {
        xIntent = new Intent(getApplicationContext(), Webform.class);
        xIntent.putExtra("url", "http://hellotamila.com/barani/petcollege/m_ledger_group.php");
        startActivity(xIntent);

    }
    public void news_master(View v) {
        xIntent = new Intent(getApplicationContext(), Webform.class);
        xIntent.putExtra("url", "http://hellotamila.com/barani/petcollege/news_feed.php");
        startActivity(xIntent);
    }

    public void add_staff(View v) {
        xIntent = new Intent(getApplicationContext(), StaffRegistration.class);
        startActivity(xIntent);
    }
    public void list_staff(View v) {
        xIntent = new Intent(getApplicationContext(), ListStaff.class);
        startActivity(xIntent);
    }
    public void logout(View v) {
        xIntent = new Intent(getApplicationContext(), HomePage.class);
        this.finish();
        startActivity(xIntent);
    }
}
