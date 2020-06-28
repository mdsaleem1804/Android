package com.kalvikural.www.kalvikuralofficial;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

 /*       FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);

        Menu menu = navigationView.getMenu();
        MenuItem xLeft2 = menu.findItem(R.id.left2);
        MenuItem xLeft3 = menu.findItem(R.id.left3);
        MenuItem xLeft4 = menu.findItem(R.id.left4);
        MenuItem xLeft5 = menu.findItem(R.id.left5);
        MenuItem xLeft6 = menu.findItem(R.id.left6);
        MenuItem xLeft7 = menu.findItem(R.id.left7);

        xLeft2.setTitle(Html.fromHtml("10 <sup>th</sup> Maths Study Materials "));
        xLeft3.setTitle(Html.fromHtml("11 <sup>th</sup> Study Materials "));
        xLeft4.setTitle(Html.fromHtml("12 <sup>th</sup> Physics Materials "));
        xLeft5.setTitle(Html.fromHtml("12 <sup>th</sup>  Public Exam Answer Key"));
        xLeft6.setTitle(Html.fromHtml("10 <sup>th</sup> Slow Learners Guide "));
        xLeft7.setTitle(Html.fromHtml("12 <sup>th</sup> Slow Learners Guide "));


        navigationView.setNavigationItemSelectedListener(this);
       displaySelectedScreen(R.id.lefthome);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
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


        String xRightMenuUrl="";
        //noinspection SimplifiableIfStatement
        if (id == R.id.rightmenu1) {
              //item.setTitle(Html.fromHtml("1<sup>th</sup> to 8<sup>th</sup> Materials "));
            xRightMenuUrl= "http://www.kalvikural.com/search/label/DEPARTMENTAL%20EXAM";

        }
        if (id == R.id.rightmenu2) {
            xRightMenuUrl= "https://tntips2016.blogspot.in/";
        }

        if (id == R.id.rightmenu3) {
            xRightMenuUrl= "http://www.kalvikural.in/";
        }
        if (id == R.id.rightmenu4 ) {
            xRightMenuUrl= "http://www.kalvikural.com/search/label/USEFUL%20SOFTWARE";

        }
        if (id == R.id.rightmenu5 ) {
            xRightMenuUrl= "http://www.kalvikural.com/search/label/ELEMENTARY%20SCHOOLS%20%20MATERIALS";
        }
        if (id == R.id.rightmenu6 ) {
            xRightMenuUrl= "http://www.kalvikural.com/search/label/10TH%20PUBLIC%20EXAM%20ORIGINAL%20QUESTION%20PAPERS";
        }
        if (id == R.id.rightmenu7 ) {
            xRightMenuUrl= "http://www.kalvikural.com/search/label/12TH%20STANDARD%20ORIGINAL%20QUESTION%20PAPERS";
        }
        Fragment fragment = new Webform();
        Bundle bundle;
        bundle = new Bundle();
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

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        displaySelectedScreen(item.getItemId());
       /* if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);*/
        return true;
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
                xUrl="http://www.kalvikural.com/search/label/NEET%20EXAM%20MATERIALS";
                break;
            case R.id.left2:
                xUrl="http://www.kalvikural.com/search/label/10TH%20MATHS%20MATERILS%20%26QUESTION%20PAPERS";
                break;
            case R.id.left3:
                xUrl="http://www.kalvikural.com/search/label/11TH%20STUDY%20MATERIALS";
                break;
            case R.id.left4:
                xUrl="http://www.kalvikural.com/search/label/12TH%20PHYSICS%20MATERIALS";
                break;
            case R.id.left5:
                xUrl="http://www.kalvikural.com/search/label/12%20PUBLIC%20EXAM%20ANSWER%20KEY";
                break;
            case R.id.left6:
                xUrl="http://www.kalvikural.com/search/label/10TH%20SLOW%20LEARNERS%20GUIDE";
                break;
            case R.id.left7:
                xUrl="http://www.kalvikural.com/search/label/12TH%20SLOW%20LEARNERS%20GUIDE";
                break;
            case R.id.left8:
                xUrl="http://www.kalvikural.com/2015/05/pg-trb-all-subjects-latest-study.html";
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
}
