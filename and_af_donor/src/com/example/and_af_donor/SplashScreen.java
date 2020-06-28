package com.example.and_af_donor;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;



public class SplashScreen extends Activity {
    ImageView imageView;
    private SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    private static final String ACCESS_FILE="Accessability";
    private static final String USER_NAME="username";
    private static final String PASS_WORD="pass";
    String userName, password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        imageView = (ImageView) findViewById(R.id.imageView3);
        sharedPreferences=getSharedPreferences(ACCESS_FILE, Context.MODE_PRIVATE);
        editor=sharedPreferences.edit();

        userName= sharedPreferences.getString(USER_NAME,"abc");
        password = sharedPreferences.getString(PASS_WORD, "abc");

       // Toast.makeText(SplashScreen.this, "Username : "+ userName, Toast.LENGTH_LONG).show();
       // Toast.makeText(SplashScreen.this, "pass : "+ password, Toast.LENGTH_LONG).show();

       /* Picasso.with(this)
                .load(R.drawable.splash)
                .into(imageView);*/


        new Thread()
        {
            @Override
            public void run() {
                try {
                    sleep(4000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


                if(userName.equals("admin") && password.equals("admin12345"))
                {
                    startActivity(new Intent(SplashScreen.this,MainActivity.class));
                    finish();
                }
                else
                {
                    startActivity(new Intent(SplashScreen.this,Login.class));
                    finish();
                }

            }
        }.start();




    }
}
