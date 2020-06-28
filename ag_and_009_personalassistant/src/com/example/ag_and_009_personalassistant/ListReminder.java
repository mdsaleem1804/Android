package com.example.ag_and_009_personalassistant;

import java.util.List;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ag_and_009_dailystudentactivities.R;

public class ListReminder extends Activity {
	String[] menu;
	DrawerLayout dLayout;
	ListView dList;
	ArrayAdapter<String> adapter, insideadapter;
	ListView insidelist;
	String xSelectedExpenses;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.slider);

		try {
			xSelectedExpenses = getIntent().getExtras().getString(
					"expensesname");
		} catch (NullPointerException e) {
			xSelectedExpenses = "";
		}
		LoadList();
		Drawer();
	}

	public void LoadList() {
		DataBaseConnection mCon;
		mCon = new DataBaseConnection(this);
		List<String> Reminders = mCon.fn_GetReminders();
		insideadapter = new ArrayAdapter<String>(this, R.layout.listdata,
				Reminders);
		// listView = getListView();

		insidelist = (ListView) findViewById(R.id.listView1);
		insidelist.setAdapter(insideadapter);
		insidelist.setTextFilterEnabled(true);
		insidelist.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// When clicked, show a toast with the TextView text
				Toast.makeText(getApplicationContext(),
						((TextView) view).getText(), Toast.LENGTH_SHORT).show();

				String xSelectedValue = insidelist.getItemAtPosition(position)
						.toString();

				Intent xIntent = new Intent(getApplicationContext(),
						EditReminder.class);

				xIntent.putExtra("remindername", xSelectedValue);

				startActivity(xIntent);
			}
		});

	}

	public void Drawer() {
		menu = new String[] { "Meetings", "Food", "Classes", "Seminar",
				"Functions", "Sports", "More" };
		dLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		dList = (ListView) findViewById(R.id.left_drawer);

		adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, menu);

		dList.setAdapter(adapter);
		dList.setSelector(android.R.color.holo_blue_dark);

		dList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View v, int position,
					long id) {

				dLayout.closeDrawers();
				if (position == 6) {
					Intent intent = new Intent(ListReminder.this,
							AddReminder.class);
					intent.putExtra("expensesname", menu[position]);
					startActivity(intent);
				} else {
					Intent intent = new Intent(ListReminder.this,
							ListReminder.class);
					startActivity(intent);
					String xSelectedValue = menu[position];
					Toast.makeText(getApplicationContext(), xSelectedValue,
							1000).show();
					/*
					 * Bundle args = new Bundle(); args.putString("Menu",
					 * menu[position]); Fragment detail = new DetailFragment();
					 * detail.setArguments(args); FragmentManager
					 * fragmentManager = getFragmentManager();
					 * fragmentManager.beginTransaction
					 * ().replace(R.id.content_frame, detail).commit();
					 */
				}

			}

		});
	}
}