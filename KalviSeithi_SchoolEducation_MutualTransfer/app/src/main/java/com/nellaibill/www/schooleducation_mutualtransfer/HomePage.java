package com.nellaibill.www.schooleducation_mutualtransfer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;
import java.util.regex.Pattern;

public class HomePage extends Activity {
    private TextView mLevelTextView;
    Button button1;
    Intent xIntent;
    DatabaseHandler db;
    EditText xEdtMobileNo;
    String xPhoneNumber;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepage);
        button1 =(Button) findViewById(R.id.btngo);
        xEdtMobileNo =(EditText) findViewById(R.id.editText2);
        xIntent = new Intent(this, MainActivity.class);
        xPhoneNumber="0000000000";
         db = new DatabaseHandler(this);
        List<Contact> contacts = db.getAllContacts();
        for (Contact cn : contacts) {
            xPhoneNumber =cn.getPhoneNumber();
        }
        if(xPhoneNumber!="0000000000")
        {
            xIntent.putExtra("passmobileno",xPhoneNumber);
            startActivity(xIntent);
        }
        else {
            db.addContact(new Contact("Test", xPhoneNumber));

        }
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isValidMobile(xEdtMobileNo.getText().toString())){
                    db.updateContact(new Contact(1,"Test", xEdtMobileNo.getText().toString()));
                    finish();
                    xIntent.putExtra("passmobileno",1);
                    startActivity(xIntent);
                }

            }
        });

    }
    private boolean isValidMobile(String phone) {
        boolean check=false;
        if(!Pattern.matches("[a-zA-Z]+", phone)) {
            if(phone.length() < 6 || phone.length() > 13) {
                // if(phone.length() != 10) {
                check = false;
                xEdtMobileNo.setError("Not Valid Number");
            } else {
                check = true;
            }
        } else {
            check=false;
        }
        return check;
    }
}