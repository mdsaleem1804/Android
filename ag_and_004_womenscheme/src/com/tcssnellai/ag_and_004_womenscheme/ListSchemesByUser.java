package com.tcssnellai.ag_and_004_womenscheme;

import java.util.List;

import com.tcssnellai.ag_and_004_womenscheme.R;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ListSchemesByUser extends ListActivity {

	ListView listView;
	String xSession,xUserName,xEducation,xCaste,xSection;
	Intent xIntent;
	DataBaseConnection mCon;
	private SQLiteDatabase db;
	private Cursor xCursor;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Bundle b = new Bundle();
		b = getIntent().getExtras();
		xSession = b.getString("formname");
		xUserName = b.getString("username");
		DataBaseConnection mCon;
		mCon = new DataBaseConnection(this);
		openDatabase();
		String xQry = "Select * from womenregistration where username='"+xUserName +"'";
		xCursor = db.rawQuery(xQry, null);
		xCursor.moveToFirst();
		showRecords();
		
		List<String> xListData = mCon.fn_GetSchemes("where education='"+xEducation+"' and caste='"+xCaste+"' and section='"+xSection+"'");
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

protected void openDatabase() {
    db = openOrCreateDatabase("ag_and_004_womenscheme1", Context.MODE_PRIVATE, null);
}

protected void showRecords() {
	xEducation=xCursor.getString(2);
	xSection=xCursor.getString(3);
	xCaste=xCursor.getString(4);
}
}