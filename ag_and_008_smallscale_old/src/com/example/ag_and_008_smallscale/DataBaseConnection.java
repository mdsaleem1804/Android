package com.example.ag_and_008_smallscale;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseConnection extends SQLiteOpenHelper {

	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_NAME = "ag_and_008_smallscale";
	String xExpensesTable,xReminderTable;

	public DataBaseConnection(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		try {

		} catch (Exception e) {

		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		//db.execSQL("DROP TABLE IF EXISTS " + xExpensesTable);
		onCreate(db);
	}
	
	public boolean fn_GetDrivers(String xUserName,String xPassword) {
		
		SQLiteDatabase db = this.getWritableDatabase();
		String xQry = "Select * from vendorregistration where username="+xUserName+"and password="+xPassword; 
		try{
		Cursor cursor = db.rawQuery(xQry,null);

		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				
			} while (cursor.moveToNext());
		}
		
		// closing connection
		cursor.close();
		db.close();
		}
		catch(Exception e)
		{
			//String xError=e.toString();
		}

		// returning lables
		return true;
	}

	

	public List<String> fn_VendorDetails(String xWhereCondition) {
		List<String> xExpenses = new ArrayList<String>();
		SQLiteDatabase db = this.getWritableDatabase();
		String xQry = "Select * from vendorregistration "+xWhereCondition; 
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

}

/*


*/