package com.example.ag_and_013;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class HomePage extends Activity {
	private BluetoothAdapter BTAdapter = BluetoothAdapter.getDefaultAdapter();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.homepage);
		   
	}
	
	public void bluetooth(View v)
	{
		Intent intent = new Intent(HomePage.this,
			BluetoothOperations.class);
		startActivity(intent);
	}
	public void registermobileno(View v)
	{
		Intent intent = new Intent(HomePage.this,
				Register.class);
		startActivity(intent);
	}
	
}

