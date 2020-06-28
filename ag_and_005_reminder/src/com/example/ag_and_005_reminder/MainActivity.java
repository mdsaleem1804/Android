package com.example.ag_and_005_reminder;

import java.util.List;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {
	DataBaseConnection mCon;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mCon = new DataBaseConnection(this);
		/*
		 * if(mCon.CheckNotification()) { addNotification(); }
		 */
		List<String> a = mCon.fn_CheckReminders();

		int i = 0;
		while (i < a.size()) {
			addNotification(a.get(i));
			i++;
		}

	}

	private void addNotification(String xMessage) {
		/*
		 * NotificationCompat.Builder builder = new
		 * NotificationCompat.Builder(this)
		 * .setSmallIcon(R.drawable.ic_launcher) .setContentTitle("")
		 * .setContentText(xMessage);
		 * 
		 * Intent notificationIntent = new Intent(this, MainActivity.class);
		 * PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
		 * notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
		 * builder.setContentIntent(contentIntent);
		 * 
		 * // Add as notification NotificationManager manager =
		 * (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
		 * manager.notify(0, builder.build());
		 */

		NotificationManager notif = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
		Notification notify = new Notification.Builder(getApplicationContext())
				.setContentTitle(xMessage).setContentText(xMessage)
				.setContentTitle(xMessage).setSmallIcon(R.drawable.ic_launcher)
				.build();

		notify.flags |= Notification.FLAG_AUTO_CANCEL;
		notif.notify(0, notify);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void expenses(View v) {
		Intent intent = new Intent(MainActivity.this, addexpenses.class);
		startActivity(intent);
	}

	public void reminder(View v) {
		Intent intent = new Intent(MainActivity.this, reminder.class);
		startActivity(intent);
	}

	public void report(View v) {
		Intent intent = new Intent(MainActivity.this, reportmain.class);
		startActivity(intent);
	}

	public void analysis(View v) {
		Intent intent = new Intent(MainActivity.this, Chart.class);
		startActivity(intent);
	}
}
