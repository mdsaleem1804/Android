package com.example.ag_and_001_traffic_taxi;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
 
public class DbAdapter {
 
 public static final String KEY_ROWID = "_id";
 public static final String KEY_DRIVERNAME = "drivername";
 public static final String KEY_ADDRESS = "address";
 public static final String KEY_LICENCENO = "licenceno";
 public static final String KEY_ROUTE = "route";
 public static final String KEY_USERNAME = "username";
 public static final String KEY_PASSWORD = "password";
 public static final String KEY_SOURCE = "source";
 public static final String KEY_DESTINATION= "destination";
 
 private static final String TAG = "dRIVERrEG";
 private DatabaseHelper mDbHelper;
 private SQLiteDatabase mDb;
 String xError;
 private static final String DATABASE_NAME = "ag_and_001_trafficandtaxi";
 private static final String SQLITE_TABLE = "driverregistration";
 private static final int DATABASE_VERSION = 1;
 
 private final Context mCtx;
 
 private static final String DATABASE_CREATE =
  "CREATE TABLE if not exists " + SQLITE_TABLE + " (" +
  KEY_ROWID + " integer PRIMARY KEY autoincrement," +
  KEY_DRIVERNAME + "," +
  KEY_ADDRESS + "," +
  KEY_LICENCENO + "," +
  KEY_ROUTE + "," +
  KEY_USERNAME + "," +
  KEY_PASSWORD + "," +
  " UNIQUE (" + KEY_DRIVERNAME +"));";
 
 private static class DatabaseHelper extends SQLiteOpenHelper {
 
  DatabaseHelper(Context context) {
   super(context, DATABASE_NAME, null, DATABASE_VERSION);
  }
 
 
  @Override
  public void onCreate(SQLiteDatabase db) {
   Log.w(TAG, DATABASE_CREATE);
  db.execSQL(DATABASE_CREATE);
   String xUserTable = "create table userdetails " + "(usermobileno varchar(50))";
	db.execSQL(xUserTable);
  }
 
  @Override
  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
   Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
     + newVersion + ", which will destroy all old data");
   db.execSQL("DROP TABLE IF EXISTS " + SQLITE_TABLE);
   onCreate(db);
  }
 }
 
 public DbAdapter(Context ctx) {
  this.mCtx = ctx;
 }
 
 public DbAdapter open() throws SQLException {
  mDbHelper = new DatabaseHelper(mCtx);
  mDb = mDbHelper.getWritableDatabase();
  return this;
 }
 
 public void close() {
  if (mDbHelper != null) {
   mDbHelper.close();
  }
 }
 
 public long createCountry(String xDriverName , String xAddress, 
   String xLicenceNo, String xRoute,
   String xUserName, String xPassword) {
 
  ContentValues initialValues = new ContentValues();
  initialValues.put(KEY_DRIVERNAME, xDriverName);
  initialValues.put(KEY_ADDRESS, xAddress);
  initialValues.put(KEY_LICENCENO, xLicenceNo);
  initialValues.put(KEY_ROUTE, xRoute);
  initialValues.put(KEY_USERNAME, xUserName);
  initialValues.put(KEY_PASSWORD, xPassword);
 
  return mDb.insert(SQLITE_TABLE, null, initialValues);
 }
 
 public boolean deleteAllCountries() {
 
  int doneDelete = 0;
  doneDelete = mDb.delete(SQLITE_TABLE, null , null);
  Log.w(TAG, Integer.toString(doneDelete));
  return doneDelete > 0;
 
 }
 
 public Cursor fetchCountriesByName(String inputText) throws SQLException {
  Log.w(TAG, inputText);
  Cursor mCursor = null;
  if (inputText == null  ||  inputText.length () == 0)  {
   mCursor = mDb.query(SQLITE_TABLE, new String[] {KEY_ROWID,
     KEY_DRIVERNAME, KEY_ADDRESS, KEY_LICENCENO, KEY_ROUTE}, 
     null, null, null, null, null);
 
  }
  else {
   mCursor = mDb.query(true, SQLITE_TABLE, new String[] {KEY_ROWID,
     KEY_DRIVERNAME, KEY_ADDRESS, KEY_LICENCENO, KEY_ROUTE}, 
     KEY_DRIVERNAME + " like '%" + inputText + "%'", null,
     null, null, null, null);
  }
  if (mCursor != null) {
   mCursor.moveToFirst();
  }
  return mCursor;
 
 }
 
 public Cursor fetchAllCountries() {
 
  Cursor mCursor = mDb.query(SQLITE_TABLE, new String[] {KEY_ROWID,
    KEY_DRIVERNAME, KEY_ADDRESS, KEY_LICENCENO, KEY_ROUTE,KEY_USERNAME,KEY_PASSWORD,KEY_SOURCE,KEY_DESTINATION}, 
    null, null, null, null, null);
 
  if (mCursor != null) {
   mCursor.moveToFirst();
  }
  return mCursor;
 }
 
 public void insertSomeCountries() {
 
  //createCountry("AFG","Afghanistan","Asia","Southern and Central Asia");
 
 }
 

	public List<String> fn_GetDrivers() {
		List<String> xExpenses = new ArrayList<String>();
		//SQLiteDatabase db = this.getWritableDatabase();
		String xQry = "Select * from driverregistration"; 
		try{
		Cursor cursor = mDb.rawQuery(xQry,null,null);

		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				xExpenses.add(cursor.getString(0)+"- "+ cursor.getString(1)+"- "+ cursor.getString(4));
			} while (cursor.moveToNext());
		}
		
		// closing connection
		cursor.close();
		mDb.close();
		}
		catch(Exception e)
		{
			 xError=e.toString();
		}

		// returning lables
		return xExpenses;
	}


 
}