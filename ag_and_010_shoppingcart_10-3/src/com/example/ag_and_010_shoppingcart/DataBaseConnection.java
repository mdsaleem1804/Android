package com.example.ag_and_010_shoppingcart;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseConnection extends SQLiteOpenHelper {

	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_NAME = "ag_and_010_shoppingcart";
	String xVendorRegistration, xUserRegistration, xProduct, xCart;

	public DataBaseConnection(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		try {
			xVendorRegistration = "create table vendorregistration "
					+ "(name varchar(50),location varchar(50),details varchar(50),"
					+ "landmark varchar(50),businessid varchar(50),floor varchar(50)"
					+ ",username varchar(50),password varchar(50))";
			db.execSQL(xVendorRegistration);

			xUserRegistration = "create table userregistration "
					+ "(name varchar(50),address varchar(50),city varchar(50),"
					+ "state varchar(50),pincode varchar(50)"
					+ ",mobile varchar(50),username varchar(50),password varchar(50))";
			db.execSQL(xUserRegistration);

			xProduct = "create table product "
					+ "(pname varchar(50),pprice varchar(50),pvendorname varchar(50),image blob)";
			db.execSQL(xProduct);

			xCart = "create table cart "
					+ "(pname varchar(50),pprice varchar(50),pvendorname varchar(50),pusername varchar(50))";
			db.execSQL(xCart);

		} catch (Exception e) {

		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// db.execSQL("DROP TABLE IF EXISTS " + xExpensesTable);
		onCreate(db);
	}

	public Boolean fn_InsertVendorDetails(String xName, String xLocation,
			String xDetails, String xLandMark, String xBusinessId,
			String xFloor, String xUserName, String xPassword) {

		try {

			ContentValues contentValues = new ContentValues();
			contentValues.put("name", xName);
			contentValues.put("location", xLocation);
			contentValues.put("details", xDetails);
			contentValues.put("landmark", xLandMark);
			contentValues.put("businessid", xBusinessId);
			contentValues.put("floor", xFloor);
			contentValues.put("username", xUserName);
			contentValues.put("password", xPassword);

			SQLiteDatabase db = this.getWritableDatabase();
			db.insert("vendorregistration", null, contentValues);

		} catch (Exception e) {
			e.toString();
		}
		return true;
	}

	public Boolean fn_InsertUserDetails(String xName, String xAddress,
			String xCity, String xState, String xPinCode, String xMobile,
			String xUserName, String xPassword) {

		try {

			ContentValues contentValues = new ContentValues();
			contentValues.put("name", xName);
			contentValues.put("address", xAddress);
			contentValues.put("city", xCity);
			contentValues.put("state", xState);
			contentValues.put("pincode", xPinCode);
			contentValues.put("mobile", xMobile);
			contentValues.put("username", xUserName);
			contentValues.put("password", xPassword);

			SQLiteDatabase db = this.getWritableDatabase();
			db.insert("userregistration", null, contentValues);

		} catch (Exception e) {
			e.toString();
		}
		return true;
	}

	public Boolean fn_InsertProductDetails(String xProductName,
			String xProductPrice, String xVendorName,byte[] xByteImage) {

		try {

			ContentValues contentValues = new ContentValues();
			contentValues.put("pname", xProductName);
			contentValues.put("pprice", xProductPrice);
			contentValues.put("pvendorname", xVendorName);
			contentValues.put("image", xByteImage);

			SQLiteDatabase db = this.getWritableDatabase();
			db.insert("product", null, contentValues);

		} catch (Exception e) {
			e.toString();
		}
		return true;
	}

	public Boolean fn_InsertCart(String xProductName, String xProductPrice, String xVendorName,
			String xUserName) {

		try {
			ContentValues contentValues = new ContentValues();
			contentValues.put("pname", xProductName);
			contentValues.put("pprice", xProductPrice);
			contentValues.put("pvendorname", xVendorName);
			
			contentValues.put("pusername", xUserName);

			SQLiteDatabase db = this.getWritableDatabase();
			db.insert("cart", null, contentValues);

		} catch (Exception e) {
			e.toString();
		}
		return true;
	}

	public boolean fn_GetVendos(String xUserName, String xPassword) {
		boolean result = false;
		try {
			String query = "SELECT  * FROM vendorregistration where username='"
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

	public boolean fn_GetUsers(String xUserName, String xPassword) {

		boolean result = false;
		try {
			String query = "SELECT  * FROM userregistration where username='"
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

	public List<String> fn_GetVendors() {
		List<String> xBus = new ArrayList<String>();
		SQLiteDatabase db = this.getWritableDatabase();
		String xQry = "Select * from vendorregistration ";
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

	public List<String> fn_GetProducts(String xVendorName) {
		List<String> xBus = new ArrayList<String>();
		SQLiteDatabase db = this.getWritableDatabase();
		String xQry = "Select * from product where pvendorname ='"+xVendorName+"'";
		try {
			Cursor cursor = db.rawQuery(xQry, null);
			// xExpenses.add("LI")
			// looping through all rows and adding to list
			if (cursor.moveToFirst()) {
				do {
					xBus.add(cursor.getString(0)+"-"+cursor.getString(1)+"-"+cursor.getString(2));
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

	// Getting All Contacts
	public List<Contact> getAllProducts(String xVendorName) {
		List<Contact> contactList = new ArrayList<Contact>();
		// Select All Query
		String selectQuery = "SELECT  * FROM product where pvendorname ='"+xVendorName+"'";

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);
		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				Contact contact = new Contact();
				contact.setName(cursor.getString(0));
				contact.setImage(cursor.getBlob(3));
				// Adding contact to list
				contactList.add(contact);
			} while (cursor.moveToNext());
		}
		// close inserting data from database
		db.close();
		// return contact list
		return contactList;

	}

	
	public List<String> fn_VendorDetails(String xWhereCondition) {
		List<String> xExpenses = new ArrayList<String>();
		SQLiteDatabase db = this.getWritableDatabase();
		String xQry = "Select * from vendorregistration " + xWhereCondition;
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

	public List<String> fn_GetCart(String xVendorName) {
		List<String> xCartItem = new ArrayList<String>();
		SQLiteDatabase db = this.getWritableDatabase();
		String xQry = "Select * from cart where pvendorname='" + xVendorName
				+ "'";
		try {
			Cursor cursor = db.rawQuery(xQry, null);
			// xExpenses.add("LI")
			// looping through all rows and adding to list
			if (cursor.moveToFirst()) {
				do {
					xCartItem.add("Pr.Name "+ cursor.getString(0)+ "Price : "
							+ cursor.getString(1) + "User Name : "
							+ cursor.getString(2));
				} while (cursor.moveToNext());
			}

			// closing connection
			cursor.close();
			db.close();
		} catch (Exception e) {
			String xError = e.toString();
		}

		// returning lables
		return xCartItem;
	}

}

/*


*/