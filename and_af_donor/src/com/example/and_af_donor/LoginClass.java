package com.example.and_af_donor;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Hasibuzzaman on 7/14/2016.
 */
public  class LoginClass {
    private TextView userName;
    private TextView password;
    private Context context;

    private  SharedPreferences  sharedPreferences;
     SharedPreferences.Editor editor;
    private static final String ACCESS_FILE="Accessability";
    private static final String USER_NAME="username";
    private static final String PASS_WORD="pass";

    public LoginClass(Context context) {
        this.context = context;
        sharedPreferences=context.getSharedPreferences(ACCESS_FILE,Context.MODE_PRIVATE);
        editor=sharedPreferences.edit();
    }

    public LoginClass() {

    }

    public void login(String userNameString, String passwordData)
    {

        if((userNameString.equals("admin")) && (passwordData.equals("admin12345")))
        {
            Intent intent=new Intent(context,MainActivity.class);
            context.startActivity(intent);
            editor.putString(USER_NAME,"admin");
            editor.putString(PASS_WORD,"admin12345");
            editor.commit();

        }
        else
        {
           Toast.makeText(context,"UserName And Password Mismatch",Toast.LENGTH_LONG).show();

        }



    }

    public void removing()
    {
        editor.remove(USER_NAME);
        editor.remove(PASS_WORD);
        editor.commit();

    }
}
