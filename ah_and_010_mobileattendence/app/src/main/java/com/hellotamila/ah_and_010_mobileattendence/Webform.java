package com.hellotamila.ah_and_010_mobileattendence;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;


public class Webform extends AppCompatActivity {
    WebView xWebView;
    WebSettings xWebSettings;
    String xUrl;
    Bundle xBundle;
    Intent xIntent;

    BottomNavigationView navigation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        setContentView(R.layout.webform);
        xWebView = (WebView) findViewById(R.id.webView1);

        xWebSettings = xWebView.getSettings();
        //customBar();
        xWebSettings.setJavaScriptEnabled(true);
        xBundle = getIntent().getExtras();
        if (xBundle != null) {
            xUrl = xBundle.getString("url");
            if (xUrl != null)
                xWebView.loadUrl(xUrl);

        }

    }




}




