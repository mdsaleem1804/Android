package com.hellotamila.ah_006_tourist;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;


public class UserMainPage extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.usermainpage);
	}
	public void listbus(View v) {
		Intent intent = new Intent(UserMainPage.this,
				ListBus.class);
		intent.putExtra("formname", "userpage");
		startActivity(intent);

	}
    public void listplaces(View v) {
        Intent intent = new Intent(UserMainPage.this,
                ListPlaces.class);
        intent.putExtra("formname", "userpage");
        startActivity(intent);

    }
	public void listhotel(View v) {
		Intent intent = new Intent(UserMainPage.this,
				ListHotel.class);
		intent.putExtra("formname", "userpage");
		startActivity(intent);

	}
	public void getbudget(View v) {
		Intent intent = new Intent(UserMainPage.this,
				GetBudget.class);
		intent.putExtra("formname", "adminpage");
		startActivity(intent);

	}
	public void goback(View v) {
		Intent intent = new Intent(UserMainPage.this,
				MainActivity.class);
		startActivity(intent);

	}
	public void loadmap(View v) {
		Intent intent = new Intent(UserMainPage.this,
				MapsActivity.class);
		startActivity(intent);

	}


}