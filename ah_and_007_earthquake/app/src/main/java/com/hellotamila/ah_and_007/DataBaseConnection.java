package com.hellotamila.ah_and_007;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DataBaseConnection extends SQLiteOpenHelper {

	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_NAME = "ah_and_007.db";
	String xUserRequests,xAddFir,xHospitalData;

	public DataBaseConnection(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		try {

         	xUserRequests = "create table user_requests " + "(fullname varchar(50),"
					+ "aadhar varchar(50)," + "mobileno varchar(50),"
					+ "remarks varchar(50))";
			db.execSQL(xUserRequests);

			xAddFir = "create table add_fir " + "(fullname varchar(50),"
					+ "aadhar varchar(50)," + "mobileno varchar(50),"
					+ "remarks varchar(50),latitude varchar(50),longitude varchar(50))";
			db.execSQL(xAddFir);
			xHospitalData = "create table add_hospital_data " + "(fullname varchar(50),"
					+ "aadhar varchar(50)," + "mobileno varchar(50),"
					+ "remarks varchar(50),latitude varchar(50),longitude varchar(50))";
			db.execSQL(xHospitalData);


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
		db.execSQL("DROP TABLE IF EXISTS " + xUserRequests);
		db.execSQL("DROP TABLE IF EXISTS " + xAddFir);
		db.execSQL("DROP TABLE IF EXISTS " + xHospitalData);
		onCreate(db);
	}

	public void insertUserRequests(String a, String b, String c, String d) {
		try {
			ContentValues contentValues = new ContentValues();
			contentValues.put("fullname", a);
			contentValues.put("aadhar", b);
			contentValues.put("mobileno", c);
			contentValues.put("remarks", d);
			SQLiteDatabase db = this.getWritableDatabase();
			db.insert("user_requests", null, contentValues);
		} catch (Exception e) {
			e.toString();
		}
	}
	public void insertFir(String a, String b, String c, String d,String f,String g) {
		try {
			ContentValues contentValues = new ContentValues();
			contentValues.put("fullname", a);
			contentValues.put("aadhar", b);
			contentValues.put("mobileno", c);
			contentValues.put("remarks", d);
			contentValues.put("latitude", f);
			contentValues.put("longitude", g);
			SQLiteDatabase db = this.getWritableDatabase();
			db.insert("add_fir", null, contentValues);
		} catch (Exception e) {
			e.toString();
		}
	}
	public void insertHospitalData(String a, String b, String c, String d,String f,String g) {
		try {
			ContentValues contentValues = new ContentValues();
			contentValues.put("fullname", a);
			contentValues.put("aadhar", b);
			contentValues.put("mobileno", c);
			contentValues.put("remarks", d);
			contentValues.put("latitude", f);
			contentValues.put("longitude", g);
			SQLiteDatabase db = this.getWritableDatabase();
			db.insert("add_hospital_data", null, contentValues);
		} catch (Exception e) {
			e.toString();
		}
	}

    public List<String> fn_Get_UserRequests() {
        List<String> xBus = new ArrayList<String>();
        SQLiteDatabase db = this.getWritableDatabase();
        String xQry = "Select * from user_requests";
        try {
            Cursor cursor = db.rawQuery(xQry, null);
            // xExpenses.add("LI")
            // looping through all rows and adding to list
            if (cursor.moveToFirst()) {
                do {
                    xBus.add(cursor.getString(1));
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
	

}

