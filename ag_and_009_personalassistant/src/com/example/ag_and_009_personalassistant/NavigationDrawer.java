package com.example.ag_and_009_personalassistant;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.ag_and_009_dailystudentactivities.R;

public class NavigationDrawer extends Activity {

	// Within which the entire activity is enclosed
	DrawerLayout mDrawerLayout; // ListView represents Navigation Drawer
	ListView mDrawerList;
	// ActionBarDrawerToggle indicates the presence of Navigation Drawer in the
	// action bar
	ActionBarDrawerToggle mDrawerToggle;

	// Title of the action bar
	String mTitle = "";
	// Array of strings...
	String[] mobileArray = { "Meeting At Hod Room" };

	ArrayAdapter adapter1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.navigationdrawer);

		try {

			adapter1 = new ArrayAdapter<String>(this,
					R.layout.activity_listview, mobileArray);

			ListView listView = (ListView) findViewById(R.id.mobile_list);
			listView.setAdapter(adapter1);
			listView.setTextFilterEnabled(true);
			listView.setOnItemClickListener(new OnItemClickListener() {
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					Toast.makeText(getApplicationContext(),
							"Editing ", Toast.LENGTH_SHORT).show();
					Intent xIntent = new Intent(NavigationDrawer.this, EditReminder.class);
				//	xIntent.putExtra("id", name1);
					startActivity(xIntent);
				}
			});
			mTitle = (String) getTitle();

			// Getting reference to the DrawerLayout
			mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

			mDrawerList = (ListView) findViewById(R.id.drawer_list);

			// Getting reference to the ActionBarDrawerToggle
			mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
					R.drawable.ic_drawer, R.string.drawer_open,
					R.string.drawer_close) {

				/** Called when drawer is closed */
				public void onDrawerClosed(View view) {
					getActionBar().setTitle(mTitle);
					invalidateOptionsMenu();

				}

				/** Called when a drawer is opened */
				public void onDrawerOpened(View drawerView) {
					getActionBar().setTitle("Select a Menu");
					invalidateOptionsMenu();
				}

			};

			// Setting DrawerToggle on DrawerLayout
			mDrawerLayout.setDrawerListener(mDrawerToggle);

			// Creating an ArrayAdapter to add items to the listview mDrawerList
			ArrayAdapter<String> adapter = new ArrayAdapter<String>(
					getBaseContext(), R.layout.drawer_list_item, getResources()
							.getStringArray(R.array.leftmenu));

			// Setting the adapter on mDrawerList
			mDrawerList.setAdapter(adapter);

			// Enabling Home button
			getActionBar().setHomeButtonEnabled(true);

			// Enabling Up navigation
			getActionBar().setDisplayHomeAsUpEnabled(true);

			// Setting item click listener for the listview mDrawerList
			mDrawerList.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					if (position == 6) {
						mDrawerLayout.closeDrawer(mDrawerList);
						Intent intent = new Intent(NavigationDrawer.this,
								AddReminder.class);
						startActivity(intent);

					}

					// Getting an array of rivers
					String[] rivers = getResources().getStringArray(
							R.array.leftmenu);

					// Currently selected river
					mTitle = rivers[position];

					/*
					 * // Creating a fragment object RiverFragment rFragment =
					 * new RiverFragment();
					 * 
					 * // Creating a Bundle object Bundle data = new Bundle();
					 * 
					 * // Setting the index of the currently selected item of
					 * mDrawerList data.putInt("position", position);
					 * 
					 * // Setting the position to the fragment
					 * rFragment.setArguments(data);
					 * 
					 * // Getting reference to the FragmentManager
					 * FragmentManager fragmentManager = getFragmentManager();
					 * 
					 * // Creating a fragment transaction FragmentTransaction ft
					 * = fragmentManager.beginTransaction();
					 * 
					 * // Adding a fragment to the fragment transaction
					 * ft.replace(R.id.content_frame, rFragment);
					 * 
					 * // Committing the transaction ft.commit();
					 */

					// Closing the drawer
					mDrawerLayout.closeDrawer(mDrawerList);

				}
			});
		} catch (Exception e) {
			String xError = e.toString();

		}
	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		mDrawerToggle.syncState();
	}

	/** Handling the touch event of app icon */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (mDrawerToggle.onOptionsItemSelected(item)) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/** Called whenever we call invalidateOptionsMenu() */
	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		// If the drawer is open, hide action items related to the content view
		boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);

		menu.findItem(R.id.action_settings).setVisible(!drawerOpen);
		return super.onPrepareOptionsMenu(menu);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}
