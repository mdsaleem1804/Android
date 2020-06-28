package com.example.ag_and_009_personalassistant;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseConnection extends SQLiteOpenHelper {

	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_NAME = "ag_and_009_personalassistant";
	String xRegistration,xReminderTable;
	public DataBaseConnection(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		try {
			xRegistration = "create table registration "
					+ "(mobileno varchar(50),password varchar(50))";
			db.execSQL(xRegistration);

			
			xReminderTable = "create table reminder " + "(name varchar(50),place varchar(50),"
					+ "description varchar(50)," + "date varchar(10)," + "expenses varchar(100))";
			db.execSQL(xReminderTable);
		} catch (Exception e) {

		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		//db.execSQL("DROP TABLE IF EXISTS " + xExpensesTable);
		onCreate(db);
	}
	public boolean Registration(String xUserMobileNo,String xPassword) {

		try {

			ContentValues contentValues = new ContentValues();
			contentValues.put("mobileno", xUserMobileNo);
			contentValues.put("password", xPassword);
			SQLiteDatabase db = this.getWritableDatabase();
			db.insert("registration", null, contentValues);
		} catch (Exception e) {
			e.toString();
		}
		return true;
	}
	
	public boolean fn_GetMobileNo(String xMobileNo) {
		boolean result = false;
		try {
			String query = "SELECT  * FROM registration where mobileno='"
					+ xMobileNo + "'";

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

	

	public List<String> fn_VendorDetails(String xWhereCondition) {
		List<String> xExpenses = new ArrayList<String>();
		SQLiteDatabase db = this.getWritableDatabase();
		String xQry = "Select * from vendorregistration "+xWhereCondition; 
		Cursor cursor = db.rawQuery(xQry, null);

		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				xExpenses.add(cursor.getString(0));
			} while (cursor.moveToNext());
		}

		// closing connection
		cursor.close();
		db.close();

		// returning lables
		return xExpenses;
	}

	public void insertReminder(String xName, String xDescription, String xDate,String xExpenses) {
		try {
			ContentValues contentValues = new ContentValues();
			contentValues.put("name", xName);
			contentValues.put("description", xDescription);
			contentValues.put("date", xDate);
			contentValues.put("expenses", xExpenses);
			SQLiteDatabase db = this.getWritableDatabase();
			db.insert("reminder", null, contentValues);		
		} catch (Exception e) {
			e.toString();
		}
	}
	
	public List<String> fn_GetReminders() {
		List<String> labels = new ArrayList<String>();
		SQLiteDatabase db = this.getWritableDatabase();
		String date = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
		String xQry = "Select * from reminder";
		Cursor cursor = db.rawQuery(xQry, null);

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
	
}

/*


*/