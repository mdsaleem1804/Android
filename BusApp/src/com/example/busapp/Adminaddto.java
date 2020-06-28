package com.example.busapp;


import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class Adminaddto extends Activity {
	ImageButton addbt1,addbt2;
	Intent addit1,addit2;
    private DbConnection mdb=null;
    private SQLiteDatabase db=null;
    private Cursor c=null;
    private static final String DATABASE_NAME = "ImageDb.db";
    public static final int DATABASE_VERSION = 1;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_adminaddto);
		addbt1=(ImageButton)findViewById(R.id.addtoa);
	    addbt2=(ImageButton)findViewById(R.id.addtoh);
	    mdb=new DbConnection(getApplicationContext(), DATABASE_NAME,null, DATABASE_VERSION);
        db=mdb.getWritableDatabase();
	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.adminaddto, menu);
		return true;
	}
	public void addtoad(View v)
	{
		ContentValues cv=new ContentValues();
        cv.put("fromarea", "Nellai");
        cv.put("toarea", "");
        cv.put("busnumber", "");
        cv.put("time", "");
        cv.put("time", "");
        db.insert("delaytime", null, cv);
        
        Toast.makeText(this, "inserted successfully", Toast.LENGTH_SHORT).show();
		Toast.makeText(getApplicationContext(),"Bus Details are successfully added...",Toast.LENGTH_LONG).show();
		addit1=new Intent(getApplication(),MainActivity.class);
		startActivityForResult(addit1, 0);
	}
	public void addtoho(View v)
	{
		Toast.makeText(getApplicationContext(),"Thank you...",Toast.LENGTH_SHORT).show();
		addit2=new Intent(getApplication(),MainActivity.class);
		startActivityForResult(addit2, 0);
	}


}
