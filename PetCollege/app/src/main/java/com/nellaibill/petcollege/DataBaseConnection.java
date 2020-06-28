package com.nellaibill.petcollege;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseConnection extends SQLiteOpenHelper {

	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_NAME = "petcollege.db";
	String xStaff;

	public DataBaseConnection(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		try {

            xStaff = "create table staff_details " + "(name varchar(50),"
					+ "mobileno varchar(50),"  + "username varchar(50),"
					+ "password varchar(50),"  + "stafflat varchar(50),"
					+ "stafflong varchar(50))";
			db.execSQL(xStaff);



		} catch (Exception e) {

		}
	}
	


	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + xStaff);
		onCreate(db);
	}

	public void insertStaff(String a, String b, String c, String d) {
		try {
			ContentValues contentValues = new ContentValues();
			contentValues.put("name", a);
			contentValues.put("mobileno", b);
			contentValues.put("username", c);
            contentValues.put("password", d);
			SQLiteDatabase db = this.getWritableDatabase();
			db.insert("staff_details", null, contentValues);
		} catch (Exception e) {
			e.toString();
		}
	}


}

