package com.example.ag_and_011_smsencryption;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseConnection extends SQLiteOpenHelper {

	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_NAME = "ag_and_011_smsencryption";
	String xMessageTable;

	public DataBaseConnection(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		try {
			xMessageTable = "create table message " + "(senderno varchar(50),"
					+ "messsagedetails varchar(50)," + "encryptedtext varchar(10))";
			db.execSQL(xMessageTable);
		} catch (Exception e) {

		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + xMessageTable);
		onCreate(db);
	}
	
	
}

/*


*/