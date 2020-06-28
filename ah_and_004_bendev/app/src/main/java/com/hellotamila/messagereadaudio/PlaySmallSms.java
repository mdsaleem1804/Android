package com.hellotamila.messagereadaudio;

import android.app.Activity;
import android.database.Cursor;
import android.net.Uri;
import android.speech.tts.TextToSpeech;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;


public class PlaySmallSms extends Activity implements
        TextToSpeech.OnInitListener {
    /** Called when the activity is first created. */

    private TextToSpeech tts;
    String xMessageText;
    ListView lViewSMS;
    LinearLayout xLinearLayout;
    String   xFlag;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        try{
            super.onCreate(savedInstanceState);
            setContentView(R.layout.play_lage_sms);
            Bundle b = new Bundle();
            b = getIntent().getExtras();
            xFlag = b.getString("flag");
            xLinearLayout = new LinearLayout(this);

            tts = new TextToSpeech(this, this);
            lViewSMS = (ListView) findViewById(R.id.listViewSMS);
            if(fetchInbox()!=null)
            {
                ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, fetchInbox());
                lViewSMS.setAdapter(adapter);
            }

        }
        catch(Exception e)
        {
            String xError=e.toString();
            Toast.makeText(getApplicationContext(),xError,Toast.LENGTH_LONG).show();
        }

        SmsReceiver.bindListener(new SmsListener() {
            @Override
            public void messageReceived(String messageText) {
                Log.e("Message",messageText);
                xMessageText=messageText;
                Toast.makeText(PlaySmallSms.this,"Message From App: "+messageText,Toast.LENGTH_LONG).show();
                speakOut();
            }
        });


        // ListView Item Click Listener
        lViewSMS.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                // ListView Clicked item index
                int itemPosition     = position;

                // ListView Clicked item value
                String  itemValue    = (String) lViewSMS.getItemAtPosition(position);
                xMessageText=itemValue;
                speakOut();
                // Show Alert
                Toast.makeText(getApplicationContext(),
                        "Position :"+itemPosition+"  ListItem : " +itemValue , Toast.LENGTH_LONG)
                        .show();

            }

        });

    }
    public ArrayList fetchInbox()
    {
        ArrayList sms = new ArrayList();

        Uri uriSms = Uri.parse("content://sms/inbox");
        Cursor cursor = getContentResolver().query(uriSms, new String[]{"_id", "address", "date", "body"},null,null,"date asc");

        cursor.moveToFirst();
        while  (cursor.moveToNext())
        {
            String address = cursor.getString(1);
            String body = cursor.getString(3);
            if(body.length()<=16) {
                sms.add(body);
                // tts.speak(xMessageText, TextToSpeech.QUEUE_FLUSH, null);
                //  Toast.makeText(getApplicationContext(), "You Can Read Upto 16 Characters in this Mode", Toast.LENGTH_LONG).show();

            }
            else
            {

            }

                     }
        return sms;

    }
    @Override
    public void onDestroy() {
        // Don't forget to shutdown tts!
        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }
        super.onDestroy();
    }

    @Override
    public void onInit(int status) {

        if (status == TextToSpeech.SUCCESS) {

            int result = tts.setLanguage(Locale.US);

            if (result == TextToSpeech.LANG_MISSING_DATA
                    || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("TTS", "This Language is not supported");
            } else {
                speakOut();
            }

        } else {
            Log.e("TTS", "Initilization Failed!");
        }

    }

    private void speakOut() {
        if(xFlag.equalsIgnoreCase("1"))
        {
            //  xLinearLayout.setBackgroundResource(R.mipmap.sms); //or whatever your image is
            Toast.makeText(getApplicationContext(),"You Can Read Only In this Mode",Toast.LENGTH_LONG).show();
        }
        else if(xFlag.equalsIgnoreCase("2"))
        {
            tts.speak(xMessageText, TextToSpeech.QUEUE_FLUSH, null);
            //xLinearLayout.setBackgroundResource(R.mipmap.voice1); //or whatever your image is
           /* if(xMessageText.length()<=16) {

                tts.speak(xMessageText, TextToSpeech.QUEUE_FLUSH, null);
            }
            else
            {
                Toast.makeText(getApplicationContext(), "You Can Read Upto 16 Characters in this Mode", Toast.LENGTH_LONG).show();
            }*/
        }
        else
        {
            //  xLinearLayout.setBackgroundResource(R.mipmap.voice2); //or whatever your image is
            tts.speak(xMessageText, TextToSpeech.QUEUE_FLUSH, null);
        }

    }
}