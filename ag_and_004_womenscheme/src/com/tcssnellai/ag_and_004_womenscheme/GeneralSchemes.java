package com.tcssnellai.ag_and_004_womenscheme;

import com.tcssnellai.ag_and_004_womenscheme.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class GeneralSchemes extends Activity {
	String xSelectedScheme;
	EditText xEdtSchemeName, xEdtMinAge, xEdtMaxAge, xEdtQualification,
			xEdtAmount, xEdtDetail, xEdtWebsite;
	DataBaseConnection mCon;
	private SQLiteDatabase db;
	private Cursor xCursor;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.userschemeview);
		Bundle b = new Bundle();
		b = getIntent().getExtras();
		 openDatabase();
		xSelectedScheme = b.getString("name");
		mCon = new DataBaseConnection(this);
		xEdtSchemeName = (EditText) findViewById(R.id.fedtSchemeName);
		xEdtMinAge = (EditText) findViewById(R.id.fedtMinAge);
		xEdtMaxAge = (EditText) findViewById(R.id.fedtMaxAge);
		xEdtQualification = (EditText) findViewById(R.id.fedtQualification);
		xEdtAmount = (EditText) findViewById(R.id.fedtAmount);
		xEdtDetail = (EditText) findViewById(R.id.fedtDetails);
		xEdtWebsite = (EditText) findViewById(R.id.fedtWebsite);
		xEdtSchemeName.setEnabled(false);
		xEdtMinAge.setEnabled(false);

		xEdtSchemeName.setText(xSelectedScheme);
		String xQry = "Select * from schemes where schemename='"+xSelectedScheme +"'";
		xCursor = db.rawQuery(xQry, null);
		xCursor.moveToFirst();
		showRecords();

	}
	protected void openDatabase() {
        db = openOrCreateDatabase("ag_and_004_womenscheme1", Context.MODE_PRIVATE, null);
    }
	protected void showRecords() {
		xEdtMinAge.setText(xCursor.getString(2));
		xEdtMaxAge.setText(xCursor.getString(3));
		xEdtQualification.setText(xCursor.getString(4));
		xEdtAmount.setText(xCursor.getString(7));
		xEdtDetail.setText(xCursor.getString(8));
		xEdtWebsite.setText(xCursor.getString(9));
		xEdtQualification.setText(xCursor.getString(4));


	}

	
	public void weblink(View v) {
		String xUrl=xEdtWebsite.getText().toString();
		if (!xUrl.startsWith("http://") && !xUrl.startsWith("https://"))
			xUrl = "http://" + xUrl;
		Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(xUrl));
		startActivity(browserIntent);
	}

}
