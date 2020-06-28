package com.example.ag_and_002_tourist;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseConnection extends SQLiteOpenHelper {

	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_NAME = "ag_and_002_tourist.db";
	String xHotelTable, xPlacesTable,xRouteTable,xBusTable;

	public DataBaseConnection(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		try {

			xHotelTable = "create table hotel " + "(hotelname varchar(50),"
					+ "sbed int (10)," + "dbed int(10),"
					+ "facilities varchar(50) ," + "others varchar(50))";

			db.execSQL(xHotelTable);
			
			xPlacesTable = "create table places " + "(placename varchar(50),"
					+ "districtname varchar(50)," + "details varchar(50),"
					+ "favourites varchar(50) ," + "nearesthotel varchar(50))";

			db.execSQL(xPlacesTable);
			
			
			xRouteTable="create table route (districtname varchar(50),placename varchar(50),path varchar(250),path2 varchar(250))";
			db.execSQL(xRouteTable);
			
			
			xBusTable = "create table bus " + "(busname varchar(50),"
					+ "departure varchar(50)," + "arrival varchar(50),"
					+ "fare varchar(50) ," + "website varchar(50))";

			db.execSQL(xBusTable);

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
		db.execSQL("DROP TABLE IF EXISTS " + xPlacesTable);
		onCreate(db);
	}
	public List<String> fn_GetBus() {
		List<String> xBus = new ArrayList<String>();
		SQLiteDatabase db = this.getWritableDatabase();
		String xQry = "Select * from bus";
		try {
			Cursor cursor = db.rawQuery(xQry, null);
			// xExpenses.add("LI")
			// looping through all rows and adding to list
			if (cursor.moveToFirst()) {
				do {
					xBus.add(cursor.getString(0));
				} while (cursor.moveToNext());
			}

			// closing connection
			cursor.close();
			db.close();
		} catch (Exception e) {
			String xError = e.toString();
		}

		// returning lables
		return xBus;
	}
	
	public List<String> fn_GetPlaces() {
		List<String> xPlaces = new ArrayList<String>();
		SQLiteDatabase db = this.getWritableDatabase();
		String xQry = "Select * from places";
		try {
			Cursor cursor = db.rawQuery(xQry, null);
			// xExpenses.add("LI")
			// looping through all rows and adding to list
			if (cursor.moveToFirst()) {
				do {
					xPlaces.add(cursor.getString(0));
				} while (cursor.moveToNext());
			}

			// closing connection
			cursor.close();
			db.close();
		} catch (Exception e) {
			String xError = e.toString();
		}

		// returning lables
		return xPlaces;
	}
	public void insertHotel(String hotelname, int sbed, int dbed, String facilities,
			String others) {

		try {

			ContentValues contentValues = new ContentValues();
			contentValues.put("hotelname", hotelname);
			contentValues.put("sbed", sbed);
			contentValues.put("dbed", dbed);
			contentValues.put("facilities", facilities);
			contentValues.put("others", others);
			SQLiteDatabase db = this.getWritableDatabase();
			db.insert("hotel", null, contentValues);
		} catch (Exception e) {
			e.toString();
		}
	}
	
	public void insertPlaces(String placename, String districtname, String details, String favourites,
			String nearesthotel) {

		try {

			ContentValues contentValues = new ContentValues();
			contentValues.put("placename", placename);
			contentValues.put("districtname", districtname);
			contentValues.put("details", details);
			contentValues.put("favourites", favourites);
			contentValues.put("nearesthotel", nearesthotel);
			SQLiteDatabase db = this.getWritableDatabase();
			db.insert("places", null, contentValues);
		} catch (Exception e) {
			e.toString();
		}
	}
	
	public boolean insertBus(String xBusName, String xDeparture, String xArrival, String xFare,
			String xWebsite,String xMode) {

		try {
			SQLiteDatabase db = this.getWritableDatabase();
			ContentValues contentValues = new ContentValues();
			contentValues.put("busname", xBusName);
			contentValues.put("departure", xDeparture);
			contentValues.put("arrival", xArrival);
			contentValues.put("fare", xFare);
			contentValues.put("website", xWebsite);
			if(xMode.equalsIgnoreCase("S")){
				db.insert("bus", null, contentValues);
				return true;
				}
				if(xMode.equalsIgnoreCase("U")){
					db.update("bus", contentValues, "busname='"
					+ xBusName + "'", null);				
					return true;
					}
			
			db.insert("bus", null, contentValues);
		} catch (Exception e) {
			e.toString();
		}
		return false;
	}
	
	public void insertPath(String districtname, String placename, String Path, String Path2) {

		try {

			ContentValues contentValues = new ContentValues();
			contentValues.put("districtname", districtname);
			contentValues.put("placename", placename);
			contentValues.put("path", Path);
			contentValues.put("path2", Path2);
			SQLiteDatabase db = this.getWritableDatabase();
			db.insert("route", null, contentValues);
		} catch (Exception e) {
			e.toString();
		}
	}
}

/*


*/