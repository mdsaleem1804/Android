package nellaibill.incometaxcalculator;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class Webform extends AppCompatActivity {
    WebView xWebView;
    WebSettings xWebSettings;
    String xUrl="http://nellaibill.com/incometax_calculator/";
    Bundle xBundle;
    Intent xIntent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        setContentView(R.layout.webform);
        xWebView = (WebView) findViewById(R.id.webView1);

        xWebSettings = xWebView.getSettings();
        xWebSettings.setJavaScriptEnabled(true);
        xWebView.loadUrl(xUrl);
    }



}




