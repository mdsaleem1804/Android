package nellaibill.tnincometaxcalculator;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

public class Webform extends AppCompatActivity {
    WebView xWebView;
    WebSettings xWebSettings;
    Bundle xBundle;
    Intent xIntent;
    String xUrlvalue;
    AdRequest xBanner;
    AdView mAdView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webform);
        xWebView = (WebView) findViewById(R.id.webView1);
        xIntent = getIntent();
        xBundle= xIntent.getExtras();
        xUrlvalue = xBundle.getString("url");
        MobileAds.initialize(this, getString(R.string.admob_app_id));
        mAdView =(AdView) findViewById(R.id.adView);
        xBanner = new AdRequest.Builder()

                // Add a test device to show Test Ads
                //.addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                //.addTestDevice("CC5F2C72DF2B356BBF0DA198")
                .build();
        mAdView.loadAd(xBanner);

        xWebSettings = xWebView.getSettings();
        xWebSettings.setJavaScriptEnabled(true);
        xWebView.loadUrl(xUrlvalue);
    }



}




