package com.example.and_af_donor;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

public class DialogOptions extends Activity {

    int acheivedId; String nameS ,phoneS, emailS , bloodGroupS, lastDateS;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_options);
        acheivedId=  getIntent().getIntExtra("id",0);
        nameS = getIntent().getStringExtra("name");
        phoneS = getIntent().getStringExtra("phone");
        emailS = getIntent().getStringExtra("email");
        bloodGroupS = getIntent().getStringExtra("bloodGroup");
        lastDateS = getIntent().getStringExtra("lastDate");
    }



    public void deletData(View view) {

        Intent intent=new Intent(DialogOptions.this,Edit_Or_Delete_Authication.class);
        intent.putExtra("task","delete");
        intent.putExtra("id",acheivedId);
        startActivity(intent);
    }



    public void call(View view) {
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+phoneS.trim()));
        startActivity(intent);

        finish();
    }

    public void email(View view) {
        Intent mailIntent = new Intent(Intent.ACTION_VIEW,Uri.parse("mailto:"+emailS.trim()));
        Intent chooser= Intent.createChooser(mailIntent, "email send");
        startActivity(mailIntent);
        finish();
    }

    public void edit(View view) {

        Intent intent=new Intent(DialogOptions.this,Edit_Or_Delete_Authication.class);
        intent.putExtra("task","edit");
        intent.putExtra("id",acheivedId);
        intent.putExtra("name",nameS);
        intent.putExtra("phone",phoneS);
        intent.putExtra("email",emailS);
        intent.putExtra("bloodGroup",bloodGroupS);
        intent.putExtra("lastDate",lastDateS);
        startActivity(intent);
    }
}
