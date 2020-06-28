    package com.hellotamila.udisecode;

import android.content.Intent;
import android.os.Build;
import android.os.StrictMode;
import android.support.annotation.RequiresApi;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.Toolbar;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

    public class MainActivity extends AppCompatActivity {
        WebView xWebView;
        WebSettings xWebSettings;
        String xUrl;
        AdView mAdView;
        AdRequest xBanner;
        InterstitialAd mInterstitialAd;


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            if (android.os.Build.VERSION.SDK_INT > 9) {
                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                        .permitAll().build();
                StrictMode.setThreadPolicy(policy);
            }
            setContentView(R.layout.activity_main);
            xWebView = (WebView) findViewById(R.id.webView1);
            mAdView = (AdView) findViewById(R.id.adView);
            xBanner = new AdRequest.Builder()
                    .build();
            mAdView.loadAd(xBanner);
            LoadInterstialAd();
            xWebSettings = xWebView.getSettings();
           // customBar();
            xWebSettings.setJavaScriptEnabled(true);
            xUrl = "http://hellotamila.com/hellotamila_apps/udise/";
            xWebView.loadUrl(xUrl);

        }
        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        public void customBar(){
            ActionBar actionBar = getSupportActionBar();
            actionBar.setDisplayShowHomeEnabled(false);
            actionBar.setDisplayShowTitleEnabled(false);

            LayoutInflater inflater = LayoutInflater.from(this);
            View customView = inflater.inflate(R.layout.custom_actionbar, null);

            ImageView menus =(ImageView) customView.findViewById(R.id.slidingmenu);

            actionBar.setCustomView(customView);
            actionBar.setDisplayShowCustomEnabled(true);
            actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);

            Toolbar toolbar=(Toolbar)actionBar.getCustomView().getParent();
            toolbar.setContentInsetsAbsolute(0, 0);
            toolbar.getContentInsetEnd();
            toolbar.setPadding(0, 0, 0, 0);
        }
        public void LoadInterstialAd() {
            mInterstitialAd = new InterstitialAd(this);

            // set the ad unit ID
            mInterstitialAd.setAdUnitId(getString(R.string.ad_id_interstitial));

            AdRequest adRequest1 = new AdRequest.Builder()
                    //.addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                    //.addTestDevice("CC5F2C72DF2B356BBF0DA198")
                    .build();
            // Load ads into Interstitial Ads
            mInterstitialAd.loadAd(adRequest1);

            mInterstitialAd.setAdListener(new AdListener() {
                public void onAdLoaded() {
                    showInterstitial();
                }
            });
        }

        private void showInterstitial() {
            if (mInterstitialAd.isLoaded()) {
                mInterstitialAd.show();
            }
        }
    }




