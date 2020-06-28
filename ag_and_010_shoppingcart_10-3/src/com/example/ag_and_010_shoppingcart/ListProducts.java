package com.example.ag_and_010_shoppingcart;

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

public class ListProducts extends Activity {
	ListView listView;
	String xSession, xGetUserName,xGetVendorName;
	Intent xIntent;
	// Listview Adapter
	//ArrayAdapter<String> adapter;

	ArrayList<Contact> imageArry = new ArrayList<Contact>();
	ContactImageAdapter adapter;


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.listproducts);
		Bundle b = new Bundle();
		b = getIntent().getExtras();
		xGetUserName = b.getString("username");
		xGetVendorName = b.getString("vendorname");
		
		DataBaseConnection mCon;
		mCon = new DataBaseConnection(this);
		//List<String> xListData = mCon.fn_GetProducts(xGetVendorName);

		listView = (ListView) findViewById(R.id.list_view);


	/*	// Adding items to listview
		adapter = new ArrayAdapter<String>(this, R.layout.listproducts_item,
				R.id.product_name, xListData);
		listView.setAdapter(adapter);
*/
		
		// Reading all contacts from database
		List<Contact> contacts = mCon.getAllProducts(xGetVendorName);
		for (Contact cn : contacts) {
			
			//add contacts data in arrayList
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
					Contact clickedObj = (Contact)parent.getItemAtPosition(position);
					xIntent = new Intent(getApplicationContext(),
							EditProduct.class);
					xIntent.putExtra("productname", clickedObj.getName());
					xIntent.putExtra("username", xGetUserName);
					xIntent.putExtra("vendorname", xGetVendorName);
					startActivity(xIntent);
				} catch (Exception e) {
					String xError = e.toString();
				}
			}
		});
		
		  EditText myFilter = (EditText) findViewById(R.id.inputSearch);
		  myFilter.addTextChangedListener(new TextWatcher() {
		 
		   public void afterTextChanged(Editable s) {
		   }
		 
		   public void beforeTextChanged(CharSequence s, int start, 
		     int count, int after) {
		   }
		 
		   public void onTextChanged(CharSequence s, int start, 
		     int before, int count) {
		    adapter.getFilter().filter(s.toString());
		   }
		  });
		   
		 
		/*inputSearch.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence cs, int arg1, int arg2,
					int arg3) {
				// When user changed the Text
				ListProducts.this.adapter.getFilter().filter(cs);
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
		});*/
	}
}