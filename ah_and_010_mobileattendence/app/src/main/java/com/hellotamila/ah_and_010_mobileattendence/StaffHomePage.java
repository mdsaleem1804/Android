package com.hellotamila.ah_and_010_mobileattendence;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class StaffHomePage extends AppCompatActivity {
    Intent xIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_staff_home_page);
    }

    public void mark_attendence(View v) {

            xIntent = new Intent(getApplicationContext(), Webform.class);
        xIntent.putExtra("url", "http://hellotamila.com/barani/attendence/mark_attendence.php");
            startActivity(xIntent);
    }

    public void view_attendence(View v) {
        xIntent = new Intent(getApplicationContext(), Webform.class);
        xIntent.putExtra("url", "http://hellotamila.com/barani/attendence/view_attendence.php");
        startActivity(xIntent);

    }


}
