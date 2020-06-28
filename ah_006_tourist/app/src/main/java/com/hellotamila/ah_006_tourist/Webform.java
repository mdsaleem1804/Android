package com.hellotamila.ah_006_tourist;

import android.content.Intent;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.webkit.SslErrorHandler;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;


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
        WebSettings settings = xWebView.getSettings();
        settings.setJavaScriptEnabled(true);
        // By using this method together with the overridden method onReceivedSslError()
        // you will avoid the "WebView Blank Page" problem to appear. This might happen if you
        // use a "https" url!

        settings.setLoadWithOverviewMode(true);
        settings.setUseWideViewPort(true);
        settings.setAppCacheEnabled(true);
        settings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
/*        settings.setBuiltInZoomControls(true);
        settings.setDisplayZoomControls(true);*/
        settings.setDomStorageEnabled(true);
        xWebView.clearHistory();
        xWebView.clearFormData();
        xWebView.clearCache(true);
        settings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        xWebView.setWebViewClient(new MyWebViewClient());
        xBundle = getIntent().getExtras();
        if (xBundle != null) {
            xUrl = xBundle.getString("url");
            if (xUrl != null)
                xWebView.loadUrl(xUrl);
        }
/*
        xWebView.setWebChromeClient(new WebChromeClient());
        xWebView.setWebViewClient(new WebViewClient());
        xBundle = getIntent().getExtras();
        if (xBundle != null) {
            xUrl = xBundle.getString("url");
            if (xUrl != null)
                xWebView.loadUrl(xUrl);

        }
*/

    }
    private class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            //if (Uri.parse(url).getHost().startsWith("https://")) {
            if (url.startsWith("http://")){
                return false;
                // This is your web site, so do not override; let the WebView to load the page

            }
            else {
                // Otherwise, the link is not for a page on my site, so launch another Activity that handles URLs

                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(intent);
                return true;
            }
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            /*if (xProgress.isShown()) {
                xProgress.setVisibility(View.GONE);
            }
            // Check web view back history availability
            if(mWebView.canGoBack()){
                mButtonBack.setEnabled(true);
            }else {
                mButtonBack.setEnabled(false);
            }

            // Check web view forward history availability
            if(mWebView.canGoForward()){
                mButtonForward.setEnabled(true);
            }else {
                mButtonForward.setEnabled(false);
            }*/
        }
        @Override
        public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
            super.onReceivedSslError(view, handler, error);

            // this will ignore the Ssl error and will go forward to your site
            handler.proceed();
        }

    }



}




