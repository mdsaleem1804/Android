package com.nellaibill.www.tn7thpay;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

/**
 * Created by user on 10/19/2017.
 */

public class seventhpay extends Activity {
    AdView mAdView,mAdView1;
    AdRequest xBanner,xBanner1;
    Intent xIntent;
    private InterstitialAd mInterstitialAd;
    EditText txt1,txt2;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadInterstitial();
        mAdView =(AdView) findViewById(R.id.adView);
        mAdView1 =(AdView) findViewById(R.id.adView1);
        txt1 =(EditText) findViewById(R.id.editText3);
        txt2 =(EditText) findViewById(R.id.editText4);

        mInterstitialAd = newInterstitialAd();
        xIntent = new Intent(getApplicationContext(), LoadSubForm.class);

        Button button9 =(Button) findViewById(R.id.button9);
        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showInterstitial();
                xIntent.putExtra("url","http://www.kalviseithi.net/2017/10/new-hra-slab-in-single-page-tn-7th-pay.html?m=1");
                startActivity(xIntent);
            }
        });

    }

    private InterstitialAd newInterstitialAd() {
        InterstitialAd interstitialAd = new InterstitialAd(this);
        interstitialAd.setAdUnitId(getString(R.string.ad_id_interstitial));
        interstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {

            }

            @Override
            public void onAdFailedToLoad(int errorCode) {

            }

            @Override
            public void onAdClosed() {
                // Proceed to the next level.
            }
        });
        return interstitialAd;
    }

    private void showInterstitial() {
        // Show the ad if it's ready. Otherwise toast and reload the ad.
        if (mInterstitialAd != null && mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        } else {
            Toast.makeText(this, "Ad did not load", Toast.LENGTH_SHORT).show();
        }
    }

    private void loadInterstitial() {
        // Disable the next level button and load the ad.

        AdRequest adRequest = new AdRequest.Builder()
                .setRequestAgent("android_studio:ad_template").build();
        mInterstitialAd.loadAd(adRequest);
    }


}
