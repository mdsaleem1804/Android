package com.hellotamila.ah_and_009_hospial;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class HomePage extends AppCompatActivity {
    DataBaseConnection mCon;
    private SQLiteDatabase db;
    private Cursor xCursor;
    EditText a, b;
    SimpleGetterAndSetter obj;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepage);
        mCon = new DataBaseConnection(this);
        a = (EditText) findViewById(R.id.edt_username);
        b = (EditText) findViewById(R.id.edt_password);
        openDatabase();
        obj= new SimpleGetterAndSetter();
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
    }
    public void login(View v) {
        try {

            String xQry = "select * from user where username ='" + a.getText().toString() + "' and password='"+b.getText().toString()+"'";

            xCursor = db.rawQuery(xQry, null);
            if(xCursor.getCount()>=1) {
                obj.setUserName(a.getText().toString());
                Intent xIntent = new Intent(getApplicationContext(), DoctorSelection.class);
                startActivity(xIntent);
            }
            else
            {
                Toast.makeText(getApplicationContext(),"Invalid",Toast.LENGTH_LONG).show();
            }

        }catch(Exception e)
        {
            Toast.makeText(getApplicationContext(),e.toString(),Toast.LENGTH_LONG).show();
        }


    }
    public void panic(View v) {
        Intent xIntent = new Intent(getApplicationContext(), Medical.class);
        startActivity(xIntent);
    }
    public void listproblem(View v) {
        Intent xIntent = new Intent(getApplicationContext(), ListProblems.class);
        startActivity(xIntent);
    }
    public void register(View v) {
        Intent xIntent = new Intent(getApplicationContext(), Registration.class);
        startActivity(xIntent);
    }
    protected void openDatabase() {
        db = openOrCreateDatabase("ah_and_009_hospial.db", Context.MODE_PRIVATE, null);
    }

}















































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































