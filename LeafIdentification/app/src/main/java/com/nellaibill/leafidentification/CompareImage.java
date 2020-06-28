package com.nellaibill.leafidentification;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class CompareImage  extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.compareimage);
        WebView mywebview = (WebView) findViewById(R.id.webView1);
        mywebview.getSettings().setJavaScriptEnabled(true);
        mywebview.setWebViewClient(new WebViewController());
        clearCache();
        mywebview.loadUrl("http://nellaibill.com/ntech/android/leaf/demo.php");
    }
    public class WebViewController extends WebViewClient {

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }
    public void clearCache() {
        new WebView(getApplicationContext()).clearCache(true);
    }
}
