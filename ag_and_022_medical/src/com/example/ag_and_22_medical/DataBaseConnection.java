package com.example.ag_and_22_medical;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseConnection extends SQLiteOpenHelper {

	private static final int DATABASE_VERSION = 3;
	private static final String DATABASE_NAME = "ag_and_022_medical";

	String xPatientRegistration, xIpAdmission, xAdvance;

	public DataBaseConnection(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		try {

			xPatientRegistration = "create table patientregistration"
					+ "(patientname varchar(50)," + "age varchar (50),"
					+ "address varchar(50)," + "mobileno varchar(50),"
					+ "diagnosis varchar(50) ,"
					+ "emergencycontactno varchar(50),"
					+ "emergencycontactaddress varchar(50))";

			db.execSQL(xPatientRegistration);

			xIpAdmission = "create table ipadmission" + "(date varchar(50),"
					+ "patientname varchar (50)," + "casetype varchar(50),"
					+ "roomno varchar(50)," + "roomtype varchar(50))";

			db.execSQL(xIpAdmission);

			xAdvance = "create table advance" + "(date varchar(50),"
					+ "patientname varchar (50)," + "amount varchar(50))";

			db.execSQL(xAdvance);

		} catch (Exception e) {

		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + xPatientRegistration);

		onCreate(db);
	}

	public boolean fn_PatientRegistration(String xPatientName, String xAge,
			String xAddress, String xMobileNo, String xDiagnosis,
			String xEContactNo, String xEContactAddr) {

		try {

			ContentValues contentValues = new ContentValues();
			contentValues.put("patientname", xPatientName);
			contentValues.put("age", xAge);
			contentValues.put("address", xAddress);
			contentValues.put("mobileno", xMobileNo);
			contentValues.put("diagnosis", xDiagnosis);
			contentValues.put("emergencycontactno", xEContactNo);
			contentValues.put("emergencycontactaddress", xEContactAddr);

			SQLiteDatabase db = this.getWritableDatabase();
			db.insert("patientregistration", null, contentValues);
			return true;
		} catch (Exception e) {
			e.toString();
		}
		return false;
	}

	public boolean fn_Ipegistration(String xDate, String xPatientName,
			String xCaseType, String xRoomNo, String xRoomType) {

		try {

			ContentValues contentValues = new ContentValues();
			contentValues.put("date", xDate);
			contentValues.put("patientname", xPatientName);
			contentValues.put("casetype", xCaseType);
			contentValues.put("roomno", xRoomNo);
			contentValues.put("roomtype", xRoomType);

			SQLiteDatabase db = this.getWritableDatabase();
			db.insert("ipadmission", null, contentValues);
			return true;
		} catch (Exception e) {
			e.toString();
		}
		return false;
	}

	public boolean fn_Advance( String xDate,String xPatientName, String xAmount) {

		try {

			ContentValues contentValues = new ContentValues();
			contentValues.put("date", xDate);
			contentValues.put("patientname", xPatientName);

			contentValues.put("amount", xAmount);

			SQLiteDatabase db = this.getWritableDatabase();
			db.insert("advance", null, contentValues);
			return true;
		} catch (Exception e) {
			e.toString();
		}
		return false;
	}

	public List<String> fn_GetPatients(String xWhereClause) {
		List<String> xExpenses = new ArrayList<String>();
		SQLiteDatabase db = this.getWritableDatabase();
		String xQry = "Select * from patientregistration " + xWhereClause;
		try {
			Cursor cursor = db.rawQuery(xQry, null);

			if (cursor.moveToFirst()) {
				do {
					xExpenses.add(cursor.getString(0));
				} while (cursor.moveToNext());
			}

			cursor.close();
			db.close();

		} catch (Exception e) {
			String xError = e.toString();
		}

		return xExpenses;
	}

	public List<String> fn_GetAdvance(String xWhereClause) {
		List<String> xExpenses = new ArrayList<String>();
		SQLiteDatabase db = this.getWritableDatabase();
		String xQry = "Select * from advance " + xWhereClause;
		try {
			Cursor cursor = db.rawQuery(xQry, null);

			if (cursor.moveToFirst()) {
				do {
					xExpenses.add(cursor.getString(0)+"-"+cursor.getString(1)+"-"+cursor.getString(2));
				} while (cursor.moveToNext());
			}

			cursor.close();
			db.close();

		} catch (Exception e) {
			String xError = e.toString();
		}

		return xExpenses;
	}


	public List<String> fn_GetIpAdmission(String xWhereClause) {
		List<String> xExpenses = new ArrayList<String>();
		SQLiteDatabase db = this.getWritableDatabase();
		String xQry = "Select * from ipadmission " + xWhereClause;
		try {
			Cursor cursor = db.rawQuery(xQry, null);

			if (cursor.moveToFirst()) {
				do {
					xExpenses.add(cursor.getString(1) + "-"
							+ cursor.getString(3));
				} while (cursor.moveToNext());
			}

			cursor.close();
			db.close();

		} catch (Exception e) {
			String xError = e.toString();
		}

		return xExpenses;
	}

}
