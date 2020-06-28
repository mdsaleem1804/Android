package com.example.ag_and_008_smallscale;

import java.util.List;

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

public class ListCart extends ListActivity {

	ListView listView;
	String xVendorName;
	Intent xIntent;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Bundle b = new Bundle();
		b = getIntent().getExtras();
		xVendorName = b.getString("vendorname");
		DataBaseConnection mCon;
		mCon = new DataBaseConnection(this);
		List<String> xListData = mCon.fn_GetCart(xVendorName);
		setListAdapter(new ArrayAdapter<String>(this, R.layout.listdata,
				xListData));
		listView = getListView();
		listView.setTextFilterEnabled(true);
	}
}