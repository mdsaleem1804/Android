package com.kalviseithi.official;


import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.ContextMenu;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.DownloadListener;
import android.webkit.SslErrorHandler;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

/**
 * Created by Belal on 18/09/16.
 */


public class Webform extends Fragment {
    public WebView mWebView;
    private ProgressBar xProgress;
    String xUrlvalue;
    private Button mButtonBack;
    private Button mButtonForward;
    private Button mButtonHome;
    AdView mAdView;
    AdRequest xBanner;
    public static Fragment newInstance(Context context) {
        Webform f = new Webform();
       return f;
    }
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message message) {
            switch (message.what) {
                case 1: {
                    webViewGoBack();
                }
                break;
            }
        }
    };
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.webform, container, false);
        Bundle mBundle = new Bundle();
        mBundle = getArguments();
         xUrlvalue = mBundle.getString("urlkey");
        try {

            mWebView = v.findViewById(R.id.webview);
            mWebView.clearCache(true);
            this.registerForContextMenu(mWebView);
            xProgress = v.findViewById(R.id.progressBar1);
            mButtonBack = v.findViewById(R.id.btn_back);
            mButtonForward = v.findViewById(R.id.btn_forward);
            mButtonHome = v.findViewById(R.id.btn_home);
            mAdView = v.findViewById(R.id.adView);
            // Request for Ads
            xBanner = new AdRequest.Builder()

                    // Add a test device to show Test Ads
                    //.addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                    //.addTestDevice("CC5F2C72DF2B356BBF0DA198")
                    .build();
            mAdView.loadAd(xBanner);

            WebSettings settings = mWebView.getSettings();

            settings.setJavaScriptEnabled(true);
            // By using this method together with the overridden method onReceivedSslError()
            // you will avoid the "WebView Blank Page" problem to appear. This might happen if you
            // use a "https" url!

            settings.setLoadWithOverviewMode(true);
            settings.setUseWideViewPort(true);
            settings.setAppCacheEnabled(true);
            settings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
            settings.setBuiltInZoomControls(true);
            settings.setDisplayZoomControls(true);
            settings.setDomStorageEnabled(true);

            mWebView.loadUrl(xUrlvalue);
            // Set a click listener for back button
            mButtonBack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                /*
                    public boolean canGoBack ()
                        Gets whether this WebView has a back history item.

                    Returns
                        true : iff this WebView has a back history item
                */
                    if (mWebView.canGoBack()) {
                    /*
                        public void goBack ()
                            Goes back in the history of this WebView.
                    */
                        mWebView.goBack();

                        // Display the notification
                        //Toast.makeText(mContext,"Go To Back",Toast.LENGTH_SHORT).show();
                    }
                }
            });

            // Set a click listener for forward button
            mButtonForward.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                /*
                    public boolean canGoForward ()
                        Gets whether this WebView has a forward history item.

                    Returns
                        true : iff this WebView has a forward history item
                */
                    if (mWebView.canGoForward()) {
                    /*
                        public void goForward ()
                            Goes forward in the history of this WebView.
                    */
                        mWebView.goForward();

                        // Display the notification
                        //Toast.makeText(mContext,"Go To Forward",Toast.LENGTH_SHORT).show();
                    }
                }
            });

            // Set a click listener for forward button
            mButtonHome.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getContext(), MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }
            });

            mWebView.setWebViewClient(new MyWebViewClient());
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
    } catch (Exception e) {
        Toast.makeText(getActivity(), e.toString(), Toast.LENGTH_SHORT).show();
    }

        return v;


}



    private void webViewGoBack(){
        mWebView.goBack();
    }
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        // Confirm the view is a webview
        if (v instanceof WebView) {
            WebView.HitTestResult result = ((WebView) v).getHitTestResult();

            if (result != null) {
                int type = result.getType();

                // Confirm type is an image
                if (type == WebView.HitTestResult.IMAGE_TYPE || type == WebView.HitTestResult.SRC_IMAGE_ANCHOR_TYPE) {
                    String imageUrl = result.getExtra();
                    Toast.makeText(getContext(), "Click and Save the Image", Toast.LENGTH_LONG).show();
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(imageUrl));
                    startActivity(i);

                }
            }
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
            if (xProgress.isShown()) {
                xProgress.setVisibility(View.GONE);
            }
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



