package com.hellotamila.middaymealtamilnadu;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DataBaseConnection extends SQLiteOpenHelper {

	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_NAME = "mdm.db";
	String xHotelTable;

	public DataBaseConnection(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		try {

			xHotelTable = "create table hotel " + "(hotelname varchar(50))";

			db.execSQL(xHotelTable);


		} catch (Exception e) {

		}
	}
	
	public void fn_DeleteBusDetails(String xBusName) {

		SQLiteDatabase xDataBase = this.getWritableDatabase();
		xDataBase.execSQL("DELETE FROM bus WHERE busname='"
				+ xBusName + "'");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + xHotelTable);
		onCreate(db);
	}
	public boolean CheckIsDataAlreadyInDBorNot() {
		SQLiteDatabase db = this.getWritableDatabase();
		String Query = "Select * from hotel";
		Cursor cursor = db.rawQuery(Query, null);
		if(cursor.getCount() <= 0){
			cursor.close();
			return false;
		}
		cursor.close();
		return true;
	}
	public String fn_GetSchoolName() {
		String xBus="";
		SQLiteDatabase db = this.getWritableDatabase();
		String xQry = "Select * from hotel";
		try {
			Cursor cursor = db.rawQuery(xQry, null);

			if (cursor.moveToFirst()) {
				do {
					xBus=cursor.getString(0);
				} while (cursor.moveToNext());
			}
			cursor.close();
			db.close();
		} catch (Exception e) {
			String xError = e.toString();
		}
		return xBus;
	}
	public void insertSchool(String xSchoolName) {

		try {

			ContentValues contentValues = new ContentValues();
			contentValues.put("hotelname", xSchoolName);

			SQLiteDatabase db = this.getWritableDatabase();
			db.insert("hotel", null, contentValues);
		} catch (Exception e) {
			e.toString();
		}
	}


}

/*


*/