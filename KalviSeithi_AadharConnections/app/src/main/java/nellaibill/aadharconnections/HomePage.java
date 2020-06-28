package nellaibill.aadharconnections;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.kirianov.multisim.MultiSimTelephonyManager;

public class HomePage extends AppCompatActivity {
    MultiSimTelephonyManager multiSimTelephonyManager;
    Button xBtn1,xBtn2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        xBtn1= (Button)findViewById(R.id.btnsim1);
        xBtn2 = (Button)findViewById(R.id.btnsim2);
        try {
         multiSimTelephonyManager = new MultiSimTelephonyManager(this, new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                    useInfo();
            }
        });
        }
        catch(Exception e)
        {
            Toast.makeText(getApplicationContext(),e.toString(),Toast.LENGTH_LONG).show();
        }
    }

    public void  refresh(View v)
    {
        useInfo();
        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }
    public void  sim1(View v)
    {
        try {
         /*  String  phone_number="9578795653";
            Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phone_number));
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra("com.android.phone.extra.slot", 0); //For sim 1
            startActivity(intent);*/

            Intent callIntent = new Intent(Intent.ACTION_CALL);
            callIntent.setData(Uri.parse("tel:0377778888"));
            callIntent.putExtra("com.android.phone.extra.slot", 0); //For sim 1
            if (ActivityCompat.checkSelfPermission(HomePage.this,
                    Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            startActivity(callIntent);

        } catch (Exception e) {
            //TODO smth
        }
    }
    public void  sim2(View v)
    {
        try {
            Intent callIntent = new Intent(Intent.ACTION_CALL);
            callIntent.setData(Uri.parse("tel:0377778888"));
            callIntent.putExtra("com.android.phone.extra.slot", 1); //For sim 1
            if (ActivityCompat.checkSelfPermission(HomePage.this,
                    Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            startActivity(callIntent);
        } catch (Exception e) {
            //TODO smth
        }
    }
    public void useInfo() {

        // get number of slots:
        if (multiSimTelephonyManager != null) {
            multiSimTelephonyManager.sizeSlots();
        }

        // get info from each slot:
        if (multiSimTelephonyManager != null) {
            for(int i = 0; i < multiSimTelephonyManager.sizeSlots(); i++) {
                if(i==0) {
                    xBtn1.setText("Connect Aadhar -"+multiSimTelephonyManager.getSlot(i).getNetworkOperatorName().toString());
                }
                else {
                    xBtn2.setText("Connect Aadhar -"+multiSimTelephonyManager.getSlot(i).getNetworkOperatorName().toString());
                }
            }
        }
    }public void updateInfo() {

        // for update UI
        runOnUiThread(new Runnable() {
                          @Override
                          public void run() {
                              multiSimTelephonyManager.update();
                              useInfo();
                          }
                      });

                // for update background information
                multiSimTelephonyManager.update();
        useInfo();
    }

}
