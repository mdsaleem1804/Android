package nellaibill.tetcomparisonsheet;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
public class HomePage extends AppCompatActivity {
    AdRequest xBanner;
    AdView mAdView;
    Intent xIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepage_tet);
        customBar();
        MobileAds.initialize(this, getString(R.string.admob_app_id));
        mAdView =(AdView) findViewById(R.id.adView);
        xBanner = new AdRequest.Builder()
                // Add a test device to show Test Ads
                //.addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                //.addTestDevice("CC5F2C72DF2B356BBF0DA198")
                .build();
        mAdView.loadAd(xBanner);
    }
    public void tet_paper1_weightage(View v){
        xIntent = new Intent(getApplicationContext(), Webform.class);
        xIntent.putExtra("url", "http://nellaibill.com/tet/paper1_weightage.php");
        startActivity(xIntent);
    }
    public void tet_paper1_entry(View v){
        xIntent = new Intent(getApplicationContext(), Webform.class);
        xIntent.putExtra("url", "http://nellaibill.com/tet/paper1_tet_entry.html");
        startActivity(xIntent);
    }
    public void tet_paper1_report(View v){
        xIntent = new Intent(getApplicationContext(), Webform_report.class);
        xIntent.putExtra("url", "http://nellaibill.com/tet/paper1_tet_report.php");
        startActivity(xIntent);
    }
    public void tet_paper2_weightage(View v){
        xIntent = new Intent(getApplicationContext(), Webform.class);
        xIntent.putExtra("url", "http://nellaibill.com/tet/paper2_weightage.php");
        startActivity(xIntent);
    }
    public void tet_paper2_entry(View v){
        xIntent = new Intent(getApplicationContext(), Webform.class);
        xIntent.putExtra("url", "http://nellaibill.com/tet/paper2_tet_entry.html");
        startActivity(xIntent);
    }

    public void tet_paper2_report(View v){
        xIntent = new Intent(getApplicationContext(), Webform_report.class);
        xIntent.putExtra("url", "http://nellaibill.com/tet/paper2_tet_report.php");
        startActivity(xIntent);
    }
    public void developer(View v){
        xIntent = new Intent(getApplicationContext(), Webform.class);
            xIntent.putExtra("url", "http://www.nellaibill.com/developer/");
        startActivity(xIntent);
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
    @Override
    public void onBackPressed() {
        //super.onBackPresssed();
        IsFinish("Want to close app?");
    }

    public void IsFinish(String alertmessage) {
        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case DialogInterface.BUTTON_POSITIVE:
                        android.os.Process.killProcess(android.os.Process.myPid());
                        // This above line close correctly
                        //finish();
                        break;

                    case DialogInterface.BUTTON_NEGATIVE:

                        break;
                }
            }
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(alertmessage)
                .setPositiveButton("Yes", dialogClickListener)
                .setNegativeButton("No", dialogClickListener).show();
    }

    protected void onPause() {
        mAdView.pause();
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mAdView.resume();
    }

}
