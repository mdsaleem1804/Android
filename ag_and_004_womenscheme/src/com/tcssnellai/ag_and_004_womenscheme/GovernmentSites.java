package com.tcssnellai.ag_and_004_womenscheme;

import java.util.List;

import com.tcssnellai.ag_and_004_womenscheme.R;

import android.app.ListActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class GovernmentSites extends ListActivity {

	ListView listView;
	String xSession;
	Intent xIntent;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		String[] xListData = { "www.india.gov.in", "www.digitalindia.gov.in",
				"www.sarkariyojna.co.in", "www.rural.nic.in",
				"www.minorityaffairs.gov.in","www.startupindia.gov.in" };
		setListAdapter(new ArrayAdapter<String>(this, R.layout.listdata,
				xListData));
		listView = getListView();
		listView.setTextFilterEnabled(true);
		listView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				// ListView Clicked item value
				try {

					String xSelectedValue = listView
							.getItemAtPosition(position).toString();

					String xUrl = xSelectedValue;
					if (!xUrl.startsWith("http://")
							&& !xUrl.startsWith("https://"))
						xUrl = "http://" + xUrl;
					Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri
							.parse(xUrl));
					startActivity(browserIntent);
				} catch (Exception e) {
					String xError = e.toString();
				}
				// When clicked, show a toast with the TextView text
				Toast.makeText(getApplicationContext(),
						((TextView) view).getText(), Toast.LENGTH_SHORT).show();
			}
		});

	}
}