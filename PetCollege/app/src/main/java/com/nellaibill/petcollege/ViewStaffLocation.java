package com.nellaibill.petcollege;


import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Toast;


public class ViewStaffLocation extends AppCompatActivity {
    WebView xWebView;
    WebSettings xWebSettings;
    String xUrl;
    Bundle xBundle;
    Intent xIntent;
    SimpleGetterAndSetter obj;
    DataBaseConnection mCon;
    private SQLiteDatabase db;
    private Cursor xCursor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        openDatabase();
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        Bundle b = new Bundle();
        b = getIntent().getExtras();
        String name = b.getString("selectedname");
        setContentView(R.layout.webform);
        obj = new SimpleGetterAndSetter();
        try {
            String xQry = "select * from staff_details where username='" + name + "'";
            xCursor = db.rawQuery(xQry, null);
            while (xCursor.moveToNext()) {
                xUrl = "http://maps.google.com/?q=" + xCursor.getString(xCursor.getColumnIndex("stafflat")) + "," + xCursor.getString(xCursor.getColumnIndex("stafflong"));
            }
            xWebView = (WebView) findViewById(R.id.webView1);
            xWebSettings = xWebView.getSettings();
            xWebSettings.setJavaScriptEnabled(true);
            if (xUrl != null)
                xWebView.loadUrl(xUrl);

        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
        }
    }

    protected void openDatabase() {
        db = openOrCreateDatabase("petcollege.db", Context.MODE_PRIVATE, null);
    }

}





