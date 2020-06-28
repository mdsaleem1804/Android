package nellaibill.tnmutualtransfer;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
public class WebViewActivity extends AppCompatActivity {
    public WebView mWebView;
    AdView xAdWebTop;
    AdRequest xAdReqWebTop;
    Intent xIntent;
    String xUrl;
    Bundle xBundle;
    WebSettings settings;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webview);

        try{
        fn_InitViews();
        customBar();
            xAdReqWebTop = new AdRequest.Builder()
                .build();
            xAdWebTop.loadAd(xAdReqWebTop);
        mWebView.clearCache(true);
        this.registerForContextMenu(mWebView);
        settings= mWebView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setLoadWithOverviewMode(true);
        // settings.setUseWideViewPort(true);
        settings.setAppCacheEnabled(true);
        settings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        settings.setDomStorageEnabled(true);
            mWebView.setWebChromeClient(new WebChromeClient());

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
            });

        xBundle = getIntent().getExtras();
        if (xBundle != null){
             xUrl = xBundle.getString("url");
            if (xUrl != null)
                mWebView.loadUrl(xUrl);

        }
    }
        catch (Exception e) {
        Toast toast = Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT);
        toast.show();
    }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);//Menu Resource, Menu
        return true;
    }
    public void fn_InitViews()
    {
        xAdWebTop = (AdView)findViewById(R.id.adwebviewtop);
        mWebView = (WebView)findViewById(R.id.webView1);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.home:
                xIntent = new Intent(getApplicationContext(), ListData.class);
                startActivity(xIntent);
                return true;
            case R.id.exit:
                IsFinish("Want to close app?");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    public void customBar(){
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        actionBar.setCustomView(getLayoutInflater().inflate(R.layout.custom_actionbar_title, null),
                new ActionBar.LayoutParams(
                        ActionBar.LayoutParams.WRAP_CONTENT,
                        ActionBar.LayoutParams.MATCH_PARENT,
                        Gravity.CENTER
                )
        );
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
            mWebView.goBack();
        } else {
            super.onBackPressed();
        }
    }
}
