package com.hellotamila.messagereadaudio;

import android.app.Activity;
    import android.content.Intent;
    import android.os.Bundle;
    import android.support.v7.app.AppCompatActivity;
    import android.util.Log;
    import android.view.View;
    import android.widget.Toast;

public class HomePage extends AppCompatActivity {
    Intent xIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try{
            setContentView(R.layout.activity_read_sms);
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
                Toast.makeText(HomePage.this,"Message From App: "+messageText,Toast.LENGTH_LONG).show();
                String xMessage=messageText;

                    Intent  xIntent = new Intent(getApplicationContext(), BluetoothFileTransfer.class);
                    xIntent.putExtra("url", xMessage);
                    startActivity(xIntent);

               /* if(xMessage.equalsIgnoreCase("L1"))
                {
                    Intent  xIntent = new Intent(getApplicationContext(), BluetoothFileTransfer.class);
                    xIntent.putExtra("url", "/storage/emulated/0/mus/a1.m4a");
                    startActivity(xIntent);
           *//* mPlayer= MediaPlayer.create(ReadSms.this, R.raw.a1);
            mPlayer.start();*//*
                }
                else  if(xMessage.equalsIgnoreCase("L2"))
                {
                    Intent  xIntent = new Intent(getApplicationContext(), BluetoothFileTransfer.class);
                    xIntent.putExtra("url", "/storage/emulated/0/mus/a2.m4a");
                    startActivity(xIntent);
                }
                else if(xMessage.equalsIgnoreCase("L3"))
                {
                    Intent  xIntent = new Intent(getApplicationContext(), BluetoothFileTransfer.class);
                    xIntent.putExtra("url", "/storage/emulated/0/mus/a3.m4a");
                    startActivity(xIntent);
                }*/
             /*   else if(xMessage.equalsIgnoreCase("D1"))
                {
                    Intent  xIntent = new Intent(getApplicationContext(), BluetoothFileTransfer.class);
                    xIntent.putExtra("url", "/storage/emulated/0/mus/d1.docx");
                    startActivity(xIntent);
                }
                else if(xMessage.equalsIgnoreCase("D2"))
                {
                    Intent  xIntent = new Intent(getApplicationContext(), BluetoothFileTransfer.class);
                    xIntent.putExtra("url", "/storage/emulated/0/mus/d2.docx");
                    startActivity(xIntent);
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Invalid Text Recieved",Toast.LENGTH_LONG).show();
                }*/

            }
        });

    }
    public void read_sms(View v) {
        xIntent = new Intent(getApplicationContext(), PlayLargeSms.class);
        xIntent.putExtra("flag", "1");
        startActivity(xIntent);
    }
    public void play_sms(View v) {
        xIntent = new Intent(getApplicationContext(), PlaySmallSms.class);
        xIntent.putExtra("flag", "2");
        startActivity(xIntent);
    }
    public void play_large_sms(View v) {
        xIntent = new Intent(getApplicationContext(), PlayLargeSms.class);
        xIntent.putExtra("flag", "3");
        startActivity(xIntent);
    }
    public void voicetotext(View v) {
        xIntent = new Intent(getApplicationContext(), VoicetoText.class);
        startActivity(xIntent);
    }
}
