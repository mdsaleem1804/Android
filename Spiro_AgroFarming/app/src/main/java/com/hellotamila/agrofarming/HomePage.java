package com.hellotamila.agrofarming;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

public class HomePage extends Activity {
    Intent xIntent;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_home_page);
        }
    public void cereals(View v) {
        xIntent = new Intent(getApplicationContext(), CerealsHomePage.class);
        startActivity(xIntent);
    }

}
