package com.example.ag_and_008_smallscale;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
 
public class DbAdapter {
 
 public static final String KEY_ROWID = "_id";
 public static final String KEY_NAME = "name";
 public static final String KEY_LOCATION = "location";
 public static final String KEY_DETAILS = "details";
 public static final String KEY_LANDMARK = "landmark";
 public static final String KEY_PRODUCTNAME = "productname";
 public static final String KEY_PRODUCTPRICE= "productprice";
  public static final String KEY_USERNAME = "username";
 public static final String KEY_PASSWORD = "password";
 
 private static final String TAG = "VendorReg";
 private DatabaseHelper mDbHelper;
 private SQLiteDatabase mDb;
 
 private static final String DATABASE_NAME = "ag_and_008_smallscale";
 private static final String SQLITE_TABLE = "vendorregistration";
 private static final int DATABASE_VERSION = 1;
 
 private final Context mCtx;
 
 private static final String DATABASE_CREATE =
  "CREATE TABLE if not exists " + SQLITE_TABLE + " (" +
  KEY_ROWID + " integer PRIMARY KEY autoincrement," +
  KEY_NAME + "," +
  KEY_LOCATION + "," +
  KEY_DETAILS + "," +
  KEY_LANDMARK + "," +
  KEY_PRODUCTNAME + "," +
  KEY_PRODUCTPRICE + "," +
  KEY_USERNAME + "," +
  KEY_PASSWORD + "," +
  " UNIQUE (" + KEY_NAME +"));";
 
 private static class DatabaseHelper extends SQLiteOpenHelper {
 
  DatabaseHelper(Context context) {
   super(context, DATABASE_NAME, null, DATABASE_VERSION);
  }
 
 
  @Override
  public void onCreate(SQLiteDatabase db) {
   Log.w(TAG, DATABASE_CREATE);
   db.execSQL(DATABASE_CREATE);
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
 
 public long CreateVendorDetails(String xName , String xLocation, 
   String xDetails, String xLandMark,String xProductName,String xProductPrice,
   String xUserName, String xPassword) {
 
  ContentValues initialValues = new ContentValues();
  initialValues.put(KEY_NAME, xName);
  initialValues.put(KEY_LOCATION, xLocation);
  initialValues.put(KEY_DETAILS, xDetails);
  initialValues.put(KEY_LANDMARK, xLandMark);
  initialValues.put(KEY_PRODUCTNAME, xProductName);
  initialValues.put(KEY_PRODUCTPRICE, xProductPrice);
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
     KEY_NAME, KEY_LOCATION, KEY_PRODUCTNAME, KEY_PRODUCTPRICE}, 
     null, null, null, null, null);
 
  }
  else {
   mCursor = mDb.query(true, SQLITE_TABLE, new String[] {KEY_ROWID,
     KEY_NAME, KEY_LOCATION, KEY_PRODUCTNAME, KEY_PRODUCTPRICE}, 
     KEY_PRODUCTNAME + " like '%" + inputText + "%'", null,
     null, null, null, null);
  }
  if (mCursor != null) {
   mCursor.moveToFirst();
  }
  return mCursor;
 
 }
 
 public Cursor fetchAllVendors() {
 
  Cursor mCursor = mDb.query(SQLITE_TABLE, new String[] {KEY_ROWID,
    KEY_NAME, KEY_LOCATION, KEY_PRODUCTNAME, KEY_PRODUCTPRICE}, 
    null, null, null, null, null);
   if (mCursor != null) {
   mCursor.moveToFirst();
  }
  return mCursor;
 }
 
 public void insertSomeCountries() {
 
  //createCountry("AFG","Afghanistan","Asia","Southern and Central Asia");
 
 }
 
}