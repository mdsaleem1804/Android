package com.hellotamila.ah_and_009_hospial;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DataBaseConnection extends SQLiteOpenHelper {

	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_NAME = "ah_and_009_hospial.db";
	String xUser,xUserProblemToDoctor;

	public DataBaseConnection(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		try {

            xUser = "create table user " + "(name varchar(50),"
					+ "mobileno varchar(50)," + "generalproblem varchar(50)," + "username varchar(50),"
					+ "password varchar(50))";
			db.execSQL(xUser);
			xUserProblemToDoctor = "create table user_problem_to_doctor " + "(username varchar(50),"
					+ " doctorname varchar(50),problem varchar(50))";
			db.execSQL(xUserProblemToDoctor);


		} catch (Exception e) {

		}
	}
	


	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + xUser);
		db.execSQL("DROP TABLE IF EXISTS " + xUserProblemToDoctor);
		onCreate(db);
	}

	public void insertUser(String a, String b, String c, String d,String f) {
		try {
			ContentValues contentValues = new ContentValues();
			contentValues.put("name", a);
			contentValues.put("mobileno", b);
			contentValues.put("generalproblem", c);
			contentValues.put("username", d);
            contentValues.put("password", f);
			SQLiteDatabase db = this.getWritableDatabase();
			db.insert("user", null, contentValues);
		} catch (Exception e) {
			e.toString();
		}
	}

	public void insertUserProblemtoDoctor(String a,String b,String c) {
		try {
			ContentValues contentValues = new ContentValues();
			contentValues.put("username", a);
			contentValues.put("doctorname", b);
			contentValues.put("problem", c);
			SQLiteDatabase db = this.getWritableDatabase();
			db.insert("user_problem_to_doctor", null, contentValues);
		} catch (Exception e) {
			e.toString();
		}
	}

	

}

