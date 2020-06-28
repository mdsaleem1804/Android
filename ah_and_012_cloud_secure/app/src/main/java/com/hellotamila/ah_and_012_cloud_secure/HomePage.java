package com.hellotamila.ah_and_012_cloud_secure;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
public class HomePage extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.homepage);
		SimpleGetterAndSetter obj;
	}
	public void new_user(View v) {
		Intent xIntent = new Intent(getApplicationContext(), Webform1.class);
		xIntent.putExtra("url", "http://hellotamila.com/spiro/ah_and_012_msuniv/reg.php");
		startActivity(xIntent);
	}
    public void existing_user(View v) {
		Intent xIntent = new Intent(getApplicationContext(), Webform1.class);
			xIntent.putExtra("url", "http://hellotamila.com/spiro/ah_and_012_msuniv/login.php");
			startActivity(xIntent);
    }

}