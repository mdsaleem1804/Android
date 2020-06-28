package com.kalviseithi.official;

import android.content.Context;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
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
    Button button1,button2,button3,button4,
            button5,button6,button7,button8;
    ImageButton xForms,xStudyMaterials,xGo,xRl;
   // ImageView xNews;
    String xNewsName= "";
    String xNewsUrl = "";
    String xNewsName2= "";
    String xNewsUrl2 = "";
    InputStream is = null;
    String result = null;
    String line = null;
    int code;
    ScrollTextView scrolltext;
    TextView xTxtNews2;
    TextView xDeveloperDetails;
    String xDeveloperName,xDeveloperWebsite;
    AdView mAdView;
    InterstitialAd mInterstitialAd;
    AdRequest xBanner;
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
            button3 = root.findViewById(R.id.button3);

            button4 = root.findViewById(R.id.button4);
            button5 = root.findViewById(R.id.button5);
            button6 = root.findViewById(R.id.button6);

            button7 = root.findViewById(R.id.button7);
            button8 = root.findViewById(R.id.button8);
            xDeveloperDetails = root.findViewById(R.id.txtDeveloperDetails);

            xRl  = root.findViewById(R.id.rl);
            xForms = root.findViewById(R.id.forms);
            xStudyMaterials = root.findViewById(R.id.studymaterials);
            xGo = root.findViewById(R.id.go);


           // xNews = (ImageView) root.findViewById(R.id.imgFlashNews);


             scrolltext= root.findViewById(R.id.scrolltext);
            xTxtNews2= root.findViewById(R.id.news2);
            mAdView = root.findViewById(R.id.adView);
            //mAdView1 = root.findViewById(R.id.adView1);

            // Request for Ads
            xBanner = new AdRequest.Builder()

                    // Add a test device to show Test Ads
                    //.addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                    //.addTestDevice("CC5F2C72DF2B356BBF0DA198")
                    .build();

            // Load ads into Banner Ads
            mAdView.loadAd(xBanner);
           // mAdView1.loadAd(xBanner);
            if (android.os.Build.VERSION.SDK_INT > 9) {
                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                        .permitAll().build();
                StrictMode.setThreadPolicy(policy);
            }
           if( isConnectedToServer("http://nellaibill.com/kalviseithi/version2/android_select.php",30000)) {
               select();
               xDeveloperDetails.setPaintFlags(xDeveloperDetails.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
              // scrolltext.setPaintFlags(scrolltext.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
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


              // xTxtNews2.setPaintFlags(scrolltext.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

               xTxtNews2.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {
                       TextView c = (TextView) v;
                       String xUrlLink = c.getTag().toString();
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

                    loadweb("http://www.kalviseithi.net/?m=1");
                    //loadweb("http://nellaibill.com/");

                }
            });

            button2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    LoadInterstialAd();
                    loadweb("http://kalviseithiplus.blogspot.com/2017/07/news-zone.html");
                }
            });

            button3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    LoadInterstialAd();
                    loadweb("http://kalviseithiplus.blogspot.com/2017/07/study-materials-zone.html");
                }
            });

            button4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    loadweb("https://m.youtube.com/channel/UCw6_zhacVae0TjBymVqpHnA?_e_pi_=7%2CPAGE_ID10%2C9032539298");
                }
            });

            button5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    LoadInterstialAd();
                    loadweb("http://kalviseithiplus.blogspot.com/2017/07/teachers-zone.html");
                }
            });

            button6.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    loadweb("http://kalviseithiplus.blogspot.com/2017/07/students-zone.html");
                }
            });



            button7.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    loadweb("http://kalviseithiplus.blogspot.com/2017/07/official-zone.html");
                }
            });


            button8.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    loadweb("http://kalviseithiplus.blogspot.com/2017/08/contact-zone.html");
                }
            });

            xStudyMaterials.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    LoadInterstialAd();
                    loadweb("http://kalviseithiplus.blogspot.com/2017/07/study-materials-zone.html");

                }
            });

            xGo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    LoadInterstialAd();
                    loadweb("http://kalviseithiplus.blogspot.com/2017/08/go.html");
                }
            });

            xForms.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    LoadInterstialAd();
                    loadweb("http://www.tnstudy.in/2017/06/important-forms-for-teachers-students.html");
                }
            });
            xRl.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    LoadInterstialAd();
                    loadweb("http://kalviseithiplus.blogspot.com/2017/08/rl-2017.html");
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
                        "http://nellaibill.com/kalviseithi/version2/android_select.php");
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

                         xNewsName = jsonChildNode.optString("news_name");
                         xNewsUrl = jsonChildNode.optString("news_url");
                        xNewsName2 = jsonChildNode.optString("news_name2");
                        xNewsUrl2 = jsonChildNode.optString("news_url2");

                        xDeveloperName = jsonChildNode.optString("developer_name");
                        xDeveloperWebsite = jsonChildNode.optString("developer_url");
                            scrolltext.setText(xNewsName);
                            scrolltext.setTag(xNewsUrl);
                        xTxtNews2.setText(xNewsName2);
                        xTxtNews2.setTag(xNewsUrl2);

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
}