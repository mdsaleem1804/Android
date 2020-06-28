
package nellaibill.tnmutualtransfer;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

import java.util.List;

public class ListData extends AppCompatActivity {
    InterstitialAd mInterstitialAd;
    AdView mAdView,mAdView1;
    AdRequest xBanner,xBanner1;
    ListView list;
    Intent xIntent;
    String[] itemname ={
            "ENTER YOUR DETAILS",
            "SEARCH YOUR PLACE",
            "EDIT YOUR DETAILS",
            "YOUR FAVOURITES",
            "MUTUAL TRANSFER FORM",
            "MUTUAL TRANSFER NEWS",
            "FEEDBACK",
            "EXIT"
    };
    DatabaseHandler db;
    String xPhoneNumber;
    Integer[] imgid={
            R.drawable.registration,
            R.drawable.search,
            R.drawable.pic3,
            R.drawable.favourities,
            R.drawable.pic5,
            R.drawable.news,
            R.drawable.feedback,
            R.drawable.exit,
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listdata);

        mAdView = (AdView) findViewById(R.id.adView);
        mAdView1 = (AdView) findViewById(R.id.adView1);
        xBanner = new AdRequest.Builder()
                .build();
        mAdView.loadAd(xBanner);

        xBanner1 = new AdRequest.Builder()
                .build();
        mAdView1.loadAd(xBanner1);
       // LoadInterstialAd();
        try {
            customBar();
            xIntent = new Intent(getApplicationContext(), WebViewActivity.class);
            db = new DatabaseHandler(this);
            List<Contact> contacts = db.getAllContacts();
            for (Contact cn : contacts) {
                xPhoneNumber = cn.getPhoneNumber();
            }

            CustomListAdapter adapter = new CustomListAdapter(this, itemname, imgid);
            list = (ListView) findViewById(R.id.list);
            list.setAdapter(adapter);
            list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> parent, View view,
                                        int position, long id) {
                    // TODO Auto-generated method stub
                    if (position == 0) {
                        LoadInterstialAd();
                        Intent xButtonGroup = new Intent(ListData.this, ButtonGroup.class);
                        xButtonGroup.putExtra("mode", "register");
                        startActivity(xButtonGroup);

                    }
                    if (position == 1) {
                        LoadInterstialAd();
                        Intent xButtonGroup = new Intent(ListData.this, ButtonGroup.class);
                        xButtonGroup.putExtra("mode", "search");
                        startActivity(xButtonGroup);

                    }
                    if (position == 2) {
                        LoadInterstialAd();
                        Intent xButtonGroup = new Intent(ListData.this, ButtonGroup.class);
                        xButtonGroup.putExtra("mode", "edit");
                        startActivity(xButtonGroup);

                    }
                    if (position == 3) {
                       LoadInterstialAd();
                        Intent xButtonGroup = new Intent(ListData.this, ButtonGroup.class);
                        xButtonGroup.putExtra("mode", "favorites");
                        startActivity(xButtonGroup);

                    }
                    if (position == 4) {
                       LoadInterstialAd();
                        xIntent.putExtra("url", "http://www.kalviseithi.net/2017/11/mutual-transfer-form-for-teachers.html?m=1");
                        startActivity(xIntent);

                    }
                    if (position == 5) {
                        LoadInterstialAd();
                        xIntent.putExtra("url", "http://www.kalviseithi.net/2017/11/school-education-mutual-transfer-mobile.html?m=1");
                        startActivity(xIntent);

                    }
                    if (position == 6) {
                        LoadInterstialAd();
                        xIntent.putExtra("url", "http://nellaibill.com/mutual_transfer/feedback.php?mobileno=" + xPhoneNumber);
                        startActivity(xIntent);

                    }
                    if (position == 7) {
                        finish();
                        System.exit(0);
                        LoadInterstialAd();
                    }

                }
            });
        }
        catch (Exception e) {
            Toast toast = Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT);
            toast.show();
        }
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

    public void LoadInterstialAd()
    {
        mInterstitialAd = new InterstitialAd(this);

        // set the ad unit ID
        mInterstitialAd.setAdUnitId(getString(R.string.ad_id_interstitial));

        AdRequest adRequest1 = new AdRequest.Builder()
                //.addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                //.addTestDevice("CC5F2C72DF2B356BBF0DA198")
                .build();
        // Load ads into Interstitial Ads
        mInterstitialAd.loadAd(adRequest1);

        mInterstitialAd.setAdListener(new AdListener() {
            public void onAdLoaded() {
                showInterstitial();
            }
        });
    }
    private void showInterstitial() {
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        }
    }
    protected void onPause() {
        mAdView.pause();
        mAdView1.pause();
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mAdView.resume();
        mAdView1.resume();
    }
}

