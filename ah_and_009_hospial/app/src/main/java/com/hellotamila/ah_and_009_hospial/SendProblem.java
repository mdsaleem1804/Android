package com.hellotamila.ah_and_009_hospial;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class SendProblem extends AppCompatActivity {
    DataBaseConnection mCon;
    EditText a;
    SimpleGetterAndSetter obj;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.send_problem);
        obj= new SimpleGetterAndSetter();
        mCon = new DataBaseConnection(this);
        a = (EditText) findViewById(R.id.problem);
    }
    public void send(View v) {
        mCon.insertUserProblemtoDoctor(obj.getUserName(),obj.getDoctor(),a.getText().toString());
        Toast.makeText(getApplicationContext(),"Your Record Saved Succesfully",Toast.LENGTH_LONG).show();
    }
}















































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































