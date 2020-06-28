package com.example.ag_and_005_reminder;

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

public class ListExpensesByPaymentMode extends ListActivity {



	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		DataBaseConnection mCon;
		mCon = new DataBaseConnection(this);
		List<String> xExpenses = mCon.fn_CheckExpenses("order by paymentmode");

		setListAdapter(new ArrayAdapter<String>(this, R.layout.listdata,
				xExpenses));

		ListView listView = getListView();
		listView.setTextFilterEnabled(true);

		listView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// When clicked, show a toast with the TextView text
				String xSelectedItem=((TextView) view).getText().toString();
				String[] namesList = xSelectedItem.split("-");
				String name1 = namesList[0];
				Toast.makeText(getApplicationContext(),
						name1, Toast.LENGTH_SHORT).show();
				Intent xIntent = new Intent(ListExpensesByPaymentMode.this, EditExpenses.class);
				xIntent.putExtra("id", name1);
				startActivity(xIntent);
			}
		});

	}

}