package com.example.ag_and_017_banking;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseConnection extends SQLiteOpenHelper {

	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_NAME = "accounts.db";
	String xDriverRegistration, xUserTable, xUserSendRequest, xTraffic;

	public DataBaseConnection(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		try {

		} catch (Exception e) {

		}
	}

	
	public boolean fn_GetLogin(String xUserName, String xPassword) {

		boolean result = false;
		try {
			String query = "SELECT  * FROM accounts where acno='"
					+ xUserName + "' and micr='" + xPassword + "';";

			SQLiteDatabase db = this.getWritableDatabase();
			Cursor cursor = db.rawQuery(query, null);
			if (cursor.getCount() > 0) {
				result = true;
			}
		} catch (Exception e) {
			String xError = e.toString();
		}

		return result;
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub
		
	}

}

/*


*/