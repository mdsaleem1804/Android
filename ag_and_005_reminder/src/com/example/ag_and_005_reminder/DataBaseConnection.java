package com.example.ag_and_005_reminder;

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
	private static final String DATABASE_NAME = "ag_and_005_reminder.db";
	String xExpensesTable, xReminderTable;

	public DataBaseConnection(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		try {

			xExpensesTable = "create table expenses "
					+ "(id INTEGER PRIMARY KEY   AUTOINCREMENT,categoryname varchar(50),"
					+ "date varchar(50)," + "itemname varchar(10),"
					+ "details varchar(20) ," + "price varchar(50),"
					+ "paymentmode varchar(50))";
			db.execSQL(xExpensesTable);

			xReminderTable = "create table reminder " + "(name varchar(50),"
					+ "description varchar(50)," + "date varchar(10))";
			db.execSQL(xReminderTable);

		} catch (Exception e) {

		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + xExpensesTable);
		onCreate(db);
	}

	public List<String> fn_CheckReminders() {
		List<String> labels = new ArrayList<String>();

		// Select All Query

		/*
		 * String selectQuery =
		 * "SELECT  patientremarks FROM userimagedetails where username='" +
		 * username + "'";
		 * 
		 * SQLiteDatabase db = this.getReadableDatabase();
		 */
		SQLiteDatabase db = this.getWritableDatabase();
		// SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		String date = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
		String xQry = "Select * from reminder where  date = '" + date + "'";
		Cursor cursor = db.rawQuery(xQry, null);

		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				labels.add(cursor.getString(0) + "" + cursor.getString(1));
			} while (cursor.moveToNext());
		}

		// closing connection
		cursor.close();
		db.close();

		// returning lables
		return labels;
	}

	public boolean CheckNotification() {

		try {
			SQLiteDatabase db = this.getWritableDatabase();
			// SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			String date = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
			String xQry = "Select * from reminder where  date = '" + date + "'";
			Cursor c = db.rawQuery(xQry, null);
			if (c.moveToFirst()) {
				String xValue = c.getString(1);
				// Toast.makeText(getApplicationContext(), c.getString(1),
				// 1000).show();
			}
		} catch (Exception e) {
			String xError = e.toString();
		}
		return true;
	}

	public void insertExpenses(String xCategory, String xDate,
			String xItemName, String xDetails, String xPrice,
			String xPaymentMode) {

		try {

			ContentValues contentValues = new ContentValues();
			contentValues.put("categoryname", xCategory);
			contentValues.put("date", xDate);
			contentValues.put("itemname", xItemName);
			contentValues.put("details", xDetails);
			contentValues.put("price", xPrice);
			contentValues.put("paymentmode", xPaymentMode);
			SQLiteDatabase db = this.getWritableDatabase();
			db.insert("expenses", null, contentValues);
		} catch (Exception e) {
			e.toString();
		}
	}

	public void fn_UpdateExpenses(String xCategory, String xDate,
			String xItemName, String xDetails, String xPrice,
			String xPaymentMode, int xId) {

		try {

			ContentValues contentValues = new ContentValues();
			contentValues.put("categoryname", xCategory);
			contentValues.put("date", xDate);
			contentValues.put("itemname", xItemName);
			contentValues.put("details", xDetails);
			contentValues.put("price", xPrice);
			contentValues.put("paymentmode", xPaymentMode);
			SQLiteDatabase db = this.getWritableDatabase();
			db.update("expenses", contentValues, "id=" + xId + "", null);
		} catch (Exception e) {
			e.toString();
		}
	}

	public void fn_DeleteExpenses(int xId) {

		SQLiteDatabase xDataBase = this.getWritableDatabase();
		xDataBase.execSQL("DELETE FROM expenses WHERE id=" + xId + "");

	}

	public void insertReminder(String xName, String xDescription, String xDate) {

		try {

			ContentValues contentValues = new ContentValues();
			contentValues.put("name", xName);
			contentValues.put("description", xDescription);
			contentValues.put("date", xDate);

			SQLiteDatabase db = this.getWritableDatabase();
			db.insert("reminder", null, contentValues);

		} catch (Exception e) {
			e.toString();
		}
	}

	public List<String> fn_CheckExpenses(String xWhereCondition) {
		List<String> xExpenses = new ArrayList<String>();
		SQLiteDatabase db = this.getWritableDatabase();
		String xQry = "Select * from expenses " + xWhereCondition;
		Cursor cursor = db.rawQuery(xQry, null);
		if (cursor.moveToFirst()) {
			do {
				xExpenses.add(cursor.getString(0) + "- " + cursor.getString(1)
						+ "- " + cursor.getString(4));
			} while (cursor.moveToNext());
		}
		cursor.close();
		db.close();
		return xExpenses;
	}
	
	public int fn_SumExpenses(String xCategoryName) {
		int xValue=0;
		SQLiteDatabase db = this.getWritableDatabase();
		String xQry = "select sum(price)as price from expenses where categoryname='" + xCategoryName +"'";
		Cursor cursor = db.rawQuery(xQry, null);
		cursor.moveToFirst();
		try
		{
		 xValue=Integer.parseInt(cursor.getString(0));
		}catch(Exception e)
		{
			String xError=e.toString();
		}
		cursor.close();
		db.close();
		return xValue;
	}
	public int fn_SumOtherExpenses() {
		int xValue=0;
		SQLiteDatabase db = this.getWritableDatabase();
		String xQry = "select sum(price)as price from expenses " +
				"where categoryname!='Medical' and categoryname!='Entertainment' and categoryname!='Food' and categoryname!='Travel' and categoryname!='Dress'";
		Cursor cursor = db.rawQuery(xQry, null);
		cursor.moveToFirst();
		try
		{
		 xValue=Integer.parseInt(cursor.getString(0));
		}catch(Exception e)
		{
			String xError=e.toString();
		}
		cursor.close();
		db.close();
		return xValue;
	}
}
