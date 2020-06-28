package nellaibill.ccecalculatorpro;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

/**
 * Created by user on 12/13/2017.
 */

public class HomePage  extends AppCompatActivity {
    AdView xAdView1;
    AdRequest xBanner;
    InterstitialAd mInterstitialAd;
    Intent xIntent;
    String xDeveloperName,xDeveloperWebsite;
    TextView  xDeveloperDetails;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepage);
        customBar();
        xAdView1 =(AdView) findViewById(R.id.adView1);
        xDeveloperDetails = (TextView) findViewById(R.id.txtDeveloperDetails);

        xIntent = new Intent(getApplicationContext(), Webform.class);
        xBanner = new AdRequest.Builder()
                .build();
        xAdView1.loadAd(xBanner);
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
    public void fn_cce_grade(View v)
    {
        xIntent = new Intent(getApplicationContext(), Webform.class);
        xIntent.putExtra("url", "file:///android_asset/cce_grade.html");
        startActivity(xIntent);
        LoadInterstialAd();
    }
    public void fn_cce_individual(View v)
    {
        isInternetOn();
        xIntent = new Intent(getApplicationContext(), Webform.class);
        xIntent.putExtra("url", "http://nellaibill.com/ccecalculatorpro/cce_individual.php");
        startActivity(xIntent);
        LoadInterstialAd();
    }
    public void fn_cce_annual(View v)
    {
        isInternetOn();
        xIntent = new Intent(getApplicationContext(), Webform.class);
        xIntent.putExtra("url", "http://nellaibill.com/ccecalculatorpro/cce_annual.php");
        startActivity(xIntent);
        LoadInterstialAd();
    }
    public void fn_class_wise(View v)
    {
        isInternetOn();
        xIntent = new Intent(getApplicationContext(), Webform.class);
        xIntent.putExtra("url", "http://nellaibill.com/ccecalculatorpro/cce_classwise.php");
        startActivity(xIntent);
        LoadInterstialAd();
    }
    public void fn_cce_useful(View v)
    {
        xIntent = new Intent(getApplicationContext(), calculation_homepage.class);
        startActivity(xIntent);
        LoadInterstialAd();
    }
    public void fn_exit(View v)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure you want to Close this App?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        finish();
                        System.exit(0);
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();

    }
    @Override
    public void onBackPressed()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure you want to Close this App?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        finish();
                        System.exit(0);
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }
    public final boolean isInternetOn() {

        // get Connectivity Manager object to check connection
        ConnectivityManager connec =
                (ConnectivityManager)getSystemService(getBaseContext().CONNECTIVITY_SERVICE);

        // Check for network connections
        if ( connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.CONNECTED ||
                connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.CONNECTING ||
                connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.CONNECTING ||
                connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.CONNECTED ) {

            // if connected with internet

           // Toast.makeText(this, " Connected ", Toast.LENGTH_LONG).show();
            return true;

        } else if (
                connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.DISCONNECTED ||
                        connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.DISCONNECTED  ) {

            Toast.makeText(this, "?lease Check with your Internet Connection.", Toast.LENGTH_LONG).show();
            return false;
        }
        return false;
    }
}
