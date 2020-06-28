package com.nellaibill.petcollege;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.BottomNavigationView;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.apache.http.util.EncodingUtils;

public class TimeTable extends Activity {

    WebView xWebView;
    WebSettings xWebSettings;
    String xUrl;
    Bundle xBundle;
    Intent xIntent;

    BottomNavigationView navigation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webform);
        Bundle b = new Bundle();
        b = getIntent().getExtras();
      String   xDepartment = b.getString("passdepartment");
        String   xYear = b.getString("passyear");
        Toast.makeText(getApplicationContext(),xDepartment + "-" + xYear, Toast.LENGTH_SHORT).show();
       // xUrl="http://hellotamila.com/barani/petcollege/viewtimetable.php?dept ="+xDepartment+"&year="+xYear;

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        setContentView(R.layout.webform);
        xWebView = (WebView) findViewById(R.id.webView1);
        xWebSettings = xWebView.getSettings();
        //customBar();
        xWebSettings.setJavaScriptEnabled(true);
        String postData = "dept="+xDepartment+"&year="+xYear;

        xWebView.postUrl(
                "http://hellotamila.com/barani/petcollege/viewtimetable.php",
                EncodingUtils.getBytes(postData, "BASE64"));
      //  xWebView.loadUrl(xUrl);

    }


}