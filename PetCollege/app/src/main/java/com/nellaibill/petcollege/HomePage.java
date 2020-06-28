package com.nellaibill.petcollege;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

public class HomePage extends AppCompatActivity {
    Intent xIntent;
    SimpleGetterAndSetter obj;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_home_page);
        obj = new SimpleGetterAndSetter();
    }
    public void student_login(View v) {
        try {
            String xSessionStudent = obj.getSessionStudent();
            if (xSessionStudent.equalsIgnoreCase("Yes")) {
                xIntent = new Intent(getApplicationContext(), StudentHomePage.class);
                startActivity(xIntent);
            } else {
                xIntent = new Intent(getApplicationContext(), StudentLogin.class);
                startActivity(xIntent);
            }
        }catch(Exception e)
        {
            String xError=e.toString();
            Toast.makeText(getApplicationContext(),xError,Toast.LENGTH_LONG).show();
        }
    }
    public void admin_login(View v) {
        xIntent = new Intent(getApplicationContext(), AdminLogin.class);
        startActivity(xIntent);
    }

    public void staff_login(View v) {
        xIntent = new Intent(getApplicationContext(), StaffLogin.class);
       // xIntent.putExtra("url", "http://hellotamila.com/barani/petcollege/mark_attendence.php");
        startActivity(xIntent);
    }
    public void library_login(View v) {
        xIntent = new Intent(getApplicationContext(), LibraryLogin.class);
        startActivity(xIntent);
    }

}
