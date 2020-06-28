package com.hellotamila.ah_006_tourist;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;


public class ListHotel extends ListActivity {
	ListView listView;
	String xSession;
	Intent xIntent;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		DataBaseConnection mCon;
		mCon = new DataBaseConnection(this);
		List<String> xListData = mCon.fn_GetHotel();
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
					startActivity(xIntent);
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