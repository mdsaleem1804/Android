package com.example.and_af_donor;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Edit_Or_Delete_Authication extends Activity {
     String task;
     int acheivedId;
     EditText passwordEditText;
    String nameS ,phoneS, emailS , bloodGroupS, lastDateS;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit__or__delete__authication);
        passwordEditText= (EditText) findViewById(R.id.passwordEditText);

        acheivedId=  getIntent().getIntExtra("id",0);
        nameS = getIntent().getStringExtra("name");
        phoneS = getIntent().getStringExtra("phone");

        emailS = getIntent().getStringExtra("email");
        bloodGroupS = getIntent().getStringExtra("bloodGroup");
        lastDateS = getIntent().getStringExtra("lastDate");

    }





    public void submit(View view) {
        task = getIntent().getStringExtra("task");

        String matchPassword= passwordEditText.getText().toString().trim();
       if(matchPassword.equals("admin12345"))
       {
           if(task.equals("delete"))
           {


               DbHelper dbHelper=new DbHelper(Edit_Or_Delete_Authication.this);
               dbHelper.delete(acheivedId);

               startActivity(new Intent(Edit_Or_Delete_Authication.this,MainActivity.class));
               finish();
           }
           else if(task.equals("edit"))
           {


               DbHelper dbHelper=new DbHelper(Edit_Or_Delete_Authication.this);
               Intent intent= new Intent(Edit_Or_Delete_Authication.this,AddContacts.class);

               intent.putExtra("task","edit");
               intent.putExtra("id",acheivedId);
               intent.putExtra("name",nameS);
               intent.putExtra("phone",phoneS);
               intent.putExtra("email",emailS);
               intent.putExtra("bloodGroup",bloodGroupS);
               intent.putExtra("lastDate",lastDateS);
               startActivity(intent);
               finish();
           }
       }
       else
       {
           Toast.makeText(this,"Please enter correct password",Toast.LENGTH_LONG).show();
       }

    }
}
