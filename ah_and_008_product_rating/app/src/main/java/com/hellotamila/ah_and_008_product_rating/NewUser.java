package com.hellotamila.ah_and_008_product_rating;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class NewUser extends AppCompatActivity {
    DataBaseConnection mCon;
    EditText a, b,c,d;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);
        mCon = new DataBaseConnection(this);
        a = (EditText) findViewById(R.id.your_full_name);
        b = (EditText) findViewById(R.id.your_email_address);
        c = (EditText) findViewById(R.id.edt_username);
        d = (EditText) findViewById(R.id.edt_password);

    }
    public void user_save(View v) {
        mCon.insertUserRequests(a.getText().toString(),b.getText().toString(),c.getText().toString(),d.getText().toString());
        Toast.makeText(getApplicationContext(),"New User Inserted  Succesfully",Toast.LENGTH_LONG).show();
        Intent xIntent = new Intent(getApplicationContext(), AddProducts.class);
        startActivity(xIntent);
    }

}
