package com.nellaibill.www.schooleducation_mutualtransfer;

/**
 * Created by user on 11/3/2017.
 */

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.DownloadListener;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class WebViewClientDemoActivity extends Activity {
    /** Called when the activity is first created. */
    WebView mWebView;
    AdView mAdView;
    AdRequest xBanner;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message message) {
            switch (message.what) {
                case 1: {
                    mWebView.goBack();
                }
                break;
            }
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        mAdView = (AdView)findViewById(R.id.adView);
        // Request for Ads
        xBanner = new AdRequest.Builder()

                // Add a test device to show Test Ads
                //.addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                //.addTestDevice("CC5F2C72DF2B356BBF0DA198")
                .build();
        mAdView.loadAd(xBanner);
        try {


        mWebView = (WebView)findViewById(R.id.webview);
        mWebView.setWebViewClient(new WebViewClient());
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.getSettings().setSaveFormData(true);
        mWebView.getSettings().setDomStorageEnabled(true);
        mWebView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);

        Bundle extras = getIntent().getExtras();
        if (extras != null){
            String xUrl = extras.getString("url");

            if (xUrl != null)
                mWebView.loadUrl(xUrl);

        }

        mWebView.setWebViewClient(new WebViewClientDemoActivity.MyWebViewClient());
        mWebView.setOnKeyListener(new View.OnKeyListener() {

            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK
                        && event.getAction() == MotionEvent.ACTION_UP
                        && mWebView.canGoBack()) {
                    handler.sendEmptyMessage(1);
                    return true;
                }

                return false;
            }

        });


        mWebView.setDownloadListener(new DownloadListener() {
            public void onDownloadStart(String url, String userAgent,
                                        String contentDisposition, String mimetype,
                                        long contentLength) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });
        }
        catch(Exception e)
        {
            String xError=e.toString();
            Toast.makeText(getApplicationContext(),xError,Toast.LENGTH_LONG).show();
        }
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

           /* // Check web view back history availability
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