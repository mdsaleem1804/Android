package com.hellotamila.ah_and_009_hospial;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Registration extends AppCompatActivity {
    DataBaseConnection mCon;
    EditText a,b,c,d,e;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration);
        mCon = new DataBaseConnection(this);
        a = (EditText) findViewById(R.id.name);
        b = (EditText) findViewById(R.id.mobileno);
        c = (EditText) findViewById(R.id.generalpproblem);
        d = (EditText) findViewById(R.id.username);
        e = (EditText) findViewById(R.id.password);

    }
    public void hospital_save(View v) {
      mCon.insertUser(a.getText().toString(),b.getText().toString(),
              c.getText().toString(),d.getText().toString(),e.getText().toString());
        Toast.makeText(getApplicationContext(),"Data Entered Succesfully",Toast.LENGTH_LONG).show();
        Intent xIntent = new Intent(getApplicationContext(), HomePage.class);
        startActivity(xIntent);

}

}

