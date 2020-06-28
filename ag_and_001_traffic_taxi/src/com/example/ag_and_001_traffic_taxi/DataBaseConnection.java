package com.example.ag_and_001_traffic_taxi;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseConnection extends SQLiteOpenHelper {

	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_NAME = "ag_and_001_trafficandtaxi";
	String xDriverRegistration, xUserTable, xUserSendRequest, xTraffic,xAlertTable;

	public DataBaseConnection(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		try {
			xDriverRegistration = "create table driverregistration "
					+ "(_id integer PRIMARY KEY autoincrement,drivername varchar(50),"
					+ "address varchar(50),"
					+ "licenceno varchar(50),"
					+ "route varchar(50),"
					+ "username varchar(50),"
					+ "password varchar(50)," +
					"latitude varchar(50)," +
					"longitude varchar(50)," +
					"source varchar(50)," +
					"destination varchar(50))";
			db.execSQL(xDriverRegistration);

			xUserTable = "create table userdetails "
					+ "(usermobileno varchar(50))";
			db.execSQL(xUserTable);

			xUserSendRequest = "create table usersendrequest "
					+ "(usersrmobileno varchar(50),usersrdrivername varchar(50))";
			db.execSQL(xUserSendRequest);

			xTraffic = "create table traffic "
					+ "(source varchar(50),destination varchar(50),tollgatename varchar(50),details varchar(50))";
			db.execSQL(xTraffic);
			
			xAlertTable = "create table alertrecieverdetails "
					+ "(mobileno varchar(50),id varchar(10))";
			db.execSQL(xAlertTable);
			db.execSQL("insert into alertrecieverdetails values('9578795653','1')");
		} catch (Exception e) {

		}
	}

	public void insertUserDetails(String xUserMobileNo) {

		try {

			ContentValues contentValues = new ContentValues();
			contentValues.put("usermobileno", xUserMobileNo);
			SQLiteDatabase db = this.getWritableDatabase();
			db.insert("userdetails", null, contentValues);
		} catch (Exception e) {
			e.toString();
		}
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

	public void fn_DriverRegistration(String xDriverName, String xAddress,
			String xLicenceNo, String xRoute, String xUserName, String xPassword) {

		ContentValues initialValues = new ContentValues();
		initialValues.put("drivername", xDriverName);
		initialValues.put("address", xAddress);
		initialValues.put("licenceno", xLicenceNo);
		initialValues.put("route", xRoute);
		initialValues.put("username", xUserName);
		initialValues.put("password", xPassword);
		SQLiteDatabase db = this.getWritableDatabase();
		db.insert("driverregistration", null, initialValues);

	}

	public void insertUserSendRequest(String xUserMobileNo, String xDriverName) {

		try {
			ContentValues contentValues = new ContentValues();
			contentValues.put("usersrmobileno", xUserMobileNo);
			contentValues.put("usersrdrivername", xDriverName);
			SQLiteDatabase db = this.getWritableDatabase();
			db.insert("usersendrequest", null, contentValues);

		} catch (Exception e) {
			e.toString();
		}
	}

	public void insertTraffic(String xSource, String xDestination,
			String xTollGateName, String xDetails) {

		try {
			ContentValues contentValues = new ContentValues();
			contentValues.put("source", xSource);
			contentValues.put("destination", xDestination);
			contentValues.put("tollgatename", xTollGateName);
			contentValues.put("details", xDetails);
			SQLiteDatabase db = this.getWritableDatabase();
			db.insert("traffic", null, contentValues);

		} catch (Exception e) {
			e.toString();
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// db.execSQL("DROP TABLE IF EXISTS " + xExpensesTable);
		onCreate(db);
	}

	public List<String> fn_GetDrivers() {
		List<String> xExpenses = new ArrayList<String>();
		SQLiteDatabase db = this.getWritableDatabase();
		String xQry = "Select * from driverregistration";
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
			// String xError=e.toString();
		}

		return xExpenses;
	}

	public List<String> fn_GetUserRequests(String xDriverName) {
		List<String> xUserRequests = new ArrayList<String>();
		SQLiteDatabase db = this.getWritableDatabase();
		String xQry = "Select * from usersendrequest where usersrdrivername='"
				+ xDriverName + "'";
		try {
			Cursor cursor = db.rawQuery(xQry, null);

			// looping through all rows and adding to list
			if (cursor.moveToFirst()) {
				do {
					xUserRequests.add(cursor.getString(0));
				} while (cursor.moveToNext());
			}
			cursor.close();
			db.close();
		} catch (Exception e) {
			// String xError=e.toString();
		}

		return xUserRequests;
	}

	public List<String> fn_GetTrafficUpdates() {
		List<String> xTrafficUpdates = new ArrayList<String>();
		SQLiteDatabase db = this.getWritableDatabase();
		String xQry = "Select * from traffic";
		try {
			Cursor cursor = db.rawQuery(xQry, null);

			// looping through all rows and adding to list
			if (cursor.moveToFirst()) {
				do {
					xTrafficUpdates.add("Source -" + cursor.getString(0)
							+ " Destination " + cursor.getString(1)
							+ " Details " + cursor.getString(3));
				} while (cursor.moveToNext());
			}
			cursor.close();
			db.close();
		} catch (Exception e) {
			// String xError=e.toString();
		}

		return xTrafficUpdates;
	}

	public boolean fn_GetDriverLogin(String xUserName, String xPassword) {

		boolean result = false;
		try {
			String query = "SELECT  * FROM driverregistration where username='"
					+ xUserName + "' and password='" + xPassword + "';";

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

}

/*


*/