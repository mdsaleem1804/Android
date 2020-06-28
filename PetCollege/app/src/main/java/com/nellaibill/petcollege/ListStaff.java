package com.nellaibill.petcollege;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class ListStaff extends Activity {
    ListView list;
    DataBaseConnection mCon;
    private SQLiteDatabase db;
    private Cursor xCursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_staff);
        mCon = new DataBaseConnection(this);
        openDatabase();

        try {
            String xQry = "select * from staff_details";
            xCursor = db.rawQuery(xQry, null);
            final  String[] xDoctorName = new String[xCursor.getCount()];
            final  String[] xUserName = new String[xCursor.getCount()];
            final  String[] xProblem = new String[xCursor.getCount()];
            int i = 0;
            while (xCursor.moveToNext()) {
                if (i == xCursor.getCount()) {
                    return;
                } else {
                    String doctorname = xCursor.getString(xCursor.getColumnIndex("name"));
                    String username = xCursor.getString(xCursor.getColumnIndex("mobileno"));
                    String problem = xCursor.getString(xCursor.getColumnIndex("username"));
                    xDoctorName[i] = doctorname;
                    xUserName[i] = username;
                    xProblem[i] = problem;
                    i++;
                }
            }
            CustomList adapter = new
                    CustomList(ListStaff.this, xDoctorName, xUserName,xProblem);
            list = (ListView) findViewById(R.id.list);
            list.setAdapter(adapter);
            list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view,
                                        int position, long id) {
                  //  Toast.makeText(ListStaff.this, "You Clicked at " + xProblem[+position], Toast.LENGTH_SHORT).show();

                    Intent   xIntent = new Intent(getApplicationContext(), ViewStaffLocation.class);
                    xIntent.putExtra("selectedname", xProblem[+position]);
                    startActivity(xIntent);
                }
            });


        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
        }
    }
    protected void openDatabase() {
        db = openOrCreateDatabase("petcollege.db", Context.MODE_PRIVATE, null);
    }

}