package com.example.ag_and_013;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseConnection extends SQLiteOpenHelper {

	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_NAME = "ag_and_013_bluetooth.db";
	String xAlertTable;

	public DataBaseConnection(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		try {

			xAlertTable = "create table alertrecieverdetails "
					+ "(mobileno varchar(50),id varchar(10))";
			db.execSQL(xAlertTable);
			db.execSQL("insert into alertrecieverdetails values('9578795653','1')");

		} catch (Exception e) {

		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + xAlertTable);
		onCreate(db);
	}

	public Boolean insertAlertRecieverDetails(String xMobileNo) {

		try {

			ContentValues contentValues = new ContentValues();
			contentValues.put("mobileno", xMobileNo);
			SQLiteDatabase db = this.getWritableDatabase();
			//db.insert("alertrecieverdetails", null, contentValues);
			String xId="1";
			db.update("alertrecieverdetails", contentValues, "id='"
					+ xId + "'", null);

		} catch (Exception e) {
			e.toString();
		}
		return true;
	}

}