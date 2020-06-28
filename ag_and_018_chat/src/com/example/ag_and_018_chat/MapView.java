package com.example.ag_and_018_chat;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

public class MapView extends Activity{

	WebView xGooglePage;
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mapview);
		xGooglePage=(WebView)findViewById(R.id.webView1);
		xGooglePage.loadUrl("http://localhost/test/map_view.php?id=2");
	}

}
