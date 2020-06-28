package com.hellotamila.ah_and_009_hospial;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;
import android.app.Activity;

public class ListProblems extends Activity {
    ListView list;
    DataBaseConnection mCon;
    private SQLiteDatabase db;
    private Cursor xCursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_problems);
        mCon = new DataBaseConnection(this);
        openDatabase();

        try {
            String xQry = "select * from user_problem_to_doctor";
            xCursor = db.rawQuery(xQry, null);
            String[] xDoctorName = new String[xCursor.getCount()];
            String[] xUserName = new String[xCursor.getCount()];
            String[] xProblem = new String[xCursor.getCount()];
            int i = 0;
            while (xCursor.moveToNext()) {
                if (i == xCursor.getCount()) {
                    return;
                } else {
                    String doctorname = xCursor.getString(xCursor.getColumnIndex("doctorname"));
                    String username = xCursor.getString(xCursor.getColumnIndex("username"));
                    String problem = xCursor.getString(xCursor.getColumnIndex("problem"));
                    xDoctorName[i] = doctorname;
                    xUserName[i] = username;
                    xProblem[i] = problem;
                    i++;
                }
            }
            CustomList adapter = new
                    CustomList(ListProblems.this, xDoctorName, xUserName,xProblem);
            list = (ListView) findViewById(R.id.list);
            list.setAdapter(adapter);
            list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view,
                                        int position, long id) {
                    //Toast.makeText(ListProblems.this, "You Clicked at " + xDoctorName[+position], Toast.LENGTH_SHORT).show();

                }
            });


        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
        }
    }
    protected void openDatabase() {
        db = openOrCreateDatabase("ah_and_009_hospial.db", Context.MODE_PRIVATE, null);
    }

}