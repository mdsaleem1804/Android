package nellaibill.tetcomparisonsheet;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.DownloadListener;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

public class Webform_report extends AppCompatActivity {
    public WebView mWebView;
    AdView xAdWebTop;
    AdRequest xAdReqWebTop;
    Intent xIntent;
    String xUrl;
    Bundle xBundle;
    WebSettings settings;
    private ProgressDialog progress;
    InterstitialAd mInterstitialAd;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webform);

        try {
            fn_InitViews();
            xAdReqWebTop = new AdRequest.Builder()
                    .build();
            xAdWebTop.loadAd(xAdReqWebTop);
            mWebView.clearCache(true);
            this.registerForContextMenu(mWebView);
            progress = new ProgressDialog(this);
            progress.setMessage("Loading...");
            progress.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            progress.setIndeterminate(true);
            progress.setProgress(0);
            progress.show();
            customBar();
            settings = mWebView.getSettings();
            mWebView.setWebViewClient(new WebViewClient());
            mWebView.getSettings().setJavaScriptEnabled(true);
            mWebView.getSettings().setSaveFormData(true);
            mWebView.getSettings().setDomStorageEnabled(true);
            mWebView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);

/*            settings.setLoadWithOverviewMode(true);
            settings.setUseWideViewPort(true);
            settings.setAppCacheEnabled(true);
            settings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);*/
            settings.setBuiltInZoomControls(true);
            settings.setDisplayZoomControls(true);
            settings.setDomStorageEnabled(true);

            mWebView.setWebViewClient(new WebViewClient());



            mWebView.setWebViewClient(new WebViewClient(){
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, String url) {

                    if( url.startsWith("http:") || url.startsWith("https:") ) {
                        return false;
                    }

                    // Otherwise allow the OS to handle things like tel, mailto, etc.
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    startActivity( intent );
                    return true;
                }
                @Override
                public void onPageFinished(WebView view, String url) {
                    if (progress.isShowing()) {
                        progress.dismiss();
                    }
                }
            });

            xBundle = getIntent().getExtras();
            if (xBundle != null){
                xUrl = xBundle.getString("url");
                if (xUrl != null)
                    mWebView.loadUrl(xUrl);

            }

            mWebView.setDownloadListener(new DownloadListener() {
                public void onDownloadStart(String url, String userAgent,
                                            String contentDisposition, String mimetype,
                                            long contentLength) {
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(url));
                    startActivity(i);
                }
            });

        }

        catch (Exception e) {
            Toast toast = Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    public void fn_InitViews()
    {
        xAdWebTop = (AdView)findViewById(R.id.adView);
        mWebView = (WebView)findViewById(R.id.webView1);
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
    public void IsFinish(String alertmessage) {
        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case DialogInterface.BUTTON_POSITIVE:
                       /* android.os.Process.killProcess(android.os.Process.myPid());
                        // This above line close correctly
                        finish();*/
                        moveTaskToBack(true);
                        finish();
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
        xAdWebTop.pause();
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        xAdWebTop.resume();

    }
    @Override
    public void onBackPressed() {
        if (mWebView.canGoBack()) {
            finish();
            LoadInterstialAd();
           // mWebView.goBack();
        } else {
            finish();
            LoadInterstialAd();
        }
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
}
