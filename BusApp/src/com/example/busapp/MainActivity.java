package com.example.busapp;


import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends Activity {
	ImageButton homeb1,homeb2;
	Intent homei1,homei2;
	  private DbConnection mdb=null;
	    private SQLiteDatabase db=null;
	    private static final String DATABASE_NAME = "bus data.db";
	    public static final int DATABASE_VERSION = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        homeb1=(ImageButton)findViewById(R.id.ha1);
	    homeb2=(ImageButton)findViewById(R.id.ha2);
	    mdb=new DbConnection(getApplicationContext(), DATABASE_NAME,null, DATABASE_VERSION);
        db=mdb.getWritableDatabase();
      
	  
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    public void haa(View v)
	{
		Toast.makeText(getApplicationContext(),"Welcome...",Toast.LENGTH_SHORT).show();
		homei1=new Intent(getApplication(),Adminlogin.class);
		startActivityForResult(homei1, 0);
	}
	public void hab(View v)
	{
		Toast.makeText(getApplicationContext(),"Welcome...",Toast.LENGTH_SHORT).show();
		homei2=new Intent(getApplication(),Userdecide.class);
		startActivityForResult(homei2, 0);
	}

    
}
