package com.hellotamila.ah_and_009_hospial;


import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebSettings;
import android.webkit.WebView;


public class Webform extends AppCompatActivity {
    WebView xWebView;
    WebSettings xWebSettings;
    String xUrl;
    Bundle xBundle;
    Intent xIntent;
    SimpleGetterAndSetter obj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        setContentView(R.layout.webform);
        obj = new SimpleGetterAndSetter();
        xUrl = "http://maps.google.com/?q=" + obj.getMedLat() + "," + obj.getMedLong();
        xWebView = (WebView) findViewById(R.id.webView1);

        xWebSettings = xWebView.getSettings();
        //customBar();
        xWebSettings.setJavaScriptEnabled(true);

        if (xUrl != null)
           xWebView.loadUrl(xUrl);

    }



}





