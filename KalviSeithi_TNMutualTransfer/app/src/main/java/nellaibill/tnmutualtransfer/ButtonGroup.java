package nellaibill.tnmutualtransfer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

import java.util.List;

public class ButtonGroup extends AppCompatActivity {
    Button xSchool, xHigher, xOther;
    Intent xIntent;
    DatabaseHandler db;
    String xPhoneNumber,xMode,xUrl;
    AdView xAdBtnGrpTop,xAdBtnGrpBottom;
    AdRequest xAdReqBtnGrpTop,xAdReqBtnGrpBottom;
    Bundle xBundle;
    InterstitialAd mInterstitialAd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button_group);

        xAdBtnGrpTop =(AdView) findViewById(R.id.adbtngrouptop);
        xAdReqBtnGrpTop = new AdRequest.Builder()
                .build();
        xAdBtnGrpTop.loadAd(xAdReqBtnGrpTop);

        //LoadInterstialAd();

        try {
            xSchool = (Button) findViewById(R.id.btn_school);
            xHigher = (Button) findViewById(R.id.btn_higher);
            xOther = (Button) findViewById(R.id.btn_higher);

            xIntent = new Intent(getApplicationContext(), WebViewActivity.class);
            db = new DatabaseHandler(this);
            xBundle= new Bundle();
            xBundle= getIntent().getExtras();
            xMode = xBundle.getString("mode");


            customBar();

            List<Contact> contacts = db.getAllContacts();
            for (Contact cn : contacts) {
                xPhoneNumber = cn.getPhoneNumber();
            }
        }
        catch (Exception e) {
            Toast toast = Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT);
            toast.show();
        }

    }
    public void fn_School(View v) {
        finish();
        if(xMode.equalsIgnoreCase("register"))
        {
            xUrl="http://nellaibill.com/mutual_transfer/school_education_registration.php?mobileno="+xPhoneNumber;
        }
        else if(xMode.equalsIgnoreCase("search"))
        {
            xUrl="http://nellaibill.com/mutual_transfer/school_education_user_report.php?mobileno="+xPhoneNumber;
        }
        else if(xMode.equalsIgnoreCase("edit"))
        {
            xUrl="http://nellaibill.com/mutual_transfer/school_education_edit.php?mobileno="+xPhoneNumber;
        }
        else if(xMode.equalsIgnoreCase("favorites"))
        {
            xUrl="http://nellaibill.com/mutual_transfer/school_education_user_favorites.php?mobileno="+xPhoneNumber;
        }
        xIntent.putExtra("url",xUrl);

        startActivity(xIntent);

    }
    public void fn_Higher(View v) {
        finish();
        if(xMode.equalsIgnoreCase("register"))
        {
            xUrl="http://nellaibill.com/mutual_transfer/higher_education_registration.php?mobileno="+xPhoneNumber;
        }
        else if(xMode.equalsIgnoreCase("search"))
        {
            xUrl="http://nellaibill.com/mutual_transfer/higher_education_user_report.php?mobileno="+xPhoneNumber;
        }
        else if(xMode.equalsIgnoreCase("edit"))
        {
            xUrl="http://nellaibill.com/mutual_transfer/higher_education_edit.php?mobileno="+xPhoneNumber;
        }
        else if(xMode.equalsIgnoreCase("favorites"))
        {
            xUrl="http://nellaibill.com/mutual_transfer/higher_education_user_favorites.php?mobileno="+xPhoneNumber;
        }
        xIntent.putExtra("url",xUrl);
        startActivity(xIntent);

    }
    public void fn_Other(View v) {
        finish();
        if(xMode.equalsIgnoreCase("register"))
        {
            xUrl="http://nellaibill.com/mutual_transfer/other_department_registration.php?mobileno="+xPhoneNumber;
        }
        else if(xMode.equalsIgnoreCase("search"))
        {
            xUrl="http://nellaibill.com/mutual_transfer/other_department_user_report.php?mobileno="+xPhoneNumber;
        }
        else if(xMode.equalsIgnoreCase("edit"))
        {
            xUrl="http://nellaibill.com/mutual_transfer/other_department_edit.php?mobileno="+xPhoneNumber;
        }
        else if(xMode.equalsIgnoreCase("favorites"))
        {
            xUrl="http://nellaibill.com/mutual_transfer/other_department_user_favorites.php?mobileno="+xPhoneNumber;
        }
        xIntent.putExtra("url",xUrl);
        startActivity(xIntent);

    }
    public void customBar() {
        try {
            ActionBar actionBar = getSupportActionBar();
            actionBar.setDisplayShowHomeEnabled(false);
            actionBar.setDisplayShowTitleEnabled(false);

            LayoutInflater inflater = LayoutInflater.from(this);
            View customView = inflater.inflate(R.layout.custom_actionbar, null);

            ImageView menus = (ImageView) customView.findViewById(R.id.slidingmenu);

            actionBar.setCustomView(customView);
            actionBar.setDisplayShowCustomEnabled(true);
            actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);

            Toolbar toolbar = (Toolbar) actionBar.getCustomView().getParent();
            toolbar.setContentInsetsAbsolute(0, 0);
            toolbar.getContentInsetEnd();
            toolbar.setPadding(0, 0, 0, 0);
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(),e.toString(),Toast.LENGTH_LONG).show();
        }
    }
    @Override
    protected void onPause() {
        xAdBtnGrpTop.pause();
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        xAdBtnGrpTop.resume();

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


