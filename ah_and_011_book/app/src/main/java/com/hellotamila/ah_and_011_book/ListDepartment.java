package com.hellotamila.ah_and_011_book;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ListDepartment extends Activity {
    // Array of strings...
    String[] xDeptArray = {"CSE","EEE","ECE","CIVIL","MECH","MCA","MBA"};
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_dept);

        ArrayAdapter adapter = new ArrayAdapter<String>(this,
                R.layout.activity_listview, xDeptArray);

         listView = (ListView) findViewById(R.id.mobile_list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                String selectedFromList =(listView.getItemAtPosition(position).toString());
                    Intent myIntent = new Intent(view.getContext(), ListYear.class);
                myIntent.putExtra("passdepartment", selectedFromList);
                startActivity(myIntent);
            }
        });
    }
}