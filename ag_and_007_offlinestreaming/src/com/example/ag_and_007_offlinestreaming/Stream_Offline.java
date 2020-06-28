package com.example.ag_and_007_offlinestreaming;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

public class Stream_Offline extends Activity{

	WebView xGooglePage;
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.streamingpage_offline);
		xGooglePage=(WebView)findViewById(R.id.webView1);
		xGooglePage.loadUrl("http://www.google.com/");
	}

}
