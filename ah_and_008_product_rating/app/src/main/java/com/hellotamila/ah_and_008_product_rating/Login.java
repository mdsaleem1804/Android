package com.hellotamila.ah_and_008_product_rating;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class Login extends AppCompatActivity {
    DataBaseConnection mCon;
    private SQLiteDatabase db;
    private Cursor xCursor;
    EditText a, b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_login);
        mCon = new DataBaseConnection(this);
        a = (EditText) findViewById(R.id.edt_username);
        b = (EditText) findViewById(R.id.edt_password);
        openDatabase();

    }
    public void new_user(View v) {
        Intent xIntent = new Intent(getApplicationContext(), NewUser.class);
        startActivity(xIntent);
    }
    protected void openDatabase() {
        db = openOrCreateDatabase("ah_and_008_product_rating.db", Context.MODE_PRIVATE, null);
    }
    public void login(View v) {
        try {
            String xQry = "select * from user_requests where username ='" + a.getText().toString() + "' and password='"+b.getText().toString()+"'";

            xCursor = db.rawQuery(xQry, null);
            if(xCursor.getCount()>=1) {

                Intent xIntent = new Intent(getApplicationContext(), AddProducts.class);
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
}
