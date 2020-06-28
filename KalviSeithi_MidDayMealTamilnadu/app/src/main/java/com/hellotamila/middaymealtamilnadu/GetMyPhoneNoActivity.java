package com.hellotamila.middaymealtamilnadu;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

public class GetMyPhoneNoActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        String number =getMyPhoneNO();
        Toast.makeText(getApplicationContext(), "Send SMS (DEFAULT-SIM): "
                +number, Toast.LENGTH_SHORT).show();
        Log.v("Debug", number);
    }

    private String getMyPhoneNO(){
        TelephonyManager mTelephonyMgr;
        mTelephonyMgr = (TelephonyManager) getSystemService
                (Context.TELEPHONY_SERVICE);
        String yourNumber = mTelephonyMgr.getLine1Number();
        return yourNumber;
    }
}