package com.kalviseithi.official;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private static final int TIME_DELAY = 2000;
    private static long back_pressed;
    InterstitialAd mInterstitialAd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (getIntent().getBooleanExtra("EXIT", false)) {
            finish();
        }
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        //add this line to display menu1 when the activity is loaded
       displaySelectedScreen(R.id.lefthome);
    }

    @Override
    public void onBackPressed() {

        android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
        if (fm.getBackStackEntryCount() > 1) {
            fm.popBackStack();
        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Are you sure you want to Close this App?")
                    .setCancelable(false)
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                          //  MainActivity.this.finish();
                            MainActivity.this.onSuperBackPressed();
                        }
                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });
            AlertDialog alert = builder.create();
            alert.show();
       }

    }

    private void onSuperBackPressed() {
        super.onBackPressed();
       finish();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //creating fragment object




        String xRightMenuUrl="";
        //noinspection SimplifiableIfStatement
        if (id == R.id.rightmenu1) {

            xRightMenuUrl= "http://www.tnstudy.in/";

        }
        if (id == R.id.rightmenu2) {
            xRightMenuUrl= "http://www.tnmaterials.blogspot.com/";
        }

        if (id == R.id.rightmenu3) {
            xRightMenuUrl= "http://www.tnvelai.blogspot.com/";
        }
        if (id == R.id.rightmenu4 ) {
            xRightMenuUrl= "https://www.facebook.com/www.kalviseithi.net/";

        }
        if (id == R.id.rightmenu5 ) {
            xRightMenuUrl= "https://www.twitter.com/Kalviseithi1";
        }
        if (id == R.id.rightmenu6 ) {
            Toast.makeText(getApplicationContext(), "9965642731", Toast.LENGTH_LONG).show();
            return true;
        }
        if (id == R.id.rightmenu7 ) {
            xRightMenuUrl= "http://www.kalviseithi.net/2015/04/blog-post_778.html?m=1";
        }

        if (id == R.id.rightmenu8 ) {
            xRightMenuUrl= "http://www.kalviseithi.net/2015/12/blog-post_711.html?m=1";
        }

        if (id == R.id.rightmenu9 ) {
            xRightMenuUrl= "http://inaiyumkarangal.blogspot.in/2015/12/blog-post.html";
        }
        if (id == R.id.rightmenu10 ) {
            xRightMenuUrl= "http://tntechguru.blogspot.com/";
        }

        if (id == R.id.rightmenu11 ) {
           /* Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);*/
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.putExtra("EXIT", true);
            startActivity(intent);

        }
if(xRightMenuUrl!="") {
    Fragment fragment = new Webform();
    Bundle bundle;
    bundle = new Bundle();
    LoadInterstialAd();
    bundle.putString("urlkey", xRightMenuUrl);
    fragment.setArguments(bundle);
    //replacing the fragment
    if (fragment != null) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.content_frame, fragment);
        ft.addToBackStack("fragment");
        //  ft.detach(fragment);
        // ft.attach(fragment);
        ft.commit();
    }
}
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return super.onOptionsItemSelected(item);
    }

    private void displaySelectedScreen(int itemId) {
        Fragment fragment=null;
        fragment = new Webform();
        Bundle bundle;
        bundle = new Bundle();
        String xUrl="";
        //initializing the fragment object which is selected
        switch (itemId) {
            case R.id.lefthome:
                  fragment = new Menu1();
                break;
            case R.id.left1:
                LoadInterstialAd();
                xUrl="http://kalviseithiplus.blogspot.in/2017/05/all-categories-labels.html";
                break;
            case R.id.left2:
                LoadInterstialAd();
                xUrl="http://kalviseithiplus.blogspot.in/2017/05/top-50-news.html";
                break;
            case R.id.left3:
                LoadInterstialAd();
                xUrl="http://www.kalviseithi.net/search/label/PROCEEDING/?m=1";
                break;
            case R.id.left4:
                LoadInterstialAd();
                xUrl="http://www.tnstudy.in/2017/02/post-continuation-order-pay-order.html";
                break;
            case R.id.left5:
                xUrl="http://kalviseithiplus.blogspot.com/2017/08/results.html";
                break;
            case R.id.left6:
                LoadInterstialAd();
                xUrl="http://kalviseithiplus.blogspot.com/2017/08/school-calendar.html";
                break;
            case R.id.left7:
                LoadInterstialAd();
                xUrl="http://www.textbooksonline.tn.nic.in/";
                break;
            case R.id.left8:
                LoadInterstialAd();
                xUrl="http://218.248.44.123/auto_cps/public/";
                break;
            case R.id.left9:
                LoadInterstialAd();
                xUrl="http://www.agae.tn.nic.in/onlinegpf/";
                break;
            case R.id.left10:

                Intent i = new Intent(Intent.ACTION_VIEW);
               // i.setData(Uri.parse("http://epayroll.tn.gov.in/tngepay/Login/employeelogin.aspx"));
                i.setData(Uri.parse("http://epayroll.tn.gov.in/tngepay/Login/Payrolllogin.aspx"));

                startActivity(i);

                break;

            case R.id.left11:
                LoadInterstialAd();
                xUrl="http://emis.tnschools.gov.in/";
                break;

        }

        bundle.putString("urlkey", xUrl);
        fragment.setArguments(bundle);
        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_frame, fragment);
            ft.addToBackStack("fragment");
           // ft.detach(fragment);
           // ft.attach(fragment);
            ft.commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }
    public void LoadInterstialAd()
    {
        mInterstitialAd = new InterstitialAd(getApplicationContext());

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

    public boolean onNavigationItemSelected(MenuItem item) {

        //calling the method displayselectedscreen and passing the id of selected menu
        displaySelectedScreen(item.getItemId());
        //make this method blank
        return true;
    }


}

