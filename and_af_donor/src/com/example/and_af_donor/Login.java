package com.example.and_af_donor;


import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Login extends Activity {
    EditText idEt;
    EditText passwordEt;
    LoginClass loginClass;
    String userName, password;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        idEt= (EditText) findViewById(R.id.id);
        passwordEt= (EditText) findViewById(R.id.password);

        loginClass=new LoginClass(Login.this);
    }


    public void login(View view) {

        String s = idEt.getText().toString();
        String s1 = passwordEt.getText().toString();
        loginClass.login(s,s1);

    }


}
