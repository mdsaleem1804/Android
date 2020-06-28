package com.example.ag_and_015_handwaeving;

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

public class ListPasswordNames extends ListActivity {
	ListView listView;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// no more this
		// setContentView(R.layout.list_fruit);
		DataBaseConnection mCon;
		mCon = new DataBaseConnection(this);
		List<String> Reminders = mCon.fn_GetPasswords();
		setListAdapter(new ArrayAdapter<String>(this, R.layout.listdata,
				Reminders));
		listView = getListView();
		listView.setTextFilterEnabled(true);
		listView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// When clicked, show a toast with the TextView text
				Toast.makeText(getApplicationContext(),
						((TextView) view).getText(), Toast.LENGTH_SHORT).show();
				Intent xIntent = new Intent(getApplicationContext(),
						NameEdit.class);
				xIntent.putExtra("passwordname", listView.getItemAtPosition(position)
						.toString());

				startActivity(xIntent);
			}
		});
	}
}