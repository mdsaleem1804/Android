package com.example.afr_and_005_unlock;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class LoginSuccess extends Activity {
	Button  xbtnCaptureImage;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.loginsuccess);
		xbtnCaptureImage = (Button) findViewById(R.id.btncaptureimage);

		try {

			xbtnCaptureImage.setOnClickListener(new OnClickListener() {

				public void onClick(View v) {
					try {
						Intent intent = new Intent(LoginSuccess.this,
								CaptureImage.class);
						startActivity(intent);

					} catch (Exception e) {

					}

				}
			});
		
		} catch (Exception e) {

		}
	}
}
