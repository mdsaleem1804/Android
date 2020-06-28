package com.nellaibill.petcollege;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class ListYear extends Activity {
    // Array of strings...
    String[] xDeptArray = {"FirstSemester","SecondSemester","ThirdSemester","FourthSemester","FifthSemester","SixthSemester","SeventhSemester","EighthSemester"};
    String xDepartment;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_dept);
        Bundle b = new Bundle();
        b = getIntent().getExtras();
        xDepartment = b.getString("passdepartment");
        Toast.makeText(getApplicationContext(),xDepartment, Toast.LENGTH_SHORT).show();
        ArrayAdapter adapter = new ArrayAdapter<String>(this,
                R.layout.activity_listview, xDeptArray);
        listView= (ListView) findViewById(R.id.mobile_list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                String selectedFromList =(listView.getItemAtPosition(position).toString());
                Intent myIntent = new Intent(view.getContext(), TimeTable.class);
                myIntent.putExtra("passdepartment", xDepartment);
                myIntent.putExtra("passyear", selectedFromList);
                startActivity(myIntent);
            }
        });
    }
}