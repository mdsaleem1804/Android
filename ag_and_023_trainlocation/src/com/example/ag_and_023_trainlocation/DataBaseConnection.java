package com.example.ag_and_023_trainlocation;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseConnection extends SQLiteOpenHelper {

	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_NAME = "ag_and_023_trainlocation.db";
	String xTrainTable;

	public DataBaseConnection(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		try {

			xTrainTable = "create table  traindata"
					+ "(id varchar(10),step1 varchar(50),step2 varchar(10),step3 varchar(50),step4 varchar(10),step5 varchar(50))";
			db.execSQL(xTrainTable);

			db.execSQL("insert into traindata values('1','left','right','straight','getdown','goup')");
		} catch (Exception e) {

		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " +xTrainTable);
		onCreate(db);
	}
	public Boolean insertTrainingDetails(String xStep1,String xStep2,String xStep3,String xStep4,String xStep5) {

		try {

			ContentValues contentValues = new ContentValues();
			contentValues.put("step1", xStep1);
			contentValues.put("step2", xStep2);
			contentValues.put("step3", xStep3);
			contentValues.put("step4", xStep4);
			contentValues.put("step5", xStep5);
			SQLiteDatabase db = this.getWritableDatabase();
			String xId="1";
			db.update("traindata", contentValues, "id='"
					+ xId + "'", null);

		} catch (Exception e) {
			e.toString();
		}
		return true;
	}
}