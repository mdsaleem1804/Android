package com.example.ag_and_008_smallscale;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

public class ListVendors extends Activity {
	ListView listView;
	String xSession, xGetUserName;
	Intent xIntent;
	// Listview Adapter
	ArrayAdapter<String> adapter;

	// Search EditText
	EditText inputSearch;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.listvendors);
		Bundle b = new Bundle();
		b = getIntent().getExtras();
		xGetUserName = b.getString("username");

		DataBaseConnection mCon;
		mCon = new DataBaseConnection(this);
		List<String> xListData = mCon.fn_GetVendors();
		/*
		 * setListAdapter(new ArrayAdapter<String>(this, R.layout.listvendors,
		 * xListData)); listView = getListView();
		 * listView.setTextFilterEnabled(true);
		 */
		inputSearch = (EditText) findViewById(R.id.inputSearch);
		listView = (ListView) findViewById(R.id.list_view);
		inputSearch = (EditText) findViewById(R.id.inputSearch);

		// Adding items to listview
		adapter = new ArrayAdapter<String>(this, R.layout.listvendors_item,
				R.id.product_name, xListData);
		listView.setAdapter(adapter);

		listView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				// ListView Clicked item value
				try {

					String xSelectedValue = listView
							.getItemAtPosition(position).toString();

					xIntent = new Intent(getApplicationContext(),
							ListProducts.class);

					xIntent.putExtra("username", xGetUserName);
					xIntent.putExtra("vendername", xSelectedValue);

					startActivity(xIntent);
				} catch (Exception e) {
					String xError = e.toString();
				}
			}
		});
		inputSearch.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence cs, int arg1, int arg2,
					int arg3) {
				// When user changed the Text
				ListVendors.this.adapter.getFilter().filter(cs);
			}

			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1,
					int arg2, int arg3) {
				// TODO Auto-generated method stub

			}

			@Override
			public void afterTextChanged(Editable arg0) {
				// TODO Auto-generated method stub
			}
		});
	}
}