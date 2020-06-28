package com.hellotamila.middaymealtamilnadu;

import android.Manifest;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.telephony.SmsManager;
import android.telephony.SubscriptionInfo;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

import java.util.ArrayList;
import java.util.List;

public class HomePage extends AppCompatActivity {
    private static final String TODO ="" ;
    EditText xEdtA, xEdtB, xEdtC;
    AdView xAdWebTop;
    AdRequest xAdReqWebTop;
    InterstitialAd mInterstitialAd;
    Button xSend;
    String xMsg;
    public static boolean isMultiSimEnabled = false;
    public static List<SubscriptionInfo> subInfoList;
    public static ArrayList<String> numbers;
    private SubscriptionManager subscriptionManager;
    static final Integer PHONESTATS = 0x1;
    AlertDialog.Builder builder;
    AlertDialog adb;
    SimpleGetterAndSetter obj;
    private static final int MY_PERMISSIONS_REQUEST_SEND_SMS =0 ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        xAdWebTop = (AdView) findViewById(R.id.adView);
        xAdReqWebTop = new AdRequest.Builder()
                .build();
        xAdWebTop.loadAd(xAdReqWebTop);
        obj= new SimpleGetterAndSetter();
        //MobileAds.initialize(this, getString(R.string.admob_app_id));

        TextView xTxtSchoolName = (TextView) findViewById(R.id.txt_SchoolName);
        xTxtSchoolName.setText("Welcome " +obj.getOtp());
        //customBar();
        xEdtA = (EditText) findViewById(R.id.a);
        xEdtB = (EditText) findViewById(R.id.b);
        xEdtC = (EditText) findViewById(R.id.c);
        xSend = (Button) findViewById(R.id.button);
        numbers = new ArrayList<String>();
        subscriptionManager = SubscriptionManager.from(HomePage.this);
        builder = new AlertDialog.Builder(this);
         adb= builder.create();
          if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.SEND_SMS)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.SEND_SMS)) {
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.SEND_SMS},
                        MY_PERMISSIONS_REQUEST_SEND_SMS);
            }
        }

    }


   public void send_sms(View v)
    {
try {

    askForPermission(Manifest.permission.READ_PHONE_STATE, PHONESTATS);
    xMsg = "MDM A" + xEdtA.getText().toString() + "B" + xEdtB.getText().toString() + "C" + xEdtC.getText().toString();

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1) {
        subInfoList = subscriptionManager.getActiveSubscriptionInfoList();
    }
    //check whether the phone is of Multi sim or Not
    if (subInfoList.size() > 1) {
        isMultiSimEnabled = true;
    }
    for (SubscriptionInfo subscriptionInfo : subInfoList)
    //add all sim number into arraylist
    {
        //numbers.add(subscriptionInfo.getNumber()+ " " +subscriptionInfo.getCarrierName());
        numbers.add(subscriptionInfo.getCarrierName().toString());
    }
    if (isMultiSimEnabled == true) {
        try {
            LayoutInflater inflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
            View layout = inflater.inflate(R.layout.alert, null);

            final LinearLayout linearLayout = (LinearLayout) layout.findViewById(R.id.sim1);
            final LinearLayout linearLayout2 = (LinearLayout) layout.findViewById(R.id.sim2);
            final TextView txtSim1 = (TextView) layout.findViewById(R.id.textView);
            final TextView txtSim2 = (TextView) layout.findViewById(R.id.textView2);

            adb.setIcon(R.mipmap.message);
            adb.setTitle("பதிவு செய்த மொபைல் எண்ணை தேர்வு செய்யவும்.");
            adb.setMessage("SELECT SIM");
            adb.setView(layout);
            adb.show();
            txtSim1.setText(numbers.get(0));
            txtSim2.setText(numbers.get(1));

        } catch (Exception e) {
            //  Log.d(TAG,e.toString());
        }
    } else {

        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage("155250", null, xMsg, null, null);
            Toast.makeText(getApplicationContext(), "Message Sent Successfully",
                    Toast.LENGTH_LONG).show();
        } catch (Exception ex) {
            Toast.makeText(getApplicationContext(), ex.getMessage().toString(),
                    Toast.LENGTH_LONG).show();
            ex.printStackTrace();
        }


    }
}catch(Exception e)
{
    Toast.makeText(getApplicationContext(),e.toString(),Toast.LENGTH_LONG).show();
}

}
    @Override
    public void onBackPressed() {
        IsFinish("Want to close app?");
    }
    public void IsFinish(String alertmessage) {
        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case DialogInterface.BUTTON_POSITIVE:
                       /* android.os.Process.killProcess(android.os.Process.myPid());
                        // This above line close correctly
                        finish();*/
                        moveTaskToBack(true);
                        finish();
                        break;

                    case DialogInterface.BUTTON_NEGATIVE:

                        break;
                }
            }
        };

        android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(this);
        builder.setMessage(alertmessage)
                .setPositiveButton("Yes", dialogClickListener)
                .setNegativeButton("No", dialogClickListener).show();
    }

    public void sim1(View v) {
        if (Build.VERSION.SDK_INT >= 22) {
            SmsManager.getSmsManagerForSubscriptionId(((SubscriptionInfo) subInfoList.get(0)).getSubscriptionId()).sendTextMessage("155250", null,xMsg, null, null);
            adb.dismiss();
            Toast.makeText(getApplicationContext(), "Message Sent Successfully",
                    Toast.LENGTH_LONG).show();
        }
        else
        {
            SendGeneralSms();

        }
    }
    public void sim2(View v) {
        if (Build.VERSION.SDK_INT >= 22) {
            SmsManager.getSmsManagerForSubscriptionId(((SubscriptionInfo) subInfoList.get(1)).getSubscriptionId()).sendTextMessage("155250", null,xMsg, null, null);
            adb.dismiss();
            Toast.makeText(getApplicationContext(), "Message Sent Successfully",
                    Toast.LENGTH_LONG).show();
        }
        else
        {
            SendGeneralSms();
        }
    }
    private void askForPermission(String permission, Integer requestCode) {
        if (ContextCompat.checkSelfPermission(HomePage.this, permission) != PackageManager.PERMISSION_GRANTED) {

            // Should show an explanation
            if (ActivityCompat.shouldShowRequestPermissionRationale(HomePage.this, permission)) {

                ActivityCompat.requestPermissions(HomePage.this, new String[]{permission}, requestCode);

            } else {

                ActivityCompat.requestPermissions(HomePage.this, new String[]{permission}, requestCode);
            }
        } else {
            Toast.makeText(this,permission + " is already granted.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
    {
        switch (requestCode) {
            case 1: {

                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                } else {
                    Toast.makeText(HomePage.this, "You have Denied the Permission", Toast.LENGTH_SHORT).show();
                }
                return;
            }
        }
    }

public void SendGeneralSms()
{
    try {
        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage("155250", null, xMsg, null, null);
        Toast.makeText(getApplicationContext(), "Message Sent Successfully",
                Toast.LENGTH_LONG).show();
    } catch (Exception ex) {
        Toast.makeText(getApplicationContext(),ex.getMessage().toString(),
                Toast.LENGTH_LONG).show();
        ex.printStackTrace();
    }
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
