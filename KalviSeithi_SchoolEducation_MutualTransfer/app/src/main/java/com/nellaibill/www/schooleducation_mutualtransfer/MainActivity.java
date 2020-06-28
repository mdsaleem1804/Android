package com.nellaibill.www.schooleducation_mutualtransfer;
/*

public class MainActivity extends AppCompatActivity {
    InterstitialAd mInterstitialAd;
    private TextView mLevelTextView;
    Button button1,button2,button3,button4,
            button5,button6,button7;
    Intent xIntent;
    AdView mAdView,mAdView1;
    AdRequest xBanner,xBanner1;
    TextView  xDeveloperDetails;
    String xDeveloperName,xDeveloperWebsite;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
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
        mAdView =(AdView) findViewById(R.id.adView);
        mAdView1 =(AdView) findViewById(R.id.adView1);
        xDeveloperDetails = (TextView) findViewById(R.id.txtDeveloperDetails);

        LoadInterstialAd();
        xIntent = new Intent(this, WebViewClientDemoActivity.class);
        xBanner = new AdRequest.Builder()
                .build();
        mAdView.loadAd(xBanner);

        xBanner1 = new AdRequest.Builder()
                .build();
        mAdView1.loadAd(xBanner1);

            Bundle extras = getIntent().getExtras();
            if (extras != null){
                String xUrl = extras.getString("passmobileno");
                Toast.makeText(getApplicationContext(),xUrl,Toast.LENGTH_LONG).show();
            }
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
        xIntent.putExtra("url","http://nellaibill.com/mutual_transfer/registration.php");
        startActivity(xIntent);
        LoadInterstialAd();
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                xIntent.putExtra("url","http://nellaibill.com/mutual_transfer/user_report.php");
                startActivity(xIntent);
                LoadInterstialAd();
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                xIntent.putExtra("url","http://nellaibill.com/mutual_transfer/user_edit.php");
                startActivity(xIntent);
                LoadInterstialAd();
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                xIntent.putExtra("url","http://nellaibill.com/mutual_transfer/user_delete.php");
                startActivity(xIntent);
                LoadInterstialAd();
            }
        });
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                xIntent.putExtra("url","http://www.kalviseithi.net/2017/05/dee-mutual-transfer-unit-transfer-2017.html?m=1");
                startActivity(xIntent);
                LoadInterstialAd();
            }
        });
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                xIntent.putExtra("url","http://www.kalviseithi.net/2017/11/school-education-mutual-transfer-mobile.html?m=1");
                startActivity(xIntent);
                LoadInterstialAd();
            }
        });
        button7.setOnClickListener(new View.OnClickListener() {
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
catch(Exception e)
        {
            String xError=e.toString();
            Toast.makeText(getApplicationContext(),xError,Toast.LENGTH_LONG).show();
        }
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
*/

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {

    ListView list;
    String[] itemname ={
            "ENTER YOUR DETAILS",
            "SEARCH YOUR PLACE",
            "EDIT YOUR DETAILS",
            "YOUR FAVOURITIES",
            "MUTUAL TRANSFER FORM",
            "MUTUAL TRANSFER NEWS",
            "EXIT"
    };

    Integer[] imgid={
            R.drawable.pic1,
            R.drawable.pic2,
            R.drawable.pic3,
            R.drawable.pic4,
            R.drawable.pic5,
            R.drawable.pic6,
            R.drawable.pic7,
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CustomListAdapter adapter=new CustomListAdapter(this, itemname, imgid);
        list=(ListView)findViewById(R.id.list);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // TODO Auto-generated method stub
                String Slecteditem= itemname[+position];
                Toast.makeText(getApplicationContext(), Slecteditem, Toast.LENGTH_SHORT).show();

            }
        });
    }
}