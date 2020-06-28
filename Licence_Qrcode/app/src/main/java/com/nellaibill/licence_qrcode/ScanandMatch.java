package com.nellaibill.licence_qrcode;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.List;
import java.util.UUID;

import android.os.Bundle;
import android.os.StrictMode;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;
import android.view.Menu;
import android.widget.Toast;
public class ScanandMatch extends Activity {

    private static final int ACTIVITY_RESULT_QR_DRDROID = 0;

    TextView report;
    Button xScanandMatch,xScanandSave;
    int xMaxId;
    private DBhelper mydb;
    Intent xIntent;
    DataOutputStream os;
    BluetoothAdapter bluetooth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scanandmatch);
        mydb = new DBhelper(this);
        report = (TextView) findViewById(R.id.textView2);
        xScanandMatch = (Button) findViewById(R.id.scan);
        bluetooth = BluetoothAdapter.getDefaultAdapter();
        BroadcastReceiver discoveryResult = new BroadcastReceiver() {
            public void onReceive(Context context, Intent intent) {
                String remoteDeviceName = intent.getStringExtra(BluetoothDevice.EXTRA_NAME);
                BluetoothDevice remoteDevice;

                remoteDevice = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);

                Toast.makeText(getApplicationContext(), "Discovered: " + remoteDeviceName + " address " + remoteDevice.getAddress(), Toast.LENGTH_SHORT).show();

                try{
                    BluetoothDevice device = bluetooth.getRemoteDevice(remoteDevice.getAddress());

                    Method m = device.getClass().getMethod("createRfcommSocket", new Class[] {int.class});

                    BluetoothSocket clientSocket =  (BluetoothSocket) m.invoke(device, 1);

                    clientSocket.connect();

                    os = new DataOutputStream(clientSocket.getOutputStream());

                    new clientSock().start();
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e("BLUETOOTH", e.getMessage());
                }
            }
        };

        registerReceiver(discoveryResult, new IntentFilter(BluetoothDevice.ACTION_FOUND));

        bluetooth.enable();
        if (!bluetooth.isDiscovering()) {
            bluetooth.startDiscovery();
        }





        xScanandMatch.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                Intent i = new Intent("la.droid.qr.scan");
                try {
                    startActivityForResult(i, ACTIVITY_RESULT_QR_DRDROID);
                }
                catch (ActivityNotFoundException activity) {
                  //  ScanandMatch.qrDroidRequired(ScanandMatch.this);
                }
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);

        if( ACTIVITY_RESULT_QR_DRDROID == requestCode
                && data != null && data.getExtras() != null ) {

            String result = data.getExtras().getString("la.droid.qr.result");

            report.setText(result);
            report.setVisibility(View.VISIBLE);

           if(mydb.CheckUsers(result))
           {
               Toast.makeText(getApplicationContext(), "Matched!",
                       Toast.LENGTH_LONG).show();
              // xIntent = new Intent(ScanandMatch.this,
                      // BluetoothList.class);



               //startActivity(xIntent);
           }
else{
               Toast.makeText(getApplicationContext(), "Not-Matched!",
                       Toast.LENGTH_LONG).show();
           }


        }
    }

    /*
     *
     * If we don't have QRDroid Application in our Device,
     * It will call below method (qrDroidRequired)
     *
     */
    public class clientSock extends Thread {
        public void run () {
            try {
                os.writeBytes("anything you want"); // anything you want
                os.flush();
            } catch (Exception e1) {
                e1.printStackTrace();
                return;
            }
        }
    }
    protected static void qrDroidRequired(final ScanandSave activity) {
        // TODO Auto-generated method stub

        AlertDialog.Builder AlertBox = new AlertDialog.Builder(activity);

        AlertBox.setMessage("QRDroid Missing");

        AlertBox.setPositiveButton("Direct Download", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                // TODO Auto-generated method stub

                activity.startActivity(new Intent(Intent.ACTION_VIEW,
                        Uri.parse("http://droid.la/apk/qr/")));
            }
        });

        AlertBox.setNeutralButton("From Market", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub

                activity.startActivity(new Intent(Intent.ACTION_VIEW,
                        Uri.parse("http://market.android.com/details?id=la.droid.qr")));
            }
        });

        AlertBox.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub

                dialog.cancel();
            }
        });

        AlertBox.create().show();
    }


}
