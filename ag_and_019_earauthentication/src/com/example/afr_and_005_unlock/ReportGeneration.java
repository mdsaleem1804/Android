package com.example.afr_and_005_unlock;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ReportGeneration extends Activity {
	Button xbtnLogin;
	EditText xedtUsername, xedtPassword;
	DataBaseConnection db;
	String xUsername, xPassword, xSelectedUser;
	String topleft = "";
	TextView xTopLeft, txt3, txt4, txt5;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.reportgeneration);
		DataBaseConnection db;
		xTopLeft = (TextView) findViewById(R.id.textView2);
		txt3 = (TextView) findViewById(R.id.textView3);
		txt4 = (TextView) findViewById(R.id.textView4);
		txt5 = (TextView) findViewById(R.id.textView5);

		db = new DataBaseConnection(this);
		Cursor cursor = db.fetchTopLeft();
		if (cursor != null) {
			if (cursor.moveToFirst()) {
				do {
					topleft = cursor
							.getString(cursor.getColumnIndex("topleft"));
					if (topleft.equals("red")) {
						xTopLeft.setText("As per the APP Analysis Wound Might be in the initial Stage");
						txt3.setText("Avoid Water on the Affected  area");
						txt4.setText("A puncture wound is caused by an object piercing the skin and creating a small hole. Some punctures are just on the surface. Others can be very deep, depending on the source and cause.");
						txt5.setText("Treatment may be necessary to prevent infection in some wounds");
					} else if (topleft.equals("black")) {
						xTopLeft.setText(" As per the APP Analysis Wound Might be in the initial Stage");
						txt3.setText("Finger infections can be caused by a variety of bacteria and viruses");
						txt4.setText("Infection may cause redness, swelling, pus, or watery discharge from a puncture wound that is not noticed or not treated properly");
						txt5.setText("Treatment may be necessary to prevent infection in some wounds");
					} else {

						xTopLeft.setText("" + "PPattern Matched");
						//txt3.setText("If the person knows or suspects part of the object remains in the wound, contact a doctor. The individual may need urgent care to detect and remove the object.");

					}
				} while (cursor.moveToNext());
			}
			cursor.close();
		}
		db.close();
	}
}
