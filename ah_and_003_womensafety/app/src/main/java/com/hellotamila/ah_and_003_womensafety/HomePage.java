package com.hellotamila.ah_and_003_womensafety;
import android.app.PendingIntent;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.provider.CallLog;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
public class HomePage extends AppCompatActivity {
    int code;
    Intent xIntent;
    String xChatText;
    String id;
    InputStream is = null;
    String result = null;
    String line = null;
    String xMobileNo,xUserName;
    EditText xEdtMobileNo,xEdtUserName;
    String xImei;
    SimpleGetterAndSetter obj;
    String xMapUrl;
    private static final int REQUEST_CODE = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        obj= new SimpleGetterAndSetter();
        try{
            setContentView(R.layout.homepage);
        }
        catch(Exception e)
        {
            String xError=e.toString();
            Toast.makeText(getApplicationContext(),xError,Toast.LENGTH_LONG).show();
        }
       // CollectGpsData();
      // fn_CollectGpsValues();
      //

    }
    public void level1(View v) {
        xIntent = new Intent(getApplicationContext(), Level1.class);
        startActivity(xIntent);
    }
public void level3(View v) {
        xIntent = new Intent(getApplicationContext(), Level2.class);
        startActivity(xIntent);
    }
    public void loadmap(View v) {
        xIntent = new Intent(getApplicationContext(), Webform.class);
        startActivity(xIntent);
    }
    public void situation1(View v) {
        fn_CollectGpsValues2();
        Uri uri = Uri.parse("content://contacts");
        Intent intent = new Intent(Intent.ACTION_PICK, uri);
        intent.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE);
        startActivityForResult(intent, REQUEST_CODE);
    }
    public void situation2(View v) {

        fn_CollectGpsValues2();
        getCallDetails();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent intent) {
        if (requestCode == REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                Uri uri = intent.getData();
                String[] projection = { ContactsContract.CommonDataKinds.Phone.NUMBER, ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME };
                Cursor cursor = getContentResolver().query(uri, projection,
                        null, null, null);
                cursor.moveToFirst();
                int numberColumnIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
                String number = cursor.getString(numberColumnIndex);
                int nameColumnIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);
                String name = cursor.getString(nameColumnIndex);
                Toast.makeText(getApplicationContext(),number,Toast.LENGTH_LONG).show();
                SmsManager sms = SmsManager.getDefault();
                sms.sendTextMessage(number, null, "Your Friend is KIDNAPPED in this  area "+xMapUrl, null, null);
                //Log.d(TAG, "ZZZ number : " + number +" , name : "+name);
            }
        }
    };

    private void getCallDetails() {
        String phNumber="";
        StringBuffer sb = new StringBuffer();
        Cursor managedCursor = managedQuery(CallLog.Calls.CONTENT_URI, null,
                null, null, null);
        int number = managedCursor.getColumnIndex(CallLog.Calls.NUMBER);
        int type = managedCursor.getColumnIndex(CallLog.Calls.TYPE);
        int date = managedCursor.getColumnIndex(CallLog.Calls.DATE);
        int duration = managedCursor.getColumnIndex(CallLog.Calls.DURATION);
        sb.append("Call Log :");
        while (managedCursor.moveToNext()) {
             phNumber = managedCursor.getString(number);
            String callType = managedCursor.getString(type);
            String callDate = managedCursor.getString(date);
            Date callDayTime = new Date(Long.valueOf(callDate));
            String callDuration = managedCursor.getString(duration);
            String dir = null;
            int dircode = Integer.parseInt(callType);
            switch (dircode) {
                case CallLog.Calls.OUTGOING_TYPE:
                    dir = "OUTGOING";
                    break;

                case CallLog.Calls.INCOMING_TYPE:
                    dir = "INCOMING";
                    break;

                case CallLog.Calls.MISSED_TYPE:
                    dir = "MISSED";
                    break;
            }
            sb.append("\nPhone Number:--- " + phNumber + " \nCall Type:--- "
                    + dir + " \nCall Date:--- " + callDayTime
                    + " \nCall duration in sec :--- " + callDuration);
            sb.append("\n----------------------------------");
        }
        //managedCursor.close();
        Toast.makeText(getApplicationContext(),phNumber,Toast.LENGTH_LONG).show();
        SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage(phNumber, null, "Your Friend Ragging by some one  in this  area "+xMapUrl, null, null);
        //textView.setText(sb);
    }
    public void getlocation(View v) {
    fn_CollectGpsValues1();
    }
    public void fn_CollectGpsValues1() {
        GPSTracker gps;
        gps = new GPSTracker(this);
        if (gps.canGetLocation()) {
            double latitude = gps.getLatitude();
            double longitude = gps.getLongitude();
            double dlowlat,dhighlat;

            dlowlat  = Double.parseDouble(obj.a());
            dhighlat = Double.parseDouble(obj.d());

            latitude=roundEven(latitude);
            dlowlat=roundEven(dlowlat);
            dhighlat=roundEven(dhighlat);
            xMapUrl = "http://maps.google.com/?q=" + latitude + ","+ longitude;
            if(latitude==dlowlat)
            {
                SmsManager sms = SmsManager.getDefault();
                sms.sendTextMessage(obj.c(), null, "Your Friend is entered into critical area "+xMapUrl, null, null);
            }
            else if(latitude==dhighlat)
            {
                SmsManager sms = SmsManager.getDefault();
                sms.sendTextMessage(obj.c(), null, "Your Friend is entered into very critical area "+xMapUrl, null, null);
            }
        } else {
            gps.showSettingsAlert();
        }
    }
    public void fn_CollectGpsValues2() {
        GPSTracker gps;
        gps = new GPSTracker(this);
        if (gps.canGetLocation()) {
            double latitude = gps.getLatitude();
            double longitude = gps.getLongitude();
            xMapUrl = "http://maps.google.com/?q=" + latitude + ","+ longitude;
        } else {
            gps.showSettingsAlert();
        }
    }
    public static long roundEven(double d) {
        return Math.round(d / 2) * 2;
    }

}
