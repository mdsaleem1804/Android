package com.kalvikural.www.kalvikuralofficial;


import android.content.Context;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class Menu1 extends Fragment {
   public WebView mWebView;
    Button button1,button2,button3,button4,button5,button6,button7,button8,button9;

    String xDeveloperName,xDeveloperWebsite;
    String xButtonValue = "";
    String xButtonName = "";
    InputStream is = null;
    String result = null;
    String line = null;
    int code;
    TextView xDeveloperDetails;
    AdView mAdView;
    InterstitialAd mInterstitialAd;
    AdRequest xBanner;
    ScrollTextView scrolltext;

    ImageButton xForms,xStudyMaterials,xNews,xContact;
    public static Fragment newInstance(Context context) {
        Menu1 f = new Menu1();
        return f;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_menu_1, null);
        try {
            button1 = root.findViewById(R.id.button1);
            button2 = root.findViewById(R.id.button2);
            ((Button)root.findViewById(R.id.button2)).setText(Html.fromHtml("1<sup>st</sup>-12<sup>th </sup>MATERIALS"));
            button3 = root.findViewById(R.id.button3);

            button4 = root.findViewById(R.id.button4);
            button5 = root.findViewById(R.id.button5);
            button6 = root.findViewById(R.id.button6);

            button7 = root.findViewById(R.id.button7);
            button8 = root.findViewById(R.id.button8);
            button9 = root.findViewById(R.id.button9);

            xNews  = root.findViewById(R.id.news);
            xForms = root.findViewById(R.id.forms);
            xStudyMaterials = root.findViewById(R.id.studymaterial);
            xContact = root.findViewById(R.id.contact);

            xDeveloperDetails = root.findViewById(R.id.txtDeveloperDetails);

            xDeveloperDetails.setPaintFlags(xDeveloperDetails.getPaintFlags()| Paint.UNDERLINE_TEXT_FLAG);
            scrolltext= root.findViewById(R.id.scrolltext);
            mAdView = root.findViewById(R.id.adView);
            // Request for Ads
            xBanner = new AdRequest.Builder()

                    // Add a test device to show Test Ads
                    //.addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                    //.addTestDevice("CC5F2C72DF2B356BBF0DA198")
                    .build();

            // Load ads into Banner Ads
            mAdView.loadAd(xBanner);
            mInterstitialAd = new InterstitialAd(getContext());

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

            if (android.os.Build.VERSION.SDK_INT > 9) {
                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                        .permitAll().build();
                StrictMode.setThreadPolicy(policy);
            }
            if( isConnectedToServer("http://nellaibill.com",30000)) {
                select();
                xDeveloperDetails.setPaintFlags(xDeveloperDetails.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
                scrolltext.setPaintFlags(scrolltext.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
                scrolltext.startScroll();

                scrolltext.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ScrollTextView b = (ScrollTextView) v;
                        String xUrlLink = b.getTag().toString();
                        LoadInterstialAd();
                        loadweb(xUrlLink);

                    }
                });

                xDeveloperDetails.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        TextView b1 = (TextView) v;
                        String xUrlLink = b1.getTag().toString();
                        loadweb(xUrlLink);
                    }
                });
            }
            button1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    loadweb("http://www.kalvikural.com/?m=1");

                }
            });

            button2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    loadweb("http://questionpapersmaterials.blogspot.in/2017/09/all-study-materials-link-one-page.html?m=1");
                }
            });

            button3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    loadweb("http://www.haistudents.com/2017/09/tnpsctrb-tet-rrb-bank-exam-study.html?m=1");
                }
            });

            button4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    loadweb("http://www.padivam.com/2017/09/all-forms-for-teachers-and-students.html?m=1");
                }
            });

            button5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    loadweb("http://www.kalvikural.net/2017/09/latest-dse-dee-proceedings-and-special.html?m=1");
                }
            });

            button6.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   loadweb("http://www.kalvigo.com/2017/09/all-government-orders-one-page-all-gos.html?m=1");
                }
            });



            button7.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    loadweb("http://www.kalvikuralvideo.com/2017/09/all-videos-one-page.html?m=1");
                }
            });


            button8.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    loadweb("http://www.kalvikural.com/search/label/SCHOOL%20EVENTS");
                }
            });
            button9.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    loadweb("http://www.kalvikural.com/search/label/TODAY%20JOBS");
                }
            });






            xForms.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   // LoadInterstialAd();
                    loadweb("http://www.padivam.com/");
                }
            });

            xStudyMaterials.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //LoadInterstialAd();
                    loadweb("http://www.haistudents.com/");

                }
            });
            xNews.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   // LoadInterstialAd();
                    loadweb("http://www.kalvikural.com/search/label/EDNL%20NEWS");
                }
            });

            xContact.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   // LoadInterstialAd();
                    loadweb("http://www.kalvikural.com/p/kalvikural-contact-numbers.html");
                }
            });
        }
        catch(Exception e)
        {
            String xError=e.toString();
        }
        return root;
    }

    public void LoadInterstialAd()
    {
        mInterstitialAd = new InterstitialAd(getContext());

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
    public void loadweb(String xUrl)
    {
        Webform fragment2 = new Webform();
        Bundle bundle = new Bundle();
        bundle.putString("urlkey", xUrl);
        fragment2.setArguments(bundle);

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.content_frame, fragment2);
        fragmentTransaction.addToBackStack("fragment2");
        fragmentTransaction.commit();
    }
    public boolean isConnectedToServer(String url, int timeout) {
        try{
            URL myUrl = new URL(url);
            URLConnection connection = myUrl.openConnection();
            connection.setConnectTimeout(timeout);
            connection.connect();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    public void select()
    {
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        try {
            ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();

            nameValuePairs.add(new BasicNameValuePair("username", "saleem"));

            try {
                HttpClient httpclient = new DefaultHttpClient();
                HttpPost httppost = new HttpPost(
                        "http://nellaibill.com/kalvikural/android_select.php");
                httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                HttpResponse response = httpclient.execute(httppost);
                HttpEntity entity = response.getEntity();
                is = entity.getContent();
                Log.e("pass 1", "connection success ");
            } catch (Exception e) {
                Log.e("Fail 1", e.toString());
                //  Toast.makeText(getApplicationContext(), "Invalid IP Address",
                //Toast.LENGTH_LONG).show();
            }

            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(
                        is, "iso-8859-1"), 8);
                StringBuilder sb = new StringBuilder();
                while ((line = reader.readLine()) != null) {
                    sb.append(line + "\n");
                }
                is.close();
                result = sb.toString();
                Log.e("pass 2", "connection success ");
            } catch (Exception e) {
                Log.e("Fail 2", e.toString());
            }

            try {

                try {
                    JSONObject jsonResponse = new JSONObject(result);
                    JSONArray jsonMainNode = jsonResponse.optJSONArray("kalvikural_data");

                    for (int i = 0; i < jsonMainNode.length(); i++) {
                        JSONObject jsonChildNode = jsonMainNode.getJSONObject(i);
                        xButtonName = jsonChildNode.optString("news_name");
                        xButtonValue = jsonChildNode.optString("news_url");
                        xDeveloperName = jsonChildNode.optString("developer_name");
                        xDeveloperWebsite = jsonChildNode.optString("developer_url");
                        scrolltext.setText(xButtonName);
                        scrolltext.setTag(xButtonValue);
                        xDeveloperDetails.setText(xDeveloperName);
                        xDeveloperDetails.setTag(xDeveloperWebsite);


                    }
                } catch (JSONException e) {
                    //Toast.makeText(getApplicationContext(), "Error" + e.toString(),
                    //Toast.LENGTH_SHORT).show();
                }

            } catch (Exception e) {
                Log.e("Fail 3", e.toString());
            }

            //   NewActivity xActivity = (NewActivity) getActivity();
            // String xValue=xActivity.xButtonName;
            //Toast.makeText(getActivity(),xButtonName, Toast.LENGTH_SHORT).show();
        }catch(Exception e)
        {
            Toast.makeText(getActivity(), e.toString(), Toast.LENGTH_SHORT).show();
        }
    }
}