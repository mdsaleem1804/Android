package com.tcssnellai.ag_and_004_womenscheme;

import java.util.List;

import com.tcssnellai.ag_and_004_womenscheme.R;

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

public class ListSchemes extends ListActivity {

	ListView listView;
	String xSession;
	Intent xIntent;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Bundle b = new Bundle();
		b = getIntent().getExtras();
		xSession = b.getString("formname");

		DataBaseConnection mCon;
		mCon = new DataBaseConnection(this);
		List<String> xListData = mCon.fn_GetSchemes("");
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

					// String requiredString = s.substring(s.indexOf("=") + 1,
					// s.indexOf("}"));
					// TODO Auto-generated method stub
					if (xSession.equalsIgnoreCase("HomePage"))

					{
						xIntent = new Intent(getApplicationContext(),
								GeneralSchemes.class);
					}
					if (xSession.equalsIgnoreCase("AdminMainPage"))

					{
						xIntent = new Intent(getApplicationContext(),
								AdminSchemeEdit.class);
					}
					xIntent.putExtra("name", xSelectedValue);

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