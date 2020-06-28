package com.example.and_af_jobportal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
	public static final String DATABASE_NAME = "jobportal.db";
	public static final String CONTACTS_TABLE_NAME = "contacts";

	public DBHelper(Context context) {
		super(context, DATABASE_NAME, null, 1);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("CREATE TABLE `jobseeker_login` (`username`	TEXT,`password`	TEXT)");
		db.execSQL("CREATE TABLE `jobseeker_signup` (`fullname`	TEXT,`username`	TEXT,`password`	TEXT,`mobileno`	TEXT,`city`	TEXT)");
		;
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}

	public boolean CheckLogin(String xUserName, String xPassword)
			{
		try
		{
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor mCursor = db.rawQuery("Select * from jobseeker_signup  WHERE username="
				+ xUserName + "password=" + xPassword, null);
		if (mCursor != null) {
			if (mCursor.getCount() > 0) {
				return true;
			}
		} 
		}catch(Exception e)
		{
			String xError=e.toString();
		}
		
		return false;
	}

	public boolean insertJoseekerSignup(String xFullName, String xUserName,
			String xPassword, String xMobileNo, String xCity) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues contentValues = new ContentValues();
		contentValues.put("fullname", xFullName);
		contentValues.put("username", xUserName);
		contentValues.put("password", xPassword);
		contentValues.put("mobileno", xMobileNo);
		contentValues.put("city", xCity);
		db.insert("jobseeker_signup", null, contentValues);
		return true;
	}

}