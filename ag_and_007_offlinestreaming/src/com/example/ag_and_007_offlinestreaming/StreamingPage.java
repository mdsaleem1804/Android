package com.example.ag_and_007_offlinestreaming;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

import android.app.Activity;
import android.content.Context;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.webkit.WebView;
import android.widget.EditText;

public class StreamingPage extends Activity {
	EditText xEdtWebPage;
	WebView xPage;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.streamingpage);

		xEdtWebPage = (EditText) findViewById(R.id.fedtwebpagetext);
		// String xIpAddress=getWifiApIpAddress();
		// Toast.makeText(getApplicationContext(), xIpAddress, 1000).show();

		// http://192.168.1.1/default.asp
	}

	public void loadpage(View v) {
		xPage = (WebView) findViewById(R.id.webView1);
		// xGooglePage.clearCache(true);
		xPage.loadUrl(xEdtWebPage.getText().toString());
	}

	public void wifion(View v) {
		WifiManager wifi = (WifiManager) getSystemService(Context.WIFI_SERVICE);
		wifi.setWifiEnabled(true);
	}

	public String getWifiApIpAddress() {
		try {
			for (Enumeration<NetworkInterface> en = NetworkInterface
					.getNetworkInterfaces(); en.hasMoreElements();) {
				NetworkInterface intf = en.nextElement();
				if (intf.getName().contains("wlan")) {
					for (Enumeration<InetAddress> enumIpAddr = intf
							.getInetAddresses(); enumIpAddr.hasMoreElements();) {
						InetAddress inetAddress = enumIpAddr.nextElement();
						if (!inetAddress.isLoopbackAddress()
								&& (inetAddress.getAddress().length == 4)) {
							// Log.d(TAG, inetAddress.getHostAddress());
							return inetAddress.getHostAddress();
						}
					}
				}
			}
		} catch (SocketException ex) {
			// Log.e(TAG, ex.toString());
		}
		return null;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}