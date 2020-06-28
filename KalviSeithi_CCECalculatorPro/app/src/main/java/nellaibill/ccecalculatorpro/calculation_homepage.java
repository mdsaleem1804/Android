package nellaibill.ccecalculatorpro;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

/**
 * Created by user on 12/8/2017.
 */

public class calculation_homepage  extends AppCompatActivity {

    Button button1,button2,button3,button4,
            button5,button6,button7,button8,button9;
    Intent xIntent;
    AdView mAdView,mAdView1;
    AdRequest xBanner,xBanner1;
    InterstitialAd mInterstitialAd;
    TextView  xDeveloperDetails;
    String xDeveloperName,xDeveloperWebsite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calculation_homepage);
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        customBar();
        button1 =(Button) findViewById(R.id.button1);
        button2 =(Button) findViewById(R.id.button2);
        button3 =(Button) findViewById(R.id.button3);
        button4 =(Button) findViewById(R.id.button4);
        button5 =(Button) findViewById(R.id.button5);
        button6 =(Button) findViewById(R.id.button6);
        button7 =(Button) findViewById(R.id.button7);
        button8 =(Button) findViewById(R.id.button8);
        button1.setPaintFlags(button1.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        button2.setPaintFlags(button1.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        button3.setPaintFlags(button1.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        button4.setPaintFlags(button1.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        button5.setPaintFlags(button1.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        button6.setPaintFlags(button1.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        button7.setPaintFlags(button1.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        button8.setPaintFlags(button1.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        mAdView =(AdView) findViewById(R.id.adView);
        mAdView1 =(AdView) findViewById(R.id.adView1);
        xDeveloperDetails = (TextView) findViewById(R.id.txtDeveloperDetails);
        xIntent = new Intent(getApplicationContext(), Webform.class);
        xBanner = new AdRequest.Builder()
                .build();
        mAdView.loadAd(xBanner);

        xBanner1 = new AdRequest.Builder()
                .build();
        mAdView1.loadAd(xBanner1);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xIntent.putExtra("url","http://www.kalviseithi.net/2017/12/cce-general-guidelines.html?m=1");
                startActivity(xIntent);
                LoadInterstialAd();
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                xIntent.putExtra("url","http://www.kalviseithi.net/2017/12/cce-fa-activities.html?m=1");
                startActivity(xIntent);
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                xIntent.putExtra("url","http://www.kalviseithi.net/2017/12/cce-fa-b-activities.html?m=1");
                startActivity(xIntent);
                LoadInterstialAd();
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                xIntent.putExtra("url","http://www.kalviseithi.net/2017/12/cce-sa-question-papers.html?m=1");
                startActivity(xIntent);
            }
        });
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                xIntent.putExtra("url","http://www.kalviseithi.net/2017/12/cce-teachers-manual.html?m=1");
                startActivity(xIntent);
                LoadInterstialAd();
            }
        });
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                xIntent.putExtra("url","http://www.kalviseithi.net/2017/12/cce-syllabus.html?m=1");
                startActivity(xIntent);
            }
        });
        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                xIntent.putExtra("url","http://www.kalviseithi.net/2017/12/cce-records.html?m=1");
                startActivity(xIntent);
                LoadInterstialAd();
            }
        });
        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xIntent.putExtra("url","http://www.kalviseithi.net/2017/12/cce-study-materials.html?m=1");
                startActivity(xIntent);
            }
        });

        xDeveloperDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xIntent.putExtra("url","http://nellaibill.com/web/");
                startActivity(xIntent);
                LoadInterstialAd();
            }
        });
    }


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
    public void LoadInterstialAd()
    {
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
