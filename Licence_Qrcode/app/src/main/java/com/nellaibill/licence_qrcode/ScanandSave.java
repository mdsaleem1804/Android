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

public class ScanandSave extends Activity {

    private static final int ACTIVITY_RESULT_QR_DRDROID = 0;

    TextView report;
    Button  xScanandSave;
    int xMaxId;
    private DBhelper mydb;
    Intent xIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scanandsave);
        mydb = new DBhelper(this);
        report = (TextView) findViewById(R.id.textView2);
        xScanandSave = (Button) findViewById(R.id.scanandsave);

        xScanandSave.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                Intent i = new Intent("la.droid.qr.scan");
                try {
                    startActivityForResult(i, ACTIVITY_RESULT_QR_DRDROID);
                } catch (ActivityNotFoundException activity) {
                    ScanandSave.qrDroidRequired(ScanandSave.this);
                }
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);

        if (ACTIVITY_RESULT_QR_DRDROID == requestCode
                && data != null && data.getExtras() != null) {

            String result = data.getExtras().getString("la.droid.qr.result");

            report.setText(result);
            report.setVisibility(View.VISIBLE);

            xMaxId = mydb
                    .fn_GetMaxID("SELECT CASE WHEN(id>0)then max(id+1) Else 1 " +
                            "END AS userno from tbl_licenecdetails");
            mydb.insertRegistration(xMaxId, result);


        }
    }

    /*
     *
     * If we don't have QRDroid Application in our Device,
     * It will call below method (qrDroidRequired)
     *
     */

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
