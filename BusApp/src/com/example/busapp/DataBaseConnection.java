package com.example.busapp;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DataBaseConnection extends SQLiteOpenHelper {

	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_NAME = "busapp.db";
	

	public DataBaseConnection(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		try {
			db.execSQL("create table frommsu(fromarea text,toarea text,busnumber text,time text,delaytime text);");
			db.execSQL("create table tomsu(fromarea text,toarea text,busnumber text,time text,delaytime text);");
		} catch (Exception e) {
			Log.e("Error", e.toString());
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS frommsu");
		db.execSQL("DROP TABLE IF EXISTS tomsu");
		onCreate(db);
	}

	public boolean InsertBusFrom(String xBusTime,String xBusDelayTime) {

		SQLiteDatabase db = this.getWritableDatabase();
		String sql1 = "insert into frommsu(time,delaytime)  values('" + xBusTime + "','"
				+ xBusDelayTime + "');";
		db.execSQL(sql1);
		return true;
	}

	public boolean CheckUsers(String username, String Password) {

		boolean result = false;
		String query = "SELECT  * FROM registration where username='"
				+ username + "' and password='" + Password + "';";

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(query, null);
		if (cursor.getCount() > 0) {
			result = true;
		}
	                	return result;

	}

	public List<String> getAllLabels() {
		List<String> labels = new ArrayList<String>();

		// Select All Query
		String selectQuery = "SELECT  username FROM registration";

		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				labels.add(cursor.getString(0));
			} while (cursor.moveToNext());
		}

		// closing connection
		cursor.close();
		db.close();

		// returning lables
		return labels;
	}

	public List<String> getPatientRemarks(String username) {
		List<String> labels = new ArrayList<String>();

		// Select All Query
		String selectQuery = "SELECT  patientremarks FROM userimagedetails where username='"
				+ username + "'";

		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				labels.add(cursor.getString(0));
			} while (cursor.moveToNext());
		}

		// closing connection
		cursor.close();
		db.close();

		// returning lables
		return labels;
	}
	// public boolean CheckMobileNo(String mobileno)
	// {
	// Long xMobileno;
	// xMobileno=Long.valueOf(mobileno);
	// boolean result = false;
	// String query = "SELECT  * FROM registration where mobileno="+xMobileno +
	// " ;";
	//
	// SQLiteDatabase db = this.getWritableDatabase();
	// Cursor cursor = db.rawQuery(query, null);
	// if(cursor.getCount()>0)
	// {
	// result=true;
	// }
	// return result;
	//
	// }
}