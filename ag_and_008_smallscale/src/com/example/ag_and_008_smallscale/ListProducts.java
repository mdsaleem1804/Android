package com.example.ag_and_008_smallscale;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ListProducts extends Activity {
	ListView listView;
	String xSession, xGetUserName, xGetVenderName;
	Intent xIntent;
	// Listview Adapter
	// ArrayAdapter<String> adapter;

	// Search EditText
	EditText inputSearch;

	ArrayList<Contact> imageArry = new ArrayList<Contact>();
	ContactImageAdapter adapter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.listproducts);
		Bundle b = new Bundle();
		b = getIntent().getExtras();
		xGetUserName = b.getString("username");
		xGetVenderName = b.getString("vendername");

		DataBaseConnection mCon;
		mCon = new DataBaseConnection(this);
		List<String> xListData = mCon.fn_GetProducts(xGetVenderName);
		inputSearch = (EditText) findViewById(R.id.inputSearch);
		listView = (ListView) findViewById(R.id.list_view);
		inputSearch = (EditText) findViewById(R.id.inputSearch);

		/*
		 * // Adding items to listview adapter = new ArrayAdapter<String>(this,
		 * R.layout.listproducts_item, R.id.product_name, xListData);
		 * listView.setAdapter(adapter);
		 */

		// Reading all contacts from database
		List<Contact> contacts = mCon.getAllProducts(xGetVenderName);
		for (Contact cn : contacts) {

			// add contacts data in arrayList
			imageArry.add(cn);

		}
		adapter = new ContactImageAdapter(this, R.layout.listproducts_item,
				imageArry);
		ListView dataList = (ListView) findViewById(R.id.list_view);
		dataList.setAdapter(adapter);

		listView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				// ListView Clicked item value
				try {

					String xSelectedValue = listView
							.getItemAtPosition(position).toString();
					Contact clickedObj = (Contact) parent
							.getItemAtPosition(position);
					xIntent = new Intent(getApplicationContext(),
							EditProduct.class);
					xIntent.putExtra("productname", clickedObj.getName());
					xIntent.putExtra("username", xGetUserName);
					xIntent.putExtra("vendername", xGetVenderName);
					startActivity(xIntent);
				} catch (Exception e) {
					String xError = e.toString();
				}
			}
		});
		/*
		 * inputSearch.addTextChangedListener(new TextWatcher() {
		 * 
		 * @Override public void onTextChanged(CharSequence cs, int arg1, int
		 * arg2, int arg3) { // When user changed the Text
		 * ListProducts.this.adapter.getFilter().filter(cs); }
		 * 
		 * @Override public void beforeTextChanged(CharSequence arg0, int arg1,
		 * int arg2, int arg3) { // TODO Auto-generated method stub
		 * 
		 * }
		 * 
		 * @Override public void afterTextChanged(Editable arg0) { // TODO
		 * Auto-generated method stub } });
		 */
	}
}