package com.hellotamila.agrofarming;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

public class CerealsHomePage extends Activity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_cereals_home_page);
    }
    public void rice(View v) {
        Intent xIntent = new Intent(getApplicationContext(), Webform.class);
        xIntent.putExtra("url", "http://hellotamila.com/spiro/agri/Rice.pdf");
        startActivity(xIntent);
    }
}
