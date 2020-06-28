package com.example.ag_and_001_traffic_taxi;

import java.util.List;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ListUserRequests extends ListActivity {

	String xGetDriverName;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		DataBaseConnection mCon;
		mCon = new DataBaseConnection(this);
		Bundle b = new Bundle();
		b = getIntent().getExtras();
		xGetDriverName = b.getString("drivername");
		List<String> xExpenses = mCon.fn_GetUserRequests(xGetDriverName);

		setListAdapter(new ArrayAdapter<String>(this, R.layout.listdata,
				xExpenses));

		ListView listView = getListView();
		listView.setTextFilterEnabled(true);

		listView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// When clicked, show a toast with the TextView text
				Toast.makeText(getApplicationContext(),
						((TextView) view).getText(), Toast.LENGTH_SHORT).show();
			}
		});

	}

}