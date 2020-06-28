package com.nellaibill.petcollege;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class StaffLogin extends AppCompatActivity {

    // CONNECTION_TIMEOUT and READ_TIMEOUT are in milliseconds
SimpleGetterAndSetter obj;
    public static final int CONNECTION_TIMEOUT=10000;
    public static final int READ_TIMEOUT=15000;
    private EditText etEmail;
    private EditText etPassword;
    String password;
    String xStaffUserName;
    DataBaseConnection mCon;
    private SQLiteDatabase db;
    private Cursor xCursor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.staff_login);
        mCon = new DataBaseConnection(this);
        obj=new SimpleGetterAndSetter();
        openDatabase();
        // Get Reference to variables
        etEmail = (EditText) findViewById(R.id.edt_username);
        etPassword = (EditText) findViewById(R.id.edt_password);

    }

    // Triggers when LOGIN Button clicked
    public void checkLogin(View arg0) {

        // Get text from email and passord field
        xStaffUserName = etEmail.getText().toString();
       password = etPassword.getText().toString();

        try {

            String xQry = "select * from staff_details where username ='" + xStaffUserName + "' and password='"+password+"'";

            xCursor = db.rawQuery(xQry, null);
            if(xCursor.getCount()>=1) {
                obj.setStaffName(xStaffUserName);
                Intent xIntent = new Intent(getApplicationContext(), StaffHomePage.class);
                startActivity(xIntent);
            }
            else
            {
                Toast.makeText(getApplicationContext(),"Invalid", Toast.LENGTH_LONG).show();
            }

        }catch(Exception e)
        {
            Toast.makeText(getApplicationContext(),e.toString(), Toast.LENGTH_LONG).show();
        }


    }

    protected void openDatabase() {
        db = openOrCreateDatabase("petcollege.db", Context.MODE_PRIVATE, null);
    }
}
