package com.hellotamila.middaymealtamilnadu;
import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SubscriptionInfo;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
public class Test extends AppCompatActivity {


    TextView tv_imei,tv_aid;
    Button bt_getall;
    String imei,androidid;
    public static boolean isMultiSimEnabled = false;
    public static List<SubscriptionInfo> subInfoList;
    public static ArrayList<String> numbers;
    private SubscriptionManager subscriptionManager;
    static final Integer PHONESTATS = 0x1;
    private final String TAG=MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);

        tv_imei=(TextView)findViewById(R.id.tv_imei);
        tv_aid=(TextView)findViewById(R.id.tv_aid);
        bt_getall=(Button)findViewById(R.id.bt_getall);


        numbers = new ArrayList<String>();
        subscriptionManager = SubscriptionManager.from(Test.this);

        bt_getall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                askForPermission(Manifest.permission.READ_PHONE_STATE, PHONESTATS);

                tv_imei.setText(imei);
                tv_aid.setText(androidid);
            }
        });


    }


    private void askForPermission(String permission, Integer requestCode) {
        if (ContextCompat.checkSelfPermission(Test.this, permission) != PackageManager.PERMISSION_GRANTED) {

            // Should show an explanation
            if (ActivityCompat.shouldShowRequestPermissionRationale(Test.this, permission)) {

                ActivityCompat.requestPermissions(Test.this, new String[]{permission}, requestCode);

            } else {

                ActivityCompat.requestPermissions(Test.this, new String[]{permission}, requestCode);
            }
        } else {
            imei = getImeiNumber();
            getClientPhoneNumber();
            androidid=getAndroidId();
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

                    imei = getImeiNumber();
                    getClientPhoneNumber();
                    androidid=getAndroidId();

                } else {

                    Toast.makeText(Test.this, "You have Denied the Permission", Toast.LENGTH_SHORT).show();
                }
                return;
            }
        }
    }


    private String getImeiNumber() {
        final TelephonyManager telephonyManager= (TelephonyManager) this.getSystemService(Context.TELEPHONY_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            //getDeviceId() is Deprecated so for android O we can use getImei() method
            return telephonyManager.getImei();
        }
        else {
            return telephonyManager.getDeviceId();
        }

    }

    private void getClientPhoneNumber() {
        try{

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1) {
                subInfoList = subscriptionManager.getActiveSubscriptionInfoList();
            }
            //check whether the phone is of Multi sim or Not
            if (subInfoList.size() > 1)
            {
                isMultiSimEnabled = true;
            }
            for (SubscriptionInfo subscriptionInfo : subInfoList)
            //add all sim number into arraylist
            {
               //numbers.add(subscriptionInfo.getNumber()+ " " +subscriptionInfo.getCarrierName());
                numbers.add(subscriptionInfo.getCarrierName().toString());
            }

            LayoutInflater inflater = (LayoutInflater)this.getSystemService(LAYOUT_INFLATER_SERVICE);
            View layout = inflater.inflate(R.layout.alert, null);
            final AlertDialog.Builder adb = new AlertDialog.Builder(this);
            final LinearLayout linearLayout = (LinearLayout) layout.findViewById(R.id.sim1);
            final LinearLayout linearLayout2 = (LinearLayout) layout.findViewById(R.id.sim2);
            final TextView txtSim1 = (TextView) layout.findViewById(R.id.textView);
            final TextView txtSim2 = (TextView) layout.findViewById(R.id.textView2);

            adb.setIcon(R.mipmap.message);
            adb.setTitle("SMS VIA");
            adb.setMessage("SELECT SIM");
            adb.setView(layout);
            adb.show();
            txtSim1.setText(numbers.get(0));
            txtSim2.setText(numbers.get(1));

        }catch (Exception e)
        {
            Log.d(TAG,e.toString());
        }


    }
    public void sim1(View v) {
        if (Build.VERSION.SDK_INT >= 22) {
            SmsManager.getSmsManagerForSubscriptionId(((SubscriptionInfo) subInfoList.get(0)).getSubscriptionId()).sendTextMessage("9578795653", null,"Hello", null, null);
        }
    }
    private String getAndroidId() {

        androidid = Settings.Secure.getString(getApplicationContext().getContentResolver(), Settings.Secure.ANDROID_ID);

        Log.e("TAG",Settings.Secure.getString(getApplicationContext().getContentResolver(), Settings.Secure.ALLOWED_GEOLOCATION_ORIGINS));
        Log.e("TAG",Settings.Secure.getString(getApplicationContext().getContentResolver(), Settings.Secure.DEFAULT_INPUT_METHOD));

        return androidid;
    }


}