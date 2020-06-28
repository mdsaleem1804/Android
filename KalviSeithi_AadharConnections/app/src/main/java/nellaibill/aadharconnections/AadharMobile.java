package nellaibill.aadharconnections;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.kirianov.multisim.MultiSimTelephonyManager;
public class AadharMobile extends AppCompatActivity {
    MultiSimTelephonyManager multiSimTelephonyManager;
    Button xBtn1, xBtn2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aadhar_mobile);
        customBar();
        try {
            xBtn1 = (Button) findViewById(R.id.btnsim1);
            xBtn2 = (Button) findViewById(R.id.btnsim2);
            multiSimTelephonyManager = new MultiSimTelephonyManager(this, new BroadcastReceiver() {
                @Override
                public void onReceive(Context context, Intent intent) {
                    useInfo();
                }
            });
        }catch(Exception e)
        {
            Toast.makeText(getApplicationContext(),e.toString(),Toast.LENGTH_LONG).show();
        }
    }
    public void  refresh(View v)
    {
        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }
    public void  sim1(View v)
    {
        try {
            Intent callIntent = new Intent(Intent.ACTION_CALL);
            callIntent.setData(Uri.parse("tel:14546"));
            callIntent.putExtra("com.android.phone.extra.slot", 0);
            startActivity(callIntent);
        } catch (Exception e) {
            //TODO smth
        }
    }
    public void  sim2(View v)
    {
        try {
            Intent callIntent = new Intent(Intent.ACTION_CALL);
            callIntent.putExtra("com.android.phone.extra.slot",1);
            callIntent.setData(Uri.parse("tel:14546"));
            startActivity(callIntent);
        } catch (Exception e) {
            //TODO smth
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
