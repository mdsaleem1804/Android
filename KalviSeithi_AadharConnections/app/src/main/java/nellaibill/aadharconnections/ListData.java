package nellaibill.aadharconnections;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

public class ListData extends AppCompatActivity  {
    InterstitialAd mInterstitialAd;
    AdView mAdView;
    AdRequest xBanner,xBanner1;
    ListView list;
    Intent xIntent;
    String[] web = {
            "Aadhar + Mobile Number",
            "Aadhar + Bank Account",
            "Aadhar + Pan Card",
            "Aadhar + Gas",
            "Aadhar + Lic Policy",
            "Aadhar + India Post"
    };
    Integer[] imageId = {
            R.drawable.mobile,
            R.drawable.bank,
            R.drawable.pancard,
            R.drawable.gas,
            R.drawable.lic,
            R.drawable.post,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listdata);
        mAdView = (AdView) findViewById(R.id.adView);

        xBanner = new AdRequest.Builder()
                .build();
        mAdView.loadAd(xBanner);

        customBar();
       // LoadInterstialAd();
        try {
            CustomListAdapter adapter = new CustomListAdapter(this, web, imageId);
            list = (ListView) findViewById(R.id.list);
            list.setAdapter(adapter);
            list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> parent, View view,
                                        int position, long id) {
                    // TODO Auto-generated method stub
                    if (position == 0) {
                        xIntent = new Intent(ListData.this, AadharMobile.class);
                    }
                    if (position == 1) {
                        xIntent = new Intent(getApplicationContext(), WebViewActivity.class);
                        xIntent.putExtra("url", "http://nellaibill.com/aadhar/aadhar_bank.php");
                    }
                    if (position == 2) {
                        xIntent = new Intent(getApplicationContext(), WebViewActivity.class);
                        xIntent.putExtra("url", "https://incometaxindiaefiling.gov.in/e-Filing/Services/LinkAadhaarPrelogin.html");
                    }
                    if (position == 3) {
                        xIntent = new Intent(getApplicationContext(), WebViewActivity.class);
                        xIntent.putExtra("url", "http://nellaibill.com/aadhar/aadhar_gas.php");
                    }
                    if (position == 4) {
                        xIntent = new Intent(getApplicationContext(), WebViewActivity.class);
                        xIntent.putExtra("url", "https://www.licindia.in/Home/Link_Aadhaar_and_PAN_to_Policy");

                    }
                    if (position == 5) {
                        xIntent = new Intent(getApplicationContext(), WebViewActivity.class);
                        xIntent.putExtra("url", "https://www.indiapost.gov.in/Financial/DOP_PDFFiles/AadhaarLinkageDelink.pdf");
                                    }
                    startActivity(xIntent);
                    LoadInterstialAd();

                }
            });
        }
        catch (Exception e) {
            Toast toast = Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT);
            toast.show();
        }
    }
    public void customBar()
    {
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
    public void aadhar_update(View v)
    {
        xIntent = new Intent(getApplicationContext(), WebViewActivity.class);
        xIntent.putExtra("url", "https://uidai.gov.in/enrolment-update/aadhaar-enrolment/aadhaar-data-update.html");
        startActivity(xIntent);
        LoadInterstialAd();
    }
    public void aadhar_download(View v)
    {
        xIntent = new Intent(getApplicationContext(), WebViewActivity.class);
        xIntent.putExtra("url", "https://eaadhaar.uidai.gov.in/#/popup");
        startActivity(xIntent);
        LoadInterstialAd();
    }

    public void aadhar_status(View v)
    {
        xIntent = new Intent(getApplicationContext(), WebViewActivity.class);
        xIntent.putExtra("url", "https://resident.uidai.gov.in/check-aadhaar-status");
        startActivity(xIntent);
        LoadInterstialAd();
    }
    @Override
    public void onBackPressed() {
        //super.onBackPresssed();
        IsFinish("Want to close app?");
    }

    public void IsFinish(String alertmessage) {
        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case DialogInterface.BUTTON_POSITIVE:
                        android.os.Process.killProcess(android.os.Process.myPid());
                        // This above line close correctly
                        //finish();
                        break;

                    case DialogInterface.BUTTON_NEGATIVE:

                        break;
                }
            }
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(alertmessage)
                .setPositiveButton("Yes", dialogClickListener)
                .setNegativeButton("No", dialogClickListener).show();
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
    protected void onPause() {
        mAdView.pause();
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mAdView.resume();
    }


}

