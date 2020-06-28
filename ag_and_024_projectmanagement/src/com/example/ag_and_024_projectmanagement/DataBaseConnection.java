package com.example.ag_and_024_projectmanagement;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseConnection extends SQLiteOpenHelper {

	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_NAME = "ag_and_024_projectmanagement.db";
	String xProject;

	public DataBaseConnection(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		try {

			xProject = "create table  project"
					+ "(projname varchar(50),projdesc varchar(100))";
			db.execSQL(xProject);
		} catch (Exception e) {

		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " +xProject);
		onCreate(db);
	}
	public Boolean insertProject(String xProjName,String xProjDesc) {

		try {

			ContentValues contentValues = new ContentValues();
			contentValues.put("projname", xProjName);
			contentValues.put("projdesc", xProjDesc);
			SQLiteDatabase db = this.getWritableDatabase();
			db.insert("project", null, contentValues);

		} catch (Exception e) {
			e.toString();
		}
		return true;
	}
}