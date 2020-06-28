package com.hellotamila.agrofarming;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

public class StartupPage extends Activity {
    Intent xIntent;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.startuppage);
    }
    public void gotomainpage(View v) {
        xIntent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(xIntent);
    }
}
