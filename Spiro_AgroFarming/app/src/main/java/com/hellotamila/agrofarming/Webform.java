package com.hellotamila.agrofarming;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;


public class Webform extends AppCompatActivity {
    WebView xWebView;
    WebSettings xWebSettings;
    String xUrl,xImage;
    Bundle xBundle;
    Intent xIntent;

    ImageView xImageView;
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
        xImageView = (ImageView) findViewById(R.id.imageView);


        xWebSettings = xWebView.getSettings();
        xWebSettings.setJavaScriptEnabled(true);
        xBundle = getIntent().getExtras();
        if (xBundle != null) {
            xUrl = xBundle.getString("url");
            xImage = xBundle.getString("image");
            if(xImage.equalsIgnoreCase("rice"))
            {
                xImageView.setImageResource(R.drawable.rice);
            }
            if(xImage.equalsIgnoreCase("blackgram"))
            {
                xImageView.setImageResource(R.drawable.blackgram);
            }
            if(xImage.equalsIgnoreCase("greengram"))
            {
                xImageView.setImageResource(R.drawable.blackgram);
            }

            if(xImage.equalsIgnoreCase("maize"))
            {
                xImageView.setImageResource(R.drawable.maize);
            }
            if(xImage.equalsIgnoreCase("cotton"))
            {
                xImageView.setImageResource(R.drawable.cotton);
            }
            if(xImage.equalsIgnoreCase("sugarcane"))
            {
                xImageView.setImageResource(R.drawable.sugarcane);
            }

          //  WebView webView=new WebView(Webform.this);
//            webView.getSettings().setJavaScriptEnabled(true);
            xWebView.getSettings().setPluginState(WebSettings.PluginState.ON);

            //---you need this to prevent the webview from
            // launching another browser when a url
            // redirection occurs---
            xWebView.setWebViewClient(new Callback());

           // String pdfURL = "http://dl.dropboxusercontent.com/u/37098169/Course%20Brochures/AND101.pdf";
           xWebView.loadUrl(
                    "http://docs.google.com/gview?embedded=true&url=" + xUrl);

            //setContentView(xWebView);
        }


            //if (xUrl != null)
              // xWebView.loadUrl(xUrl);
            //xWebView.loadUrl("http://docs.google.com/gview?embedded=true&url="
              //      + xUrl);
        }


    private class Callback extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(
                WebView view, String url) {
            return(false);
        }
    }
    }









