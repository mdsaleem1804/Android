package com.nellaibill.petcollege;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class FacultyDepartment extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty_department);
    }
    public void csc(View v) {
        Intent xIntent = new Intent(getApplicationContext(), Webform.class);
        xIntent.putExtra("url", "file:///android_asset/cse.html");
        startActivity(xIntent);
    }
    public void ece(View v) {
        Intent xIntent = new Intent(getApplicationContext(), Webform.class);
        xIntent.putExtra("url", "file:///android_asset/ece.html");
        startActivity(xIntent);
    }
    public void eee(View v) {
        Intent xIntent = new Intent(getApplicationContext(), Webform.class);
        xIntent.putExtra("url", "file:///android_asset/eee.html");
        startActivity(xIntent);
    }
}
