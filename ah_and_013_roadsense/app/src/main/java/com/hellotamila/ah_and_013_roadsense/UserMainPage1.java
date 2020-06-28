package com.hellotamila.ah_and_013_roadsense;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

public class UserMainPage1 extends Activity {

	String xGetUserMobileNo,xGetUserPlace;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
	    try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.user_mainpage_1);
        }
        catch(Exception e)
        {
            String xError=e.toString();
        }
		Bundle b = new Bundle();
		b = getIntent().getExtras();
		xGetUserMobileNo = b.getString("usermobileno");
		xGetUserPlace= b.getString("userplace");
	}

	public void  list_drivers1(View v) {
		Intent intent = new Intent(UserMainPage1.this, ListView_User.class);
		intent.putExtra("usermobileno", xGetUserMobileNo);
		intent.putExtra("userplace", xGetUserPlace);
		startActivity(intent);
	}
	public void  list_areas(View v) {
		Intent intent = new Intent(UserMainPage1.this, MapsActivity.class);
		startActivity(intent);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
