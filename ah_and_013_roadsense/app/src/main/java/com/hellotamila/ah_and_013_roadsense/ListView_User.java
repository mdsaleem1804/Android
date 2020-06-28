package com.hellotamila.ah_and_013_roadsense;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.FilterQueryProvider;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class ListView_User extends Activity {

	private DbAdapter dbHelper;
	private SimpleCursorAdapter dataAdapter;
	String xGetUserMobileNo,xGetUserPlace;
	String xDriverName;
	DataBaseConnection mCon;
	private SQLiteDatabase db;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.listdrivers);
		openDatabase();
		Bundle b = new Bundle();
		b = getIntent().getExtras();
		xGetUserMobileNo = b.getString("usermobileno");
		xGetUserPlace= b.getString("userplace");
		dbHelper = new DbAdapter(this);
		dbHelper.open();
		displayListView();

	}

	protected void openDatabase() {
		db = openOrCreateDatabase("ag_and_001_trafficandtaxi",
				Context.MODE_PRIVATE, null);
	}

	private void displayListView() {

		Cursor cursor = dbHelper.fetchAllCountries();
		// Cursor cursor dbHelper.rawQuery("SELECT * FROM ");
		// String xQry = "Select * from driverregistration";
		// Cursor cursor = db.rawQuery(xQry, null);
		// The desired columns to be bound
		String[] columns = new String[] { DbAdapter.KEY_USERNAME,
				DbAdapter.KEY_ADDRESS, DbAdapter.KEY_DESTINATION,
		// CountriesDbAdapter.KEY_REGION
		};

		//String[] columns = new String[] { "source","destination","username",};
		// the XML defined views which the data will be bound to
		int[] to = new int[] { R.id.fDriverName, R.id.fRoute,
				R.id.fDriverDetails,
		// R.id.region,
		};

		// create the adapter using the cursor pointing to the desired data
		// as well as the layout information
		try {
			dataAdapter = new SimpleCursorAdapter(this, R.layout.driverinfo,
					cursor, columns, to, 0);
		} catch (Exception e) {
			String xError = e.toString();
		}
		ListView listView = (ListView) findViewById(R.id.listView1);
		// Assign adapter to ListView
		listView.setAdapter(dataAdapter);

		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> listView, View view,
					int position, long id) {
				// Get the cursor, positioned to the corresponding row in the
				// result set
				Cursor cursor = (Cursor) listView.getItemAtPosition(position);

				// Get the state's capital from this row in the database.
				xDriverName = cursor.getString(cursor
						.getColumnIndexOrThrow("username"));
				Toast.makeText(getApplicationContext(), xDriverName,
						Toast.LENGTH_SHORT).show();
				Intent intent = new Intent(ListView_User.this,
						UserSelectDriver.class);
				intent.putExtra("usermobileno", xGetUserMobileNo);
				intent.putExtra("userplace", xGetUserPlace);
				intent.putExtra("drivername", xDriverName);

				startActivity(intent);

			}
		});

		EditText myFilter = (EditText) findViewById(R.id.myFilter);
		myFilter.addTextChangedListener(new TextWatcher() {

			public void afterTextChanged(Editable s) {
			}

			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}

			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				dataAdapter.getFilter().filter(s.toString());
			}
		});

		dataAdapter.setFilterQueryProvider(new FilterQueryProvider() {
			public Cursor runQuery(CharSequence constraint) {
				return dbHelper.fetchCountriesByName(constraint.toString());
			}
		});

	}
}