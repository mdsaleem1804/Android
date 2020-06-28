package com.example.and_af_donor;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	Spinner spinner;
	ListView lv;

	// Button flotingbtn;

	DbHelper dbHelper;
	int increaser = 0;

	// Arraylist of BLOOD Groups
	ArrayList<String> bloodGroups = new ArrayList<String>();
	ArrayList<DonorInformation> datas;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		lv = (ListView) findViewById(R.id.listView);
		spinner = (Spinner) findViewById(R.id.spinner);
		// flotingbtn= (Button) findViewById(R.id.flotingbutton);

		bloodGroups.add("Please select a Blood group");
		bloodGroups.add("All");
		bloodGroups.add("A+");
		bloodGroups.add("A-");
		bloodGroups.add("B+");
		bloodGroups.add("B-");
		bloodGroups.add("AB+");
		bloodGroups.add("AB-");
		bloodGroups.add("O+");
		bloodGroups.add("O-");

		dbHelper = new DbHelper(MainActivity.this);
		populateList("All");

		spinner.setAdapter(new ArrayAdapter<String>(MainActivity.this,
				android.R.layout.simple_spinner_dropdown_item, bloodGroups));
		spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> adapterView, View view,
					int i, long l) {
				String selectedItem = spinner.getSelectedItem().toString()
						.trim();
				// Toast.makeText(MainActivity.this,"Length is : "+selectedItem.length(),Toast.LENGTH_LONG).show();
				if (increaser >= 2 && selectedItem.length() > 5) {
					// populateList("All");
					Toast.makeText(MainActivity.this,
							"Please select a BloodGroup", Toast.LENGTH_LONG)
							.show();
				} else if (selectedItem.length() <= 3
						&& selectedItem.length() >= 2) {
					populateList(selectedItem);
					// Toast.makeText(MainActivity.this,"Aikhane ashar to kotha na",Toast.LENGTH_LONG).show();
				}

			}

			@Override
			public void onNothingSelected(AdapterView<?> adapterView) {

			}
		});

		lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adapterView, View view,
					int i, long l) {
				Intent intent = new Intent(MainActivity.this,
						DonorDescription.class);
				intent.putExtra("name", datas.get(i).name);
				intent.putExtra("phone", datas.get(i).phone);
				intent.putExtra("email", datas.get(i).email);
				intent.putExtra("bloodGroup", datas.get(i).bloodGroup);
				intent.putExtra("lastDate", datas.get(i).lastDate);
				startActivity(intent);

			}
		});

		lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
			@Override
			public boolean onItemLongClick(AdapterView<?> adapterView,
					View view, int i, long l) {
				Intent intent = new Intent(MainActivity.this,
						DialogOptions.class);
				intent.putExtra("id", datas.get(i).id);
				intent.putExtra("name", datas.get(i).name);
				intent.putExtra("phone", datas.get(i).phone);
				intent.putExtra("email", datas.get(i).email);
				intent.putExtra("bloodGroup", datas.get(i).bloodGroup);
				intent.putExtra("lastDate", datas.get(i).lastDate);
				startActivity(intent);
				return true;
			}
		});

	}

	public void viewlist(View v) {
		Intent intent = new Intent(MainActivity.this, ViewList.class);
		startActivityForResult(intent, 100);
	}

	public void populateList(String selectedGroup) {
		datas = dbHelper.getDatas(selectedGroup);
		lv.setAdapter(new Myadapter(datas));

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == 100 && resultCode == RESULT_OK) {
			Toast.makeText(this, "Data inserted successfully",
					Toast.LENGTH_LONG).show();

		}
		spinner.setSelection(1);
		populateList("All");
	}

	/*
	 * public void addcontacts(View v) { Intent intent = new
	 * Intent(MainActivity.this, AddContacts.class); intent.putExtra("task",
	 * "add"); startActivityForResult(intent, 100); } public void addDonor(View
	 * view) { Intent intent = new Intent(MainActivity.this,AddContacts.class);
	 * intent.putExtra("task","add"); startActivityForResult(intent,100);
	 * 
	 * }
	 */

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		// inflater.inflate(R.menu.menu, menu);
		return true;

	}

	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle item selection
		switch (item.getItemId()) {
		case R.id.logout:
			LoginClass login = new LoginClass(MainActivity.this);
			login.removing();
			startActivity(new Intent(MainActivity.this, Login.class));
			finish();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	// Base Adapter Class

	private class Myadapter extends BaseAdapter {

		ArrayList<DonorInformation> donorList;

		public Myadapter(ArrayList<DonorInformation> donorlist) {
			this.donorList = donorlist;
		}

		class ViewHolder {

			TextView tvName;
			Button donateButtony;
			/*
			 * TextView tvPhone; TextView tvEmail;
			 */
			TextView tvBloodgroup;

		}

		@Override
		public int getCount() {
			return donorList.size();
		}

		@Override
		public Object getItem(int i) {
			return null;
		}

		@Override
		public long getItemId(int i) {
			return 0;
		}

		@Override
		public View getView(final int i, View view, ViewGroup viewGroup) {
			ViewHolder viewHolder;
			if (view == null) {
				viewHolder = new ViewHolder();
				view = getLayoutInflater().inflate(R.layout.item_list_row_2,
						null, false);
				viewHolder.tvName = (TextView) view
						.findViewById(R.id.NametextView);
				viewHolder.tvBloodgroup = (TextView) view
						.findViewById(R.id.BloodgrouptextView);
				// viewHolder.donateButtony= (Button)
				// view.findViewById(R.id.donateButton);

				view.setTag(viewHolder);
			} else {
				viewHolder = (ViewHolder) view.getTag();
			}

			viewHolder.tvName.setText(donorList.get(i).name);
			viewHolder.tvBloodgroup.setText(donorList.get(i).bloodGroup);

			/*
			 * viewHolder.donateButtony.setOnClickListener(new
			 * View.OnClickListener() {
			 * 
			 * @Override public void onClick(View view) { DonorInformation
			 * donorInfo = new
			 * DonorInformation(donorList.get(i).id,donorList.get(i).
			 * name,donorList.get(i).phone,donorList.get(i).email,
			 * donorList.get(i).bloodGroup,donorList.get(i).lastDate); DbHelper
			 * dbHelper = new DbHelper(MainActivity.this);
			 * //Toast.makeText(this,"ID : "+
			 * acheivedId,Toast.LENGTH_LONG).show(); int re =
			 * dbHelper.update(MainActivity.this, donorInfo); } });
			 */

			return view;
		}
	}

}
