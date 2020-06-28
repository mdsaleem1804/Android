package com.tcssnellai.ag_and_004_womenscheme;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseConnection extends SQLiteOpenHelper {

	private static final int DATABASE_VERSION = 2;
	private static final String DATABASE_NAME = "ag_and_004_womenscheme1";

	String xSchemeDetails, xWomenRegistration;

	public DataBaseConnection(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		try {

			xWomenRegistration = "create table womenregistration "
					+ "(name varchar(50)," + "age varchar (10),"
					+ "education varchar(30)," + "section varchar(30),"
					+ "caste varchar(50) ," + "address varchar(50),"
					+ "username varchar(50),password varchar(50))";

			db.execSQL(xWomenRegistration);

			xSchemeDetails = "create table schemes "
					+ "( ID INTEGER PRIMARY KEY   AUTOINCREMENT,schemename varchar(50)," + "minage varchar (10),"
					+ "maxage varchar(10)," + "education varchar(30),"
					+ "section varchar(30)," + "caste varchar(30),"
					+ "amount varchar(50) ," + "detail varchar(200),"
					+ "website varchar(100))";

			db.execSQL(xSchemeDetails);
		} catch (Exception e) {

			
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + xWomenRegistration);
		db.execSQL("DROP TABLE IF EXISTS " + xSchemeDetails);
		onCreate(db);
	}

	public boolean fn_WomenRegistration(String xName, String xAge,
			String xEducation, String xCaste, String xSection, String xAddress,
			String xUserName, String xPassword) {

		try {

			ContentValues contentValues = new ContentValues();
			contentValues.put("name", xName);
			contentValues.put("age", xAge);
			contentValues.put("education", xEducation);
			contentValues.put("caste", xCaste);
			contentValues.put("section", xSection);
			contentValues.put("address", xAddress);
			contentValues.put("username", xUserName);
			contentValues.put("password", xPassword);
			SQLiteDatabase db = this.getWritableDatabase();
			db.insert("womenregistration", null, contentValues);
			return true;
		} catch (Exception e) {
			e.toString();
		}
		return false;
	}

	public boolean fn_GetWomenRegistration(String username, String Password) {

		boolean result = false;
		try {
			String query = "SELECT  * FROM womenregistration where username='"
					+ username + "' and password='" + Password + "';";

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
	
	public boolean fn_GetSchemeRegistration(String xSchemeName) {

		boolean result = false;
		try {
			String query = "SELECT  * FROM schemes where schemename='"
					+ xSchemeName + "';";

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

	public void fn_DeleteSchemeDetails(String xSchemeName) {

		SQLiteDatabase xDataBase = this.getWritableDatabase();
		xDataBase.execSQL("DELETE FROM schemes WHERE schemename='"
				+ xSchemeName + "'");

	}

	public boolean fn_DataProcess(String xSchemeName, String xMinAge,
			String xMaxAge, String xEducation, String xCaste, String xSection, String xAmount,
			String xDetails, String xWebsite, String xMode) {

		try {

			ContentValues contentValues = new ContentValues();
			contentValues.put("schemename", xSchemeName);
			contentValues.put("minage", xMinAge);
			contentValues.put("maxage", xMaxAge);
			contentValues.put("education", xEducation);
			contentValues.put("caste", xCaste);
			contentValues.put("section", xSection);
			contentValues.put("amount", xAmount);
			contentValues.put("detail", xDetails);
			contentValues.put("website", xWebsite);
			SQLiteDatabase db = this.getWritableDatabase();
			if (xMode.equalsIgnoreCase("S")) {
				db.insert("schemes", null, contentValues);
				return true;
			}
			if (xMode.equalsIgnoreCase("U")) {
				db.update("schemes", contentValues, "schemename='"
						+ xSchemeName + "'", null);

				return true;
			}

		} catch (Exception e) {
			e.toString();
		}
		return false;
	}

	public List<String> fn_GetWomens() {
		List<String> xExpenses = new ArrayList<String>();
		SQLiteDatabase db = this.getWritableDatabase();
		String xQry = "Select * from womenregistration";
		try {
			Cursor cursor = db.rawQuery(xQry, null);

			// looping through all rows and adding to list
			if (cursor.moveToFirst()) {
				do {
					xExpenses.add(cursor.getString(0) + "- "
							+ cursor.getString(1) + "- " + cursor.getString(4));
				} while (cursor.moveToNext());
			}

			// closing connection
			cursor.close();
			db.close();
		} catch (Exception e) {
			String xError = e.toString();
		}

		// returning lables
		return xExpenses;
	}

	public List<String> fn_GetSchemes(String xWhereClause) {
		List<String> xExpenses = new ArrayList<String>();
		SQLiteDatabase db = this.getWritableDatabase();
		String xQry = "Select * from schemes " + xWhereClause;
		try {
			Cursor cursor = db.rawQuery(xQry, null);
			// xExpenses.add("LI")
			// looping through all rows and adding to list
			if (cursor.moveToFirst()) {
				do {
					xExpenses.add(cursor.getString(1));
				} while (cursor.moveToNext());
			}

			// closing connection
			cursor.close();
			db.close();
			
		} catch (Exception e) {
			String xError = e.toString();
		}

		// returning lables
		return xExpenses;
	}
}

/*


*/