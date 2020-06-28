package com.nellaibill.www.tn7thpay;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

public class MainActivity extends AppCompatActivity {
    InterstitialAd mInterstitialAd;
    private TextView mLevelTextView;
    Button button1,button2,button3,button4,
            button5,button6,button7,button8,button9;
    Intent xIntent;
        AdView mAdView,mAdView1;
    AdRequest xBanner,xBanner1;
    TextView  xDeveloperDetails;
    String xDeveloperName,xDeveloperWebsite;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        button1 =(Button) findViewById(R.id.button1);
        button2 =(Button) findViewById(R.id.button2);
        button3 =(Button) findViewById(R.id.button3);
        button4 =(Button) findViewById(R.id.button4);
        button5 =(Button) findViewById(R.id.button5);
        button6 =(Button) findViewById(R.id.button6);
        button7 =(Button) findViewById(R.id.button7);
        button8 =(Button) findViewById(R.id.button8);
        button9 =(Button) findViewById(R.id.button9);
        mAdView =(AdView) findViewById(R.id.adView);
        mAdView1 =(AdView) findViewById(R.id.adView1);
        xDeveloperDetails = (TextView) findViewById(R.id.txtDeveloperDetails);


        LoadInterstialAd();
        xIntent = new Intent(getApplicationContext(), LoadSubForm.class);
        xBanner = new AdRequest.Builder()
                .build();
        mAdView.loadAd(xBanner);

        xBanner1 = new AdRequest.Builder()
                .build();
        mAdView1.loadAd(xBanner1);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                xIntent.putExtra("url","http://nellaibill.com/7thpay/");
                startActivity(xIntent);
                LoadInterstialAd();

            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                xIntent.putExtra("url","http://www.kalviseithi.net/2017/10/flash-news-tn-7th-pay-commission-go.html?m=1");
                startActivity(xIntent);
                LoadInterstialAd();
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                xIntent.putExtra("url","http://www.kalviseithi.net/2017/10/flash-news-tn-7th-pay-hra-go-released.html?m=1");
                startActivity(xIntent);
                LoadInterstialAd();
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                xIntent.putExtra("url","http://www.kalviseithi.net/2017/10/tn-7th-pay-commission-all-pay-matrix.html?m=1");
                startActivity(xIntent);
                LoadInterstialAd();
            }
        });
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                xIntent.putExtra("url","http://www.kalviseithi.net/2017/10/new-hra-slab-in-single-page-tn-7th-pay.html?m=1");
                startActivity(xIntent);
                LoadInterstialAd();
            }
        });
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                xIntent.putExtra("url","http://www.kalviseithi.net/2017/10/7th-pay-fixation-tamil-form.html?m=1");
                startActivity(xIntent);
                LoadInterstialAd();
            }
        });
        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                xIntent.putExtra("url","http://www.kalviseithi.net/2017/10/flash-news-tn-7th-pay-commission-report.html?m=1");
                startActivity(xIntent);
                LoadInterstialAd();
            }
        });
        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                xIntent.putExtra("url","http://www.kalviseithi.net/search/label/TN%207th%20PAY?m=1");
                startActivity(xIntent);
                LoadInterstialAd();
            }
        });
        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
                System.exit(0);
            }
        });
        xDeveloperDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xIntent.putExtra("url","http://nellaibill.com/web/");
                startActivity(xIntent);
            }
        });
    }

    public void LoadInterstialAd()
    {
        mInterstitialAd = new InterstitialAd(this);

        // set the ad unit ID
        mInterstitialAd.setAdUnitId(getString(R.string.ad_id_interstitial));

        AdRequest adRequest1 = new AdRequest.Builder()
                //.addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
    }
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
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
