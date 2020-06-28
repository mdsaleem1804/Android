package com.hellotamila.ah_and_008_product_rating;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DataBaseConnection extends SQLiteOpenHelper {

	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_NAME = "ah_and_008_product_rating.db";
	String xUserRequests,xAddProducts;

	public DataBaseConnection(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		try {

         	xUserRequests = "create table user_requests " + "(name varchar(50),"
					+ "email varchar(50)," + "username varchar(50),"
					+ "password varchar(50))";
			db.execSQL(xUserRequests);
			xAddProducts = "create table add_products " + "(name varchar(50),"
					+ "oldrate varchar(50)," + "newrate varchar(50),"
					+ "state varchar(50)," + "description varchar(50),"
					+ "g varchar(50)," + "url varchar(50),"
					+ "i varchar(50))";
			db.execSQL(xAddProducts);




		} catch (Exception e) {

		}
	}
	
	public void fn_Delete_Products(String a) {

		SQLiteDatabase xDataBase = this.getWritableDatabase();
		xDataBase.execSQL("DELETE FROM add_products WHERE name='"
				+ a + "'");

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + xUserRequests);
		onCreate(db);
	}

	public void insertUserRequests(String a, String b, String c, String d) {
		try {
			ContentValues contentValues = new ContentValues();
			contentValues.put("name", a);
			contentValues.put("email", b);
			contentValues.put("username", c);
			contentValues.put("password", d);
			SQLiteDatabase db = this.getWritableDatabase();
			db.insert("user_requests", null, contentValues);
		} catch (Exception e) {
			e.toString();
		}
	}
	public void insertAddProducts(String a, String b, String c, String d,String f, String g, String h, String i) {
		try {
			ContentValues contentValues = new ContentValues();
			contentValues.put("name", a);
			contentValues.put("oldrate", b);
			contentValues.put("newrate", c);
			contentValues.put("state", d);
			contentValues.put("description", f);
			contentValues.put("g", g);
			contentValues.put("url", h);
			contentValues.put("i", i);
			SQLiteDatabase db = this.getWritableDatabase();
			db.insert("add_products", null, contentValues);
		} catch (Exception e) {
			e.toString();
		}
	}

    public List<String> fn_Get_Product_NewRate() {
        List<String> xProducts = new ArrayList<String>();
        SQLiteDatabase db = this.getWritableDatabase();
        String xQry = "Select name,newrate from add_products";
        try {
            Cursor cursor = db.rawQuery(xQry, null);
            // xExpenses.add("LI")
            // looping through all rows and adding to list
			xProducts.add("Product Name/Rate");
            if (cursor.moveToFirst()) {
                do {
					xProducts.add(cursor.getString(0)+"  /  "+cursor.getString(1));
                } while (cursor.moveToNext());
            }

            // closing connection
            cursor.close();
            db.close();
        } catch (Exception e) {
            String xError = e.toString();
        }

        // returning lables
        return xProducts;
    }


	public List<String> fn_GetUsers() {
		List<String> xProducts = new ArrayList<String>();
		SQLiteDatabase db = this.getWritableDatabase();
		String xQry = "Select name,email,username from user_requests";
		try {
			Cursor cursor = db.rawQuery(xQry, null);
			// xExpenses.add("LI")
			// looping through all rows and adding to list
            xProducts.add("Name/Email/UserName");
			if (cursor.moveToFirst()) {
				do {
					xProducts.add(cursor.getString(0)+"  /  "+cursor.getString(1)+"  /  "+cursor.getString(2));
				} while (cursor.moveToNext());
			}

			// closing connection
			cursor.close();
			db.close();
		} catch (Exception e) {
			String xError = e.toString();
		}

		// returning lables
		return xProducts;
	}


	public List<String> fn_Get_Product_Old_NewRate() {
		List<String> xProducts = new ArrayList<String>();
		SQLiteDatabase db = this.getWritableDatabase();
		String xQry = "Select name,oldrate,newrate from add_products";
		try {
			Cursor cursor = db.rawQuery(xQry, null);
			// xExpenses.add("LI")
			// looping through all rows and adding to list
			xProducts.add("Product Name/Old Rate/New Rate");
			if (cursor.moveToFirst()) {
				do {
					xProducts.add(cursor.getString(0)+"  /  "+cursor.getString(1)+"  /  "+cursor.getString(2));
				} while (cursor.moveToNext());
			}

			// closing connection
			cursor.close();
			db.close();
		} catch (Exception e) {
			String xError = e.toString();
		}

		// returning lables
		return xProducts;
	}

}

